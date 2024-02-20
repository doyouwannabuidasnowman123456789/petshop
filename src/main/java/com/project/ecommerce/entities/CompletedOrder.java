package com.project.ecommerce.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompletedOrder {
    private String status;
    private String payId;
    private String redirectLink;

    public CompletedOrder(String status) {
        this.status = status;
    }
}
