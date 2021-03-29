package com.example.demo.domain.entity;

import com.example.demo.dto.MemberDTO;

import com.example.demo.encrypt.AESEncryptionDecryption;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import com.example.demo.utils.Constants;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Entity
@Data
@Table(
        name = "member"
)
@NoArgsConstructor
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class Member extends BaseEntity<Member, MemberDTO>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    @Size(max = Constants.MAX_NAME_SIZE)
    private String name;

    @NotNull
    @Column(name = "surname", nullable = false)
    @Size(max = Constants.MAX_SURNAME_SIZE)
    private String surname;

    @NotNull
    @Column(name = "email", nullable = false)
    @Email
    @Size(max = Constants.MAX_EMAIL_SIZE)
    private String email;

    @NotNull
    @Column(name = "password", nullable = false)
    @Size(max = Constants.MAX_PASSWORD_SIZE)
    private String encryptedPassword;

    @Transient
    private String password;

    @NotNull
    @Column(name = "membership_date", nullable = false)
    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE )
    private OffsetDateTime membershipDate;

    @NotNull
    @Column(name = "membership_status", nullable = false)
    private int membershipStatus;

    @NotNull
    @Column(name = "membership_type", nullable = false)
    private int membershipType;

    @Column(name = "membership_end_date")
    @DateTimeFormat( iso = DateTimeFormat.ISO.DATE )
    private OffsetDateTime membershipEndDate;


    @PostLoad
    public void decryptPassword() {
        // decrypts password during DATABASE READ
        AESEncryptionDecryption aes = new AESEncryptionDecryption();
        this.password = aes.decrypt(encryptedPassword,"secrete");
    }

    @PrePersist
    @PreUpdate
    public void encryptPassword() {
        // encrypts password during INSERT/UPDATE
        AESEncryptionDecryption aes = new AESEncryptionDecryption();
        this.encryptedPassword = aes.encrypt(password, "secrete");
        System.out.println( "encrypted password = " + this.encryptedPassword);
    }

}
