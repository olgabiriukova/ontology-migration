package cz.cvut.fel.model.changes;

import cz.cvut.fel.fuseki.FusekiRepository;

public class DeletePropertyChange extends Change{
    private String uri;
    public DeletePropertyChange(String uri) {
        this.uri = uri;
    }

    public DeletePropertyChange(){}

    /*
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


        System.out.println("Property "+uri+" removed!");

    }*/

    @Override
    public void apply(FusekiRepository repository) {

    }
}
