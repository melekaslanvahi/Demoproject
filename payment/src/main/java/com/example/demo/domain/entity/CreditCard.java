package com.example.demo.domain.entity;


import com.example.demo.dto.CreditCardDTO;
import com.example.demo.enrypt.AESEncryptionDecryption;
import com.example.demo.utils.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(
        name = "member_credit_card"
)
@NoArgsConstructor
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class CreditCard extends BaseEntity<CreditCard, CreditCardDTO>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "surname", nullable = false)
    private String surname;

    // this field could have package private get/set methods
    @NotNull
    @Column(name = "card_number", length = Constants.MAX_CARD_NUMBER_SIZE, nullable = false)
    private String encrpytedCardNumber;

    // this is the public operated upon field
    @Transient
    private String cardNumber;


    @NotNull
    @Column(name = "cvv", length = Constants.MAX_CVV, nullable = false)
    private int cvv;

    // format is like 11/21
    @NotNull
    @Column(name = "card_expire_date", length = Constants.MAX_EXPIRE_DATE, nullable = false)
    private String cardExpireDate;


    @NotNull
    @Column(name = "user_id", nullable = false)
    private Long userId;


    @PostLoad
    public void decryptCardNumber() {
        // decrypts card number during DATABASE READ
        AESEncryptionDecryption aes = new AESEncryptionDecryption();
        this.cardNumber = aes.decrypt(encrpytedCardNumber,"secrete");
    }

    @PrePersist
    @PreUpdate
    public void encryptCardNumber() {
        // encrypts card number during INSERT/UPDATE
        AESEncryptionDecryption aes = new AESEncryptionDecryption();
        this.encrpytedCardNumber = aes.encrypt(cardNumber, "secrete");
        System.out.println( "encryptedCardNumber = " + this.encrpytedCardNumber );
    }


}
