package cz.cvut.fel.semanticapp.migration;

import cz.cvut.fel.executor.Executor;
import cz.cvut.fel.model.ChangeLog;
import cz.cvut.fel.runner.MigrationRunner;
import jakarta.annotation.PostConstruct;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdfconnection.RDFConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class Runner {
    private final MigrationRunner migrationRunner;

    public Runner(@Value("${fuseki.endpoint}") String endpoint,
                  @Value("${fuseki.user}") String user,
                  @Value("${fuseki.password}") String password) throws IOException {
        this.migrationRunner = new MigrationRunner(endpoint, user, password);
    }

    @PostConstruct
    public void runMigration() throws Exception{
        try(InputStream in = new ClassPathResource("/changelog/changelog.yaml").getInputStream()) {
            migrationRunner.run(in);
        }
        System.out.println("Migration completed.");
    }
}