package cz.cvut.fel.model.changes;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Statement;

import java.util.List;

public class RenamePropertyChange extends Change {
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
    public void apply(Model model){
        Property oldProperty = model.createProperty(oldName);
        if(oldProperty == null){
            System.out.println("Property not found: " + oldName);
            return;
        }
        Property newProperty = model.createProperty(newName);
        List<Statement> statements = (List<Statement>) model.listStatements(null, oldProperty, (RDFNode) null).toList();

        for(Statement statement : statements){
            model.add(statement.getSubject(), newProperty, statement.getObject());
            model.remove(statement);
        }
        List<Statement> schema = model.listStatements(oldProperty, null, (RDFNode) null).toList();
        for(Statement s : schema){
            model.add(newProperty, s.getPredicate(), s.getObject());
            model.remove(s);
        }
        System.out.println("Property was changed: " + oldName + " -> " + newName);
    }


}