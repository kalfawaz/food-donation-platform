package com.fooddonation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

// We use our own JWT login, so the default Spring user is turned off
@SpringBootApplication(exclude = UserDetailsServiceAutoConfiguration.class)
public class FoodDonationApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodDonationApplication.class, args);
    }
}
