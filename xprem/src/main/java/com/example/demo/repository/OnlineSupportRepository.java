package com.example.demo.repository;

import com.example.demo.domain.entity.OnlineSupport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnlineSupportRepository extends JpaRepository<OnlineSupport, Long> {


}
