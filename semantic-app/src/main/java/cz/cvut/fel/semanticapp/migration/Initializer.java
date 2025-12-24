package cz.cvut.fel.semanticapp.migration;

import cz.cvut.fel.exceptions.ChangeLogValidationException;
import cz.cvut.fel.runner.MigrationRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Environment env = applicationContext.getEnvironment();
        String type = env.getProperty("repository.type");
        String endpoint = env.getProperty("repository.endpoint");
        String user = env.getProperty("repository.user");
        String password = env.getProperty("repository.password");
        try {
            MigrationRunner runner = new MigrationRunner(type, endpoint, user, password);
            runner.run();
            System.out.println("Migration completed.");
        } catch (ChangeLogValidationException e) {
            System.err.println("ChangeLog validation error!");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Changelog is not found!");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Migration errors: " + e.getMessage());
            System.exit(1);
        }

    }
}