package com.app.repository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;



/**
 * 
 * @author Somenath
 * 
 * This class will be starting point of Spring Boot Application.
 *
 */
@SpringBootApplication(scanBasePackages = { "com.app" })
public class Application {

	/**
	 * Main method which will initiate springboot application.
	 * @param args arguments to be provided
	 */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
       
    }

    @Bean
    public CommandLineRunner commandLineRunner(RetailManagerRepository ctx) {
        return args -> {

        			// System.out.println("Let's inspect the beans provided by Spring Boot:");

            
            		/*// save a couple of customers
            		ctx.save(new Shop("MDonaldsOne","Premier Plaza, Old Mumbai Pune Highway, Anand Nagar, Pimpri Colony, Pimpri-Chinchwad, Maharashtra ", "411019"));
            		ctx.save(new Shop("MDonaldsTwo", "Survey no 15 / 4, Near Santosh Nagar, Thergoan, Pune, Maharashtra","411033"));
            		
         			// fetch an individual customer by ID
         			Shop shop = ctx.findOne(1L);
         			System.out.println(shop.getShopZipCode());
         			
         			Shop shop1 = ctx.findByShopName("S2");
         			System.out.println(shop1.getShopZipCode());*/

         			
            
        };
    }

}
