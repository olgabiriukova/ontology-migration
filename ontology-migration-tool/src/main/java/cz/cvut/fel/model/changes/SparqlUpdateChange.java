package cz.cvut.fel.model.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import cz.cvut.fel.fuseki.FusekiRepository;

public class SparqlUpdateChange extends Change{
    @JsonProperty("query")
    private String query;
    public SparqlUpdateChange(String query) {
        this.query = query;
    }

    public SparqlUpdateChange() {}

    @Override
    public String apply(FusekiRepository repository) { //sparql must be without with <graph>
        System.out.println("Updating " + query);
        return query;
    }
}
