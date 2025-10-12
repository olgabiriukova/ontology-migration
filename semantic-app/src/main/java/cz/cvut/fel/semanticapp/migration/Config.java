package cz.cvut.fel.semanticapp.migration;

import cz.cvut.fel.executor.Executor;
import cz.cvut.fel.loader.ChangeLogLoader;
import cz.cvut.fel.model.ChangeLog;
import cz.cvut.fel.utils.RDFUtils;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class Config {

    @Bean
    public ChangeLogLoader changeLogLoader() {
        return new ChangeLogLoader();
    }

    @Bean
    public Executor executor(Model jenaModel) {
        return new Executor(jenaModel);
    }

    @Bean
    public Model jenaModel() throws IOException {
        String inputFile = "ontology/ontology.ttl"; // путь в resources
        Model model = ModelFactory.createDefaultModel();
        try (InputStream in = new ClassPathResource(inputFile).getInputStream()) {
            model.read(in, null, RDFUtils.getRDFFormat(inputFile));
        }
        return model;
    }

    @Bean
    public ChangeLog changeLog(ChangeLogLoader loader) {
        try (InputStream input = new ClassPathResource("/changelog/changelog.yaml").getInputStream()) {
            return loader.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load changelog.yaml", e);
        }
    }
}
