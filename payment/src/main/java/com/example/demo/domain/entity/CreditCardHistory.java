package com.example.demo.domain.entity;

import com.example.demo.dto.CreditCardDTO;
import com.example.demo.dto.CreditCardHistoryDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(
        name = "member_credit_card_history"
)
@NoArgsConstructor
@ToString(callSuper = false)
@EqualsAndHashCode(callSuper = false)
public class CreditCardHistory extends BaseEntity<CreditCardHistory, CreditCardHistoryDTO>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "credit_card_id", nullable = false)
    private Long credit_card_id;
}