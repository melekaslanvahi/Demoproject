package com.example.demo.domain.entity;

import com.example.demo.dto.OnlineSupportDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.example.demo.utils.Constants;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(
        name = "online_support"
)
@NoArgsConstructor
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class OnlineSupport extends BaseEntity<OnlineSupport, OnlineSupportDTO>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "subject", nullable = false)
    @Size(max = Constants.MAX_SUBJECT_SIZE)
    private String subject;

    @NotNull
    @Column(name = "message", nullable = false)
    @Size(max = Constants.MAX_MESSAGE_SIZE)
    private String message;

    @NotNull
    @Column(name= "user_id", nullable = false)
    private Long user;
}
