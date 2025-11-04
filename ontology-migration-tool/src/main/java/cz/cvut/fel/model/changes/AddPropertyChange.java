package cz.cvut.fel.model.changes;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

import java.awt.geom.RectangularShape;

public class AddPropertyChange extends Change {
    //TODO
    private final String propertyURI;
    private final String objectURI;
    private final String subjectURI;

    public AddPropertyChange(String propertyURI, String objectURI, String subjectURI) {
        this.propertyURI = propertyURI;
        this.objectURI = objectURI;
        this.subjectURI = subjectURI;
    }
    @Override
    public void apply(Model model) {
        Resource subject = model.getResource(subjectURI);
        Resource object = model.getResource(objectURI);
        if(subject == null){
            System.out.println("No such subject: " + subjectURI);
            return;
        }
        if(object == null){
            System.out.println("No such object: " + objectURI);
            return;
        }
        Property property = model.getProperty(propertyURI);
        if(property != null){
            System.out.println("Property already exists: " + propertyURI);
            return;
        }

        property = model.createProperty(propertyURI);
        subject.addProperty(property, object);
        System.out.println("Added property: " + propertyURI);

    }
}
