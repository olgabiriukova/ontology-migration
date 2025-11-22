package cz.cvut.fel.model.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.fel.fuseki.FusekiRepository;

public class AddClassChange extends Change{
    @JsonProperty("uri")
    private String uri;

    @JsonProperty("label")
    private String label;

    public AddClassChange(String uri, String label) {
        this.uri = uri;
        this.label = label;
    }
    public AddClassChange(){}

    @Override
    public void apply(FusekiRepository repository) {
        StringBuilder sb = new StringBuilder();
        sb.append("PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> ")
            .append("PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> ")
            .append("INSERT DATA { ")
            .append(String.format("<%s> rdf:type rdfs:Class . ", uri));
        if(label != null){
            sb.append(String.format("<%s> rdfs:label \"%s\" . ", uri, label));
        }
        sb.append("}");
        repository.update(sb.toString());
        System.out.println("Class added: " + uri);

    }
}
