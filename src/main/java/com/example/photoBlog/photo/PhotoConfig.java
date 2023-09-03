package com.example.photoBlog.photo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PhotoConfig {
    @Bean
    CommandLineRunner commandLineRunner(
        PhotoRepository repository) {
        return args -> {
            Photo photo1 = new Photo(
                "Photo 1", 1, "Category 1");
            Photo photo2 = new Photo(
                "Photo 2", 2, "Category 2");
            Photo photo3 = new Photo(
                "Photo 3", 3, "Category 3");
            repository.saveAll(
                Arrays.asList(photo1, photo2, photo3));
        };
    }
}
