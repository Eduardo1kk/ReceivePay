package com.receivepay.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MercadoPagoConfig {

    public static final String API_URL = "https://api.mercadopago.com";
    public static final String ACCESS_TOKEN = "APP_USR-7743198616330832-111613-d48e67a12b2cc7a3e497c763238de349-3847360611";

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
