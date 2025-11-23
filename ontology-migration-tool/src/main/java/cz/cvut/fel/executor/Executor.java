package cz.cvut.fel.executor;

import cz.cvut.fel.model.ChangeLog;
import cz.cvut.fel.model.ChangeSet;
import cz.cvut.fel.model.changes.*;
import cz.cvut.fel.fuseki.FusekiRepository;


public class Executor {
    private final FusekiRepository repository;

    public Executor(FusekiRepository repository) {
        this.repository = repository;
    }

    public void execute(ChangeLog changeLog) {
        System.out.println("Start migration");
        StringBuilder transaction = new StringBuilder();

        for (ChangeSet changeSet : changeLog.getChangeSets()) {
            System.out.println("ChangeSet: " + changeSet.getId());
            for (Change change : changeSet.getChanges()) {
                System.out.println("Step type: " + change.getType());
                System.out.println("Apply step logic");
                String sparql = change.apply(repository);
                transaction.append(sparql).append(";\n");
            }
            try{
                repository.update(transaction.toString());
                System.out.println("ChangeSet: " + changeSet.getId() + " applied successfully.");
            }catch(Exception e){
                System.err.println("ChangeSet "+changeSet.getId()+" FAILED: "+e.getMessage());
                throw new RuntimeException("Migration failed at ChangeSet: " + changeSet.getId(), e);
            }
        }
        System.out.println("Migration finished.");

    }

}
