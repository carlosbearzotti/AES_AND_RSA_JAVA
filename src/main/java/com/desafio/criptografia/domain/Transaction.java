package com.desafio.criptografia.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import com.desafio.criptografia.crypto.AesCryptoConverter;
import com.desafio.criptografia.crypto.RsaCryptoConverter;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = AesCryptoConverter.class)
    @Column(name = "user_document", length = 512)
    private String userDocument;

    @Convert(converter = RsaCryptoConverter.class)
    @Column(name = "credit_card_token", length = 2048) // RSA can have large outputs
    private String creditCardToken;

    @Column(name = "value")
    private Long value;

    public Transaction() {
    }

    public Transaction(String userDocument, String creditCardToken, Long value) {
        this.userDocument = userDocument;
        this.creditCardToken = creditCardToken;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserDocument() {
        return userDocument;
    }

    public void setUserDocument(String userDocument) {
        this.userDocument = userDocument;
    }

    public String getCreditCardToken() {
        return creditCardToken;
    }

    public void setCreditCardToken(String creditCardToken) {
        this.creditCardToken = creditCardToken;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
