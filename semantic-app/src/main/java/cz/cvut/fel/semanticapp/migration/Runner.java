package cz.cvut.fel.semanticapp.migration;

import cz.cvut.fel.exceptions.ChangeLogValidationException;
import cz.cvut.fel.executor.Executor;
import cz.cvut.fel.model.ChangeLog;
import cz.cvut.fel.runner.MigrationRunner;
import jakarta.annotation.PostConstruct;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdfconnection.RDFConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class Runner implements ApplicationRunner {
    private final MigrationRunner migrationRunner;

    public Runner(@Value("${repository.type}") String type,
                  @Value("${repository.endpoint}") String endpoint,
                  @Value("${repository.user:}") String user,
                  @Value("${repository.password:}") String password) throws IOException {
        this.migrationRunner = new MigrationRunner(type, endpoint, user, password);
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            migrationRunner.run();
            System.out.println("Migration completed.");
        } catch (ChangeLogValidationException e) {
            System.err.println("ChangeLog validation error!");
            shutdown(e);
        } catch (IOException e) {
            System.err.println("Changelog is not found!");
            shutdown(e);
        } catch (Exception e) {
            System.err.println("Migration errors: " + e.getMessage());
            shutdown(e);
        }
    }

    private void shutdown(Exception e) {
        e.printStackTrace();
        System.exit(1);
    }
}