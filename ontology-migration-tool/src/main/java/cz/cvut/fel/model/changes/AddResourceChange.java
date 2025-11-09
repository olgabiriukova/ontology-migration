package cz.cvut.fel.model.changes;

import cz.cvut.fel.fuseki.FusekiRepository;

public class AddResourceChange extends Change{
    private String uri;
    private String classUri;
    private String label;

    public AddResourceChange(String uri, String classUri, String label) {
        this.uri = uri;
        this.classUri = classUri;
        this.label = label;
    }
    public AddResourceChange(){}

    /*
    @Override
    public void apply(Model model) {
        Resource resource = model.createResource(uri);
        if(classUri != null){
            resource.addProperty(model.createProperty("http://www.w3.org/1999/02/22-rdf-syntax-ns#type"),
                    model.createResource(classUri));
        }
         if(label != null){
             resource.addProperty(model.createProperty("http://www.w3.org/2000/01/rdf-schema#label"), label);
         }

        System.out.println("Resource added:  " + uri);
    }*/

    @Override
    public void apply(FusekiRepository repository) {

    }
}
