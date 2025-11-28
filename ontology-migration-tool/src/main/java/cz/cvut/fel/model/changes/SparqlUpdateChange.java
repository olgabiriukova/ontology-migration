package cz.cvut.fel.model.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.fel.repository.FusekiRepository;
import cz.cvut.fel.repository.OntologyRepository;

public class SparqlUpdateChange extends Change{
    @JsonProperty("query")
    private String query;
    public SparqlUpdateChange(String query) {
        this.query = query;
    }

    public SparqlUpdateChange() {}

    @Override
    public String apply(OntologyRepository repository) { //sparql must be without with <graph>
        System.out.println("Updating " + query);
        return query;
    }
}
