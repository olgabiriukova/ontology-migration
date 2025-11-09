package cz.cvut.fel.model.changes;

import cz.cvut.fel.fuseki.FusekiRepository;

public class DeleteClassChange extends Change{
    private String uri;
    public DeleteClassChange(String uri) {
        this.uri = uri;
    }
    public DeleteClassChange(){}

    /*
    @Override
    public void apply(Model model) {
        Resource cls = model.getResource(uri);
        if(cls != null){
            System.out.println(cls.getURI()+ "not found!");
        }
        model.listStatements(null, RDF.type, cls)
                .forEachRemaining(model::remove);

        cls.listProperties().forEachRemaining(stmt -> model.remove(stmt));
        model.listStatements(cls, null, (RDFNode) null)
                .forEachRemaining(model::remove);
        System.out.println(uri + "removed!");

    }*/

    @Override
    public void apply(FusekiRepository repository) {

    }
}
