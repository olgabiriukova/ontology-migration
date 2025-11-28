package cz.cvut.fel.model.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.fel.repository.FusekiRepository;
import cz.cvut.fel.repository.OntologyRepository;

public class DeleteResourceChange extends Change{
    @JsonProperty("uri")
    private String uri;
    public DeleteResourceChange(String uri) {
        this.uri = uri;
    }
    public DeleteResourceChange(){}

    @Override
    public String apply(OntologyRepository repository) {
        if (graph != null && !graph.isBlank()) {
            return String.format(
                    "DELETE WHERE { GRAPH <%s> { <%s> ?p ?o } }; " +
                            "DELETE WHERE { GRAPH <%s> { ?s ?p <%s> } }",
                    graph, uri, graph, uri
            );
        } else {
            return String.format(
                    "DELETE WHERE { <%s> ?p ?o }; " +
                            "DELETE WHERE { ?s ?p <%s> }",
                    uri, uri
            );
        }
    }
}
