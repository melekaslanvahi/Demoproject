package com.example.demo.repository;

import com.example.demo.domain.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    Optional<CreditCard> findByUserId(Long userId);

    @Query("SELECT c FROM CreditCard c WHERE c.userId IN :users")
    List<CreditCard> findCreditCardByUsers(@Param("users") List<Long> users);
}
