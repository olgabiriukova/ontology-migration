package cz.cvut.fel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RenamePropertyStep.class, name = "renameProperty"),
})
public abstract class Step {
    public String getType() {
        JsonTypeName annotation = this.getClass().getAnnotation(JsonTypeName.class);
        if (annotation != null) {
            return annotation.value();
        }
        return this.getClass().getSimpleName();
    }
}