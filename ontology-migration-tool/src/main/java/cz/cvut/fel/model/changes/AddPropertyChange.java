package cz.cvut.fel.model.changes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import cz.cvut.fel.fuseki.FusekiRepository;

public class AddPropertyChange extends Change {
    @JsonProperty("propertyURI")
    private String propertyURI;
    @JsonProperty("objectURI")
    private String objectURI;
    @JsonProperty("subjectURI")
    private String subjectURI;

    public AddPropertyChange(){}

    public AddPropertyChange(String propertyURI, String objectURI, String subjectURI) {
        this.propertyURI = propertyURI;
        this.objectURI = objectURI;
        this.subjectURI = subjectURI;
    }

    @Override
    public void apply(FusekiRepository repository) { //TODO переписать
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT DATA { ");
        if(subjectURI != null && propertyURI != null && objectURI != null){
            sb.append(String.format("<%s> <%s> <%s> . ", subjectURI, propertyURI, objectURI));
        }
        sb.append(" }");
        repository.update(sb.toString());
        System.out.println("Property added: " + propertyURI);
    }
}
