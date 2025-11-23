package cz.cvut.fel.model.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.fel.fuseki.FusekiRepository;

public class AddResourceChange extends Change{
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("classUri")
    private String classUri;
    @JsonProperty("label")
    private String label;

    public AddResourceChange(String uri, String classUri, String label) {
        this.uri = uri;
        this.classUri = classUri;
        this.label = label;
    }
    public AddResourceChange(){}

    @Override
    public String apply(FusekiRepository repository) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT DATA { ");
        if (classUri != null) {
            sb.append(String.format("<%s> <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <%s> . ", uri, classUri));
        }
        if (label != null) {
            sb.append(String.format("<%s> <http://www.w3.org/2000/01/rdf-schema#label> \"%s\" . ", uri, label));
        }
        sb.append(" }");
        //repository.update(sb.toString());
        System.out.println("Resource added: " + uri);
        return sb.toString();
    }
}
