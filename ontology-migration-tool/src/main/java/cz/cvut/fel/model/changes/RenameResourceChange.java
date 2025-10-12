package cz.cvut.fel.model.changes;

import com.fasterxml.jackson.annotation.JsonTypeName;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

import java.util.List;


public class RenameResourceChange extends Change{
    private String oldName;
    private String newName;

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
    public String getOldName() {
        return oldName;
    }
    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    @Override
    public void apply(Model model) {
        Resource oldResource = model.getResource(oldName);
        Resource newResource = model.getResource(newName);

        if (oldResource == null) {
            System.out.println("Resource not found:"+oldName);
            return;
        }

        List<Statement> props = oldResource.listProperties().toList();
        for (Statement stmt : props) {
            newResource.addProperty(stmt.getPredicate(), stmt.getObject());
        }

        List<Statement> refs = model.listStatements(null, null, oldResource).toList();
        for (Statement stmt : refs) {
            model.add(stmt.getSubject(), stmt.getPredicate(), newResource);
            model.remove(stmt);
        }
        model.removeAll(oldResource, null, null);
        model.removeAll(null, null, oldResource);
        System.out.println("Resource changed to " + newName);
    }
}
