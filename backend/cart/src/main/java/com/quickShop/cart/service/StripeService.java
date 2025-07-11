package com.quickShop.cart.service;

import com.quickShop.cart.Entity.Cart;
import com.quickShop.cart.Entity.OrderLog;
import com.quickShop.cart.repository.OrderLogRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StripeService {

    @Value("${stripe.secret.key}")
    private String secretKey;

    @Value("${stripe.success.url}")
    private String successUrl;

    @Value("${stripe.cancel.url}")
    private String cancelUrl;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    @Autowired
    private OrderLogRepository orderLogRepository;

    public String createCheckoutSession(Cart cart) throws StripeException {
        List<SessionCreateParams.LineItem> lineItems = cart.getItems().stream()
                .map(item -> SessionCreateParams.LineItem.builder()
                        .setQuantity((long) item.getQuantity())
                        .setPriceData(
                                SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("inr")
                                        .setUnitAmount((long) (item.getPrice() * 100)) // in cents
                                        .setProductData(
                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                        .setName(item.getTitle())
                                                        .build()
                                        )
                                        .build()
                        )
                        .build()
                ).toList();

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(successUrl)
                .setCancelUrl(cancelUrl)
                .addAllLineItem(lineItems)
                .build();

        Session session = Session.create(params);
        OrderLog log = new OrderLog();
        log.setAmount(cart.getTotal());
        log.setTransactionId(session.getId());
        log.setStatus("INITIATED");
        log.setCreatedAt(LocalDateTime.now());
        orderLogRepository.save(log);

        return session.getUrl();
    }
}
