package cz.cvut.fel.model.changes;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;

public class DeletePropertyChange extends Change{
    private String uri;
    public DeletePropertyChange(String uri) {
        this.uri = uri;
    }

    public DeletePropertyChange(){}

    @Override
    public void apply(Model model) {
        Property property = model.getProperty(uri);
        if(property != null){
            System.out.println("Property "+uri+" not found!");
            return;
        }
        model.listStatements(null, property, (RDFNode) null)
                .forEachRemaining(model::remove);

        //delete property like resource??
        /*
        model.listStatements(property, null, (RDFNode) null)
                .forEachRemaining(model::remove);
         */

        System.out.println("Property "+uri+" removed!");

    }
}
