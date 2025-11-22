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
    public void apply(FusekiRepository repository) {
        try{
            repository.update(query);
            System.out.println("Updated query: " + query);
        }catch(Exception e){
            System.err.println("Error updating query: " + query);
            throw e;
        }

    }
}
