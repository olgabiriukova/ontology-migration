package cz.cvut.fel.runner;

import cz.cvut.fel.exceptions.ChangeLogValidationException;
import cz.cvut.fel.executor.Executor;
import cz.cvut.fel.loader.ChangeLogLoader;
import cz.cvut.fel.model.ChangeLog;
import cz.cvut.fel.fuseki.FusekiRepository;

import java.io.IOException;
import java.io.InputStream;

public class MigrationRunner {
    private final FusekiRepository repo;
    private final ChangeLogLoader loader;

    public MigrationRunner(String endpoint, String username, String password) throws IOException {
        repo = new FusekiRepository(endpoint, username, password);
        this.loader = new ChangeLogLoader();
    }

    public void run(InputStream input) throws IOException, ChangeLogValidationException {
        ChangeLog changeLog = loader.load(input);
        Executor executor = new Executor(repo);
        executor.execute(changeLog);
    }

}