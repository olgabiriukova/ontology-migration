package cz.cvut.fel.model.changes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import org.apache.jena.rdf.model.Model;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RenamePropertyChange.class, name = "renameProperty"),
})
public abstract class Change {
    public String getType() {
        JsonTypeName annotation = this.getClass().getAnnotation(JsonTypeName.class);
        if (annotation != null) {
            return annotation.value();
        }
        return this.getClass().getSimpleName();
    }

    public abstract void apply(Model model);
}