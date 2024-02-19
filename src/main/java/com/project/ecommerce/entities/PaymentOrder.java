package com.project.ecommerce.entities;

import java.io.Serializable;
import java.util.List;

import com.paypal.orders.LinkDescription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOrder implements Serializable {
    private String status;
    private String id;
    private List<LinkDescription> links;

    public PaymentOrder(String status) {
        this.status = status;
    }
}
