package com.bbdgrads.beancards.ServiceImplementations;

import com.bbdgrads.beancards.Entities.ContactType;
import com.bbdgrads.beancards.Entities.Player;
import com.bbdgrads.beancards.Entities.PlayerContact;
import com.bbdgrads.beancards.Repositories.ContactTypeRepository;
import com.bbdgrads.beancards.Repositories.PlayerContactRepository;
import com.bbdgrads.beancards.Repositories.PlayerRepository;
import com.bbdgrads.beancards.Services.AuthenticationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerContactRepository playerContactRepository;

    @Autowired
    private ContactTypeRepository contactTypeRepository;

    public String extractAccessTokenFromResponse(ResponseEntity<String> response) {
        String responseBody = response.getBody();
        if(response != null) {
            String[] responseParts = responseBody.split("&");
            //String of responseParts is an array of strings, each string is a key value pair of params
            return Arrays.stream(responseParts)
                    //map each string to a string array of key value pairs,
                    // so we have an array of arrays [[key, value],of params]
                    .map(param -> param.split("="))
                    //filter the array of arrays to only include the valid key value pair of access_token
                    .filter(keyValuePair -> keyValuePair.length == 2 && keyValuePair[0].equals("access_token"))
                    //map the array of arrays to an array of strings, by extracting the value of the access_token
                    .map(keyValuePair -> keyValuePair[1])
                    //return the first element of the array of strings, which is the access_token
                    .findFirst()
                    //if the array of strings is empty, return null
                    .orElse(null);
        }
        /* Imperative style
        if (response != null) {
            String[] responseParts = responseBody.split("&");
            for (String param : responseParts) {
                String[] keyValuePair = param.split("=");
                if (keyValuePair.length == 2 && keyValuePair[0].equals("access_token")) {
                    accessToken = keyValuePair[1];
                    break;
                }
            }
        }*/
        return null;
    }

        @Override
        public String exchangeCodeForGithubToken(String code) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("client_id", clientId);
            requestBody.add("client_secret", clientSecret);
            requestBody.add("code", code);

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    "https://github.com/login/oauth/access_token",
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            return extractAccessTokenFromResponse(response);
        }

        @Override
        @Transactional
        public Player signInWithGithubToken(String githubAccessToken) {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(githubAccessToken);

            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    "https://api.github.com/user",
                    HttpMethod.GET,
                    requestEntity,
                    Map.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                Map<String, String> gitHubUserData = response.getBody();

                String username = (String) gitHubUserData.get("login");
                String email = (String) gitHubUserData.get("email");

                //Create or update player
                Player player = playerRepository.findByDisplayName(username)
                        .orElseGet(() -> new Player(username));

                //Ensuring our player has a contact email and the right contact type
                ensurePlayerEmailContact(player, email);

                //save
                playerRepository.save(player);

                return player;
            } else {
                return null;
            }
        }

    private void ensurePlayerEmailContact(Player player, String email) {
        ContactType emailType = contactTypeRepository.findByContactType("email")
                .orElseGet(() -> new ContactType("email"));

        // Save email type if it's new
        if (emailType.getId() == null) {
            contactTypeRepository.save(emailType);
        }

        PlayerContact emailContact = playerContactRepository
                .findByPlayerAndContactType(player, emailType)
                .orElseGet(() -> new PlayerContact(player, emailType, email));

        if (!email.equals(emailContact.getContactValue())) {
            emailContact.setContactValue(email);
            playerContactRepository.save(emailContact);
        }
    }
}
