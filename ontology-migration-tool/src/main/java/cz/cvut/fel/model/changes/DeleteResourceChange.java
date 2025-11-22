package cz.cvut.fel.model.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.fel.fuseki.FusekiRepository;

import java.sql.SQLOutput;

public class DeleteResourceChange extends Change{
    @JsonProperty("uri")
    private String uri;
    public DeleteResourceChange(String uri) {
        this.uri = uri;
    }
    public DeleteResourceChange(){}

    @Override
    public void apply(FusekiRepository repository) {
        String query = String.format(
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                        "DELETE WHERE { <%1$s> ?p ?o. ?s ?p2 <%1$s>. ?instance rdf:type <%1$s>. }",
                uri
        );
        repository.update(query);
        System.out.println("Resource deleted: " + uri);
    }
}
