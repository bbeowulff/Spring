package com.example.spring_12_15.Repository;


import com.example.spring_12_15.Model.Purchase;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PurchaseRepository {

    private final JdbcTemplate jdbc;

    public PurchaseRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void storePurchase(Purchase purchase) {
        String sql = "INSERT INTO purchase VALUES (NULL, ?, ?)";

        jdbc.update(
                sql,
                purchase.getProduct(),
                purchase.getPrice()
        );
    }


    public List<Purchase> findAllPurchases() {
        String sql = "SELECT * FROM purchase";

        RowMapper<Purchase> rowMapper = (resultSet, rowNum) -> {
            Purchase p = new Purchase();
            p.setId(resultSet.getInt("id"));
            p.setProduct(resultSet.getString("product"));
            p.setPrice(resultSet.getBigDecimal("price"));
            return p;
        };

        return jdbc.query(sql, rowMapper);
    }
}

