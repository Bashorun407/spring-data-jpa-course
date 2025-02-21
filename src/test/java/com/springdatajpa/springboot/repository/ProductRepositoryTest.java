package com.springdatajpa.springboot.repository;

import com.springdatajpa.springboot.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveMethod(){
        //Create product
        Product product = new Product();
        product.setName("Product-1");
        product.setDescription("product-1 description");
        product.setPrice(new BigDecimal(100));
        product.setSku("100AS12E");
        product.setActive(true);
        product.setImageUrl("product-1.png");

        //Save product
        Product savedProduct = productRepository.save(product);

        //Display product
        System.out.println(savedProduct);
        System.out.println("New Product: " + savedProduct.toString());
    }

    @Test
    void updateUsingSaveMethod(){
        //retrieve record from the database using id
        Long id = 1L;
        Product product = productRepository.findById(id).get();

        //update record
        product.setName("Updated Product-1");
        product.setDescription("Updated Product-1 description");

        //save updated record to the database
        productRepository.save(product);
    }

    @Test
    void findByIdMethod(){
        Long id = 1L;

        Product product = productRepository.findById(id).get();
    }

    @Test
    void saveAllMethod(){

        //Create product 2
        Long id = 2L;
       Product product2 = new Product();
       product2.setName("Product-2");
       product2.setDescription("Product-2 Description");
       product2.setPrice(new BigDecimal(200));
       product2.setSku("200AS14E");
       product2.setActive(true);
       product2.setImageUrl("product-2.png");

        //Create product 3
        Long id2 = 3L;
        Product product3 = new Product();
        product3.setName("Product-3");
        product3.setDescription("Product-3 Description");
        product3.setPrice(new BigDecimal(200));
        product3.setSku("300AS16E");
        product3.setActive(true);
        product3.setImageUrl("product-3.png");

        //Save multiple items using saveAll() method
        productRepository.saveAll(List.of(product2, product3));
    }

    @Test
    void findAllMethod(){
        List<Product> products = productRepository.findAll();
        products.forEach((p)-> {
            System.out.println(p.getName());
        });
    }

    @Test
    void deleteById(){
        Long id = 1L;
        productRepository.deleteById(id);
    }

    @Test
    void deleteMethod(){
        Long id = 3L;
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
    }

    @Test
    void deleteAllMethod(){
        //productRepository.deleteAll();
        Product product1 = productRepository.findById(6L).get();
        Product product2 = productRepository.findById(7L).get();

        //deleting using the iterable_delete
        productRepository.deleteAll(List.of(product1, product2));
    }

    @Test
    void countMethod(){
        long id = productRepository.count();

        System.out.println("Number of items in the database is: " + id);
    }
}