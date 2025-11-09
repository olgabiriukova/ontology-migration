package cz.cvut.fel.model.changes;

import cz.cvut.fel.fuseki.FusekiRepository;

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
    public void apply(FusekiRepository repo) {
        String sparql = String.format("""
                DELETE { ?s <%s> ?o }
                INSERT { ?s <%s> ?o }
                WHERE  { ?s <%s> ?o }
                """, oldName, newName, oldName);
        repo.update(sparql);
        String updateMetaSparql = String.format("""
                DELETE { <%s> ?p ?o}
                INSERT { <%s> ?p ?o }
                WHERE { <%s> ?p ?o }
                """, oldName, newName, oldName);
        repo.update(updateMetaSparql);
        System.out.println("Property was changed: " + oldName + " -> " + newName);
    }




    /*
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
    }*/
}