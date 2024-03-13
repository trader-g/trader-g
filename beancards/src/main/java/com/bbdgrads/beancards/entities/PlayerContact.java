package com.bbdgrads.beancards.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "PlayerContact")
public class PlayerContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PlayerContactId")
    private Integer playerContactId;

    @ManyToOne
    @JoinColumn(name = "ContactTypeId", referencedColumnName = "ContactTypeId")
    private ContactType contactType;

    @Column(name = "ContactValue")
    private String contactValue;

    @ManyToOne
    @JoinColumn(name = "PlayerId", referencedColumnName = "PlayerId")
    private Player player;

    public PlayerContact() {
    }

    public PlayerContact(Player player, ContactType contactType, String contactValue) {
        this.player = player;
        this.contactType = contactType;
        this.contactValue = contactValue;
    }

    public String getContactValue() {
        return contactValue;
    }

    public void setContactValue(String email) {
        this.contactValue = email;
    }

    public Integer getPlayerContactId() {
        return playerContactId;
    }
}
