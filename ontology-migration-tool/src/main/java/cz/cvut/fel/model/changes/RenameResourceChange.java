package cz.cvut.fel.model.changes;

import cz.cvut.fel.fuseki.FusekiRepository;


public class RenameResourceChange extends Change{
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
    public String apply(FusekiRepository repository) {
        String sparqlProperty = String.format("""
                DELETE { ?s <%s> ?o }
                INSERT { ?s <%s> ?o }
                WHERE  { ?s <%s> ?o }
                """, oldName, newName, oldName);
        //repository.update(sparqlProperty);
        String sparqlSubject = String.format("""
                DELETE { <%s> ?p ?o}
                INSERT { <%s> ?p ?o }
                WHERE { <%s> ?p ?o }
                """, oldName, newName, oldName);
        //repository.update(sparqlSubject);
        String sparqlType = String.format("""
                PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>
                DELETE { ?s rdf:type <%s>}
                INSERT { ?s rdf:type <%s>}
                WHERE { ?s rdf:type <%s> }
                """, oldName, newName, oldName);
        //repository.update(sparqlType);
        String sparqlObject = String.format("""
                DELETE { ?s ?p <%s> }
                INSERT { ?s ?p <%s> }
                WHERE  { ?s ?p <%s> }
                """, oldName, newName, oldName);
        //repository.update(sparqlObject);
        String transaction = sparqlProperty + ";\n" +
                sparqlSubject + ";\n" +
                sparqlType + ";\n" +
                sparqlObject;
        System.out.println("Resource was renamed " + oldName + " -> " + newName);
        return transaction;
    }
}
