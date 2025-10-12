package cz.cvut.fel.executor;

import cz.cvut.fel.model.ChangeLog;
import cz.cvut.fel.model.ChangeSet;
import cz.cvut.fel.model.changes.Change;
import cz.cvut.fel.model.changes.RenamePropertyChange;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.rdf.model.*;

import java.util.List;


public class Executor {
    private final Model model;

    public Executor(Model model) {
        this.model = model;
    }

    public Model getModel(){
        return model;
    }

    public void execute(ChangeLog changeLog) {
        System.out.println("Start migration");
        for (ChangeSet changeSet : changeLog.getChangeSets()) {
            System.out.println("ChangeSet: " + changeSet.getId());
            for (Change change : changeSet.getChanges()) {
                System.out.println("Step type: " + change.getType());
                System.out.println("Apply step logic");
                //ToDo
                if (change instanceof RenamePropertyChange renamePropertyChange) {
                    renamePropertyChange.apply(model);
                }

            }
        }
        System.out.println("Migration finished.");
    }

}
