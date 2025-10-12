package cz.cvut.fel.semanticapp.migration;

import cz.cvut.fel.executor.Executor;
import cz.cvut.fel.model.ChangeLog;
import jakarta.annotation.PostConstruct;
import org.apache.jena.rdf.model.Model;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

@Component
public class Runner {
    private final Executor executor;
    private final ChangeLog changeLog;
    private final Model model;

    public Runner(Executor executor, ChangeLog changeLog, Model model) {
        this.executor = executor;
        this.changeLog = changeLog;
        this.model = model;
    }

    @PostConstruct
    public void runMigration() throws Exception{
        executor.execute(changeLog);
        System.out.println("Saving migrated ontology to: " + new File("ontology_migrated.ttl").getAbsolutePath());

        try (FileOutputStream out = new FileOutputStream("ontology_migrated.ttl")) {
            executor.getModel().write(out, "TTL");
        }
    }
}