package cz.cvut.fel.executor;

import cz.cvut.fel.model.ChangeLog;
import cz.cvut.fel.model.ChangeSet;
import cz.cvut.fel.model.Step;


public class Executor {
    public void execute(ChangeLog changeLog) {
        System.out.println("Start migration");
        for (ChangeSet changeSet : changeLog.getChangeSets()) {
            System.out.println("ChangeSet: " + changeSet.getId());
            for (Step step : changeSet.getSteps()) {
                System.out.println("Step type: " + step.getType());
                System.out.println("Apply step logic");
                //ToDo
            }
        }
        System.out.println("Migration finished.");
    }
}
