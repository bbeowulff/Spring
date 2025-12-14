package com.example.spring_12_15.Repository;

import com.example.spring_12_15.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    // Spring Data will derive the query: SELECT a FROM Account a WHERE a.name = ?1
    List<Account> findByName(String name);

    void changeAmount(int i, BigDecimal bigDecimal);
}
