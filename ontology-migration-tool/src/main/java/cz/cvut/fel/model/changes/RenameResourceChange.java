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
        String withGraph = "";
        if(graph!=null && !graph.isBlank()){
            withGraph ="WITH <" + graph + "> ";
        }
        String sparqlProperty = withGraph + String.format("""
                DELETE { ?s <%s> ?o }
                INSERT { ?s <%s> ?o }
                WHERE  { ?s <%s> ?o }
                """, oldName, newName, oldName);
        //repository.update(sparqlProperty);
        String sparqlSubject = withGraph + String.format("""
                DELETE { <%s> ?p ?o}
                INSERT { <%s> ?p ?o }
                WHERE { <%s> ?p ?o }
                """, oldName, newName, oldName);
        //repository.update(sparqlSubject);
        String sparqlType = withGraph + String.format("""
                DELETE { ?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <%s>}
                INSERT { ?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <%s>}
                WHERE { ?s <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <%s> }
                """, oldName, newName, oldName);
        //repository.update(sparqlType);
        String sparqlObject = withGraph + String.format("""
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
