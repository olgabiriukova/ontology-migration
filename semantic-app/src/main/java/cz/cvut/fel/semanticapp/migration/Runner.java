package cz.cvut.fel.semanticapp.migration;

import cz.cvut.fel.exceptions.ChangeLogValidationException;
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

    public Runner(@Value("${fuseki.endpoint}") String fusekiServer,
                  @Value("${fuseki.user}") String user,
                  @Value("${fuseki.password}") String password,
                  @Value("${rdf4j.server:}") String rdf4jServer,
                  @Value("${rdf4j.repository}") String rdf4jRepo) throws IOException {
        if(rdf4jServer!=null && !rdf4jServer.isBlank()){
            this.migrationRunner = new MigrationRunner(rdf4jServer, rdf4jRepo);
        }else if(fusekiServer!=null && !fusekiServer.isBlank()){
            this.migrationRunner = new MigrationRunner(fusekiServer, user, password);
        }else{
            throw new IllegalArgumentException("endpoint is blank");
        }
    }

    @PostConstruct
    public void runMigration() throws Exception{
        try{
            migrationRunner.run();
        } catch (ChangeLogValidationException e) {
            System.err.println("ChangeLog validation error!");
            System.exit(1);

        } catch (IOException e) {
            System.err.println("Changelog is not found");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Migration errors: " + e.getMessage());
            System.exit(1);
        }
        System.out.println("Migration completed.");
    }
}