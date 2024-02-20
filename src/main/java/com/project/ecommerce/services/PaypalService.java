package com.project.ecommerce.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.core.PayPalHttpClient;
import com.paypal.http.HttpResponse;
import com.paypal.orders.AmountWithBreakdown;
import com.paypal.orders.ApplicationContext;
import com.paypal.orders.Order;
import com.paypal.orders.OrderRequest;
import com.paypal.orders.OrdersCaptureRequest;
import com.paypal.orders.OrdersCreateRequest;
import com.paypal.orders.PurchaseUnitRequest;
import com.project.ecommerce.dto.PaypalOrderBookingRequestDTO;
import com.project.ecommerce.dto.PaypalOrderRequestDTO;
import com.project.ecommerce.entities.CompletedOrder;
import com.project.ecommerce.entities.EPaymentMethod;
import com.project.ecommerce.entities.PaymentOrder;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class PaypalService {
    @Autowired
    private PayPalHttpClient payPalHttpClient;

    @Autowired
    private OrderService orderService;

    public PaymentOrder createPayment(PaypalOrderRequestDTO paypalOrderRequestDTO, String email) {
        System.out.println(paypalOrderRequestDTO.getAddress());
        DecimalFormat df = new DecimalFormat("####0.00");
        BigDecimal fee = new BigDecimal(df.format(paypalOrderRequestDTO.getTotal()));
        System.out.println(fee.toString());
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");
        AmountWithBreakdown amountBreakdown = new AmountWithBreakdown().currencyCode("USD").value(fee.toString());
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest().amountWithBreakdown(amountBreakdown);
        orderRequest.purchaseUnits(List.of(purchaseUnitRequest));
        ApplicationContext applicationContext = new ApplicationContext()
                .returnUrl("https://localhost:3000/capture")
                .cancelUrl("https://localhost:3000/cancel");
        orderRequest.applicationContext(applicationContext);
        OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);

        try {
            HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
            Order order = orderHttpResponse.result();

            String redirectUrl = order.links().stream()
                    .filter(link -> "approve".equals(link.rel()))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new)
                    .href();

            // Create order
            orderService.createOrder(email, EPaymentMethod.CREDIT_CARD, paypalOrderRequestDTO.getAddress());
            return new PaymentOrder(order.status(),  order.id(), order.links());
        } catch (IOException e) {
            log.error(e.getMessage());
            // return null;
            return new PaymentOrder("Error");
        }
    }


    public CompletedOrder completePayment(String token) {
        OrdersCaptureRequest ordersCaptureRequest = new OrdersCaptureRequest(token);
        try {
            HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersCaptureRequest);
            if (httpResponse.result().status() != null) {
                Order order = httpResponse.result();
                System.out.println(order.status());
                return new CompletedOrder("success", token, "https://localhost:3000/cart");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return new CompletedOrder("error");
    }

    public PaymentOrder createPaymentForBooking(PaypalOrderBookingRequestDTO paypalOrderBookingRequestDTO, String email) {
        DecimalFormat df = new DecimalFormat("####0.00");
        BigDecimal fee = new BigDecimal(df.format(paypalOrderBookingRequestDTO.getTotal()));
        System.out.println(fee.toString());
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");
        AmountWithBreakdown amountBreakdown = new AmountWithBreakdown().currencyCode("USD").value(fee.toString());
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest().amountWithBreakdown(amountBreakdown);
        orderRequest.purchaseUnits(List.of(purchaseUnitRequest));
        ApplicationContext applicationContext = new ApplicationContext()
                .returnUrl("https://localhost:3000/capture")
                .cancelUrl("https://localhost:3000/cancel");
        orderRequest.applicationContext(applicationContext);
        OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest().requestBody(orderRequest);

        try {
            HttpResponse<Order> orderHttpResponse = payPalHttpClient.execute(ordersCreateRequest);
            Order order = orderHttpResponse.result();

            String redirectUrl = order.links().stream()
                    .filter(link -> "approve".equals(link.rel()))
                    .findFirst()
                    .orElseThrow(NoSuchElementException::new)
                    .href();

            // Create order
            return new PaymentOrder(order.status(),  order.id(), order.links());
        } catch (IOException e) {
            log.error(e.getMessage());
            // return null;
            return new PaymentOrder("Error");
        }
    }

    public CompletedOrder completePaymentForBooking(String token) {
        OrdersCaptureRequest ordersCaptureRequest = new OrdersCaptureRequest(token);
        try {
            HttpResponse<Order> httpResponse = payPalHttpClient.execute(ordersCaptureRequest);
            if (httpResponse.result().status() != null) {
                Order order = httpResponse.result();
                System.out.println(order.status());
                return new CompletedOrder("success", token, "https://localhost:3000/my-take-care");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return new CompletedOrder("error");
    }
}
