package com.example.spring_12_15.Controller;


import com.example.spring_12_15.Model.Purchase;
import com.example.spring_12_15.Repository.PurchaseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {

    private final PurchaseRepository purchaseRepository;

    public PurchaseController(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }


    @PostMapping
    public void storePurchase(@RequestBody Purchase purchase) {
        purchaseRepository.storePurchase(purchase);
    }

    /**
     * ❺ GET /purchase
     *    - Returns a JSON array of all purchases in the database.
     *    - Example response:
     *      [
     *        {
     *           "id": 1,
     *           "product": "Spring Security in Action",
     *           "price": 25.2
     *        }
     *      ]
     */
    @GetMapping
    public List<Purchase> findPurchases() {
        return purchaseRepository.findAllPurchases();
    }
}

