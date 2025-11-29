package cz.cvut.fel.executor;

import cz.cvut.fel.model.ChangeLog;
import cz.cvut.fel.model.ChangeSet;
import cz.cvut.fel.model.changes.*;
import cz.cvut.fel.repository.OntologyRepository;
import cz.cvut.fel.versioning.VersionManager;


public class Executor {
    private final OntologyRepository repository;
    private final VersionManager versionManager;

    public Executor(OntologyRepository repository) {
        this.repository = repository;
        this.versionManager = new VersionManager(repository);
    }

    public void execute(ChangeLog changeLog) {
        System.out.println("Start migration");
        repository.begin();
        try {
            for (ChangeSet changeSet : changeLog.getChangeSets()) {
                if(versionManager.isApplied(changeSet.getId())){
                    System.out.println("Skip already applied changeset: " + changeSet.getId());
                    continue;
                }
                System.out.println("ChangeSet: " + changeSet.getId());
                //StringBuilder transaction = new StringBuilder();
                for (Change change : changeSet.getChanges()) {
                    System.out.println("Step type: " + change.getType());
                    System.out.println("Apply step logic");
                    String sparql = change.apply(repository);
                    repository.update(sparql);
                }
                versionManager.markApplied(changeSet.getId());
            }
            repository.commit();
        } catch (Exception e) {
            System.err.println("FAILED: " + e.getMessage());
            throw new RuntimeException(e);
        }
    System.out.println("Migration finished.");

    }

}
