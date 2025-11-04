package cz.cvut.fel.model.changes;

import org.apache.jena.query.Dataset;
import org.apache.jena.query.DatasetFactory;
import org.apache.jena.query.ReadWrite;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.update.*;

public class SparqlUpdateChange extends Change{
    private final String query;
    public SparqlUpdateChange(String query) {
        this.query = query;
    }

    @Override
    public void apply(Model model) {
        Dataset dataset = DatasetFactory.create(model);
        dataset.begin(ReadWrite.WRITE);
        try{
            UpdateRequest request = UpdateFactory.create(query);
            UpdateProcessor processor = UpdateExecutionFactory.create(request, dataset);
            processor.execute();
            dataset.commit();
            System.out.println("Updated " + query);
        }catch (Exception e) {
            dataset.abort();
            e.printStackTrace();
        }finally{
            dataset.end();
        }
    }
}
