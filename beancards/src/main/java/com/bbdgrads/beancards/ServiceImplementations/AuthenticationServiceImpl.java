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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
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
        if (response != null) {
            String[] responseParts = responseBody.split("&");
            return Arrays.stream(responseParts)
                    .map(param -> param.split("="))
                    .filter(keyValuePair -> keyValuePair.length == 2 && keyValuePair[0].equals("access_token"))
                    .map(keyValuePair -> keyValuePair[1])
                    .findFirst()
                    .orElse(null);
        }
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
        System.out.println("This ran and here is the code: " + githubAccessToken);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(githubAccessToken);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Attempt to fetch user profile
        ResponseEntity<Map> userResponse = restTemplate.exchange(
                "https://api.github.com/user",
                HttpMethod.GET,
                requestEntity,
                Map.class
        );

        if (userResponse.getStatusCode() != HttpStatus.OK || userResponse.getBody() == null) {
            System.out.println("Failed to fetch user profile.");
            return null;
        }

        System.out.println("User profile response body: " + userResponse.getBody());

        String username = (String) userResponse.getBody().get("login");
        System.out.println("Username: " + username);

        String email = null;

        // Attempt to fetch user emails
        try {
            ResponseEntity<List<Map<String, Object>>> emailResponse = restTemplate.exchange(
                    "https://api.github.com/user/emails",
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<List<Map<String, Object>>>() {
                    }
            );

            if (emailResponse.getStatusCode() == HttpStatus.OK && emailResponse.getBody() != null) {
                for (Map<String, Object> emailObj : emailResponse.getBody()) {
                    Boolean primary = (Boolean) emailObj.get("primary");
                    Boolean verified = (Boolean) emailObj.get("verified");
                    if (Boolean.TRUE.equals(primary) && Boolean.TRUE.equals(verified)) {
                        email = (String) emailObj.get("email");
                        System.out.println("Primary verified email: " + email);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to fetch user emails or no verified primary email found.");
        }

        if (username != null) {
            System.out.println("Creating or updating player: " + username + " and email " + email);
            Player player = playerRepository.findByDisplayName(username)
                    .orElseGet(() -> new Player(username));
            System.out.println("Here is the player: " + player);
            // Save and return the updated player
            playerRepository.save(player);

            if (email != null) {
                ensurePlayerEmailContact(player, email);
            }

            System.out.println("Here is the player after saving: " + player);
            return player;
        } else {
            System.out.println("Username was null, unable to create or update player.");
            return null;
        }
    }



    private void ensurePlayerEmailContact(Player player, String email) {
        ContactType emailType = contactTypeRepository.findByContactType("email")
                .orElseGet(() -> new ContactType("email"));
        System.out.println("Here is the email type: " + emailType);
        if (emailType.getId() == null) {
            contactTypeRepository.save(emailType);
            System.out.println("Email type was null, saving email type first before contact.");
        }

        PlayerContact emailContact = playerContactRepository
                .findByPlayerAndContactType(player, emailType)
                .orElseGet(() -> new PlayerContact(player, emailType, email));
        System.out.println("Here is the email contact: " + emailContact.getContactValue());

        if (!email.equals(emailContact.getContactValue())) {
            emailContact.setContactValue(email);
            playerContactRepository.save(emailContact);
        }

    }
}
