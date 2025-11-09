package cz.cvut.fel.model.changes;

import cz.cvut.fel.fuseki.FusekiRepository;

public class DeleteResourceChange extends Change{
    private String uri;
    public DeleteResourceChange(String uri) {
        this.uri = uri;
    }
    public DeleteResourceChange(){}

    /*
    @Override
    public void apply(Model model) {
        Resource resource = model.getResource(uri);
        if(resource != null){
            System.out.println("Resource "+uri+" not found!");
        }
        resource.listProperties().forEachRemaining(stmt->model.remove(stmt));
        model.listStatements(null,null,resource).forEachRemaining(stmt->model.remove(stmt));

        System.out.println("Resource "+uri+" removed!");

    }*/

    @Override
    public void apply(FusekiRepository repository) {

    }
}
