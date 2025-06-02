package cz.cvut.fel.semanticapp.migration;

import cz.cvut.fel.executor.Executor;
import cz.cvut.fel.loader.ChangeLogLoader;
import cz.cvut.fel.model.ChangeLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;

@Configuration
public class Config {

    @Bean
    public ChangeLogLoader changeLogLoader() {
        return new ChangeLogLoader();
    }

    @Bean
    public Executor executor() {
        return new Executor();
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
