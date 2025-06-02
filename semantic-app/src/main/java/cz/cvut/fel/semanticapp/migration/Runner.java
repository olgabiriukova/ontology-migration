package cz.cvut.fel.semanticapp.migration;

import cz.cvut.fel.executor.Executor;
import cz.cvut.fel.model.ChangeLog;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Runner {
    private final Executor executor;
    private final ChangeLog changeLog;

    public Runner(Executor executor, ChangeLog changeLog) {
        this.executor = executor;
        this.changeLog = changeLog;
    }

    @PostConstruct
    public void runMigration() {
        executor.execute(changeLog);
    }
}