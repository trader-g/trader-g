package com.bbdgrads.beancards.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ContactType")
public class ContactType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContactTypeId")
    private Integer contactTypeId;

    @Column(name = "ContactType")
    private String contactType;

    public ContactType(String contactType) {
        this.contactType = contactType;
    }

    public ContactType() {
    }

    public Integer getId() {
        return this.contactTypeId;
    }
}
