package cz.cvut.fel.semanticapp;

import cz.cvut.fel.semanticapp.migration.Initializer;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SemanticAppApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SemanticAppApplication.class)
            .initializers(new Initializer())
            .run(args);
    }
}
