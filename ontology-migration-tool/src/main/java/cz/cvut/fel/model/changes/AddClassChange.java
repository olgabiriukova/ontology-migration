package cz.cvut.fel.model.changes;

import cz.cvut.fel.fuseki.FusekiRepository;

public class AddClassChange extends Change{
    private String uri;
    private String label;

    public AddClassChange(String uri, String label) {
        this.uri = uri;
        this.label = label;
    }
    public AddClassChange(){}

    /*
    @Override
    public void apply(Model model) {
        Resource newClass = model.createResource(uri);
        newClass.addProperty(RDFS.label, label!= null ? label : uri);
        newClass.addProperty(RDF.type, RDFS.Class);

        System.out.println("Added new class: " + uri);
    }*/

    @Override
    public void apply(FusekiRepository repository) {

    }
}
