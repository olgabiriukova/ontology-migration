package cz.cvut.fel.model.changes;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

import java.util.List;


public class RenameClassChange extends Change{
    private String oldName;
    private String newName;

    public String getOldName() {
        return oldName;
    }
    public void setOldName(String oldName) {
        this.oldName = oldName;
    }
    public String getNewName() {
        return newName;
    }
    public void setNewName(String newName) {
        this.newName = newName;
    }

    @Override
    public void apply(Model model) {
        Resource oldClass = model.getResource(oldName);
        Resource newClass = model.getResource(newName);

        if (oldClass == null) {
            System.out.println("Class not found:"+oldName);
            return;
        }

        List<Statement> props = oldClass.listProperties().toList();
        for (Statement stmt : props) {
            newClass.addProperty(stmt.getPredicate(), stmt.getObject());
        }

        List<Statement> refs = model.listStatements(null, null, oldClass).toList();
        for (Statement stmt : refs) {
            model.add(stmt.getSubject(), stmt.getPredicate(), newClass);
            model.remove(stmt);
        }
        model.removeAll(oldClass, null, null);
        model.removeAll(null, null, oldClass);
        System.out.println("Class changed to " + newName);
    }
}
