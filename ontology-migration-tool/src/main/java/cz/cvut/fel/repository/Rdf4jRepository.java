package cz.cvut.fel.repository;

import org.eclipse.rdf4j.query.Update;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.http.HTTPRepository;


public class Rdf4jRepository implements OntologyRepository {
    private final Repository repo;
    private RepositoryConnection conn;

    public Rdf4jRepository(Repository repo) {
        this.repo = repo;
    }

    @Override
    public void begin(){
        conn = repo.getConnection();
        conn.begin();
    }

    @Override
    public void update(String sparql) {
        Update upd = conn.prepareUpdate(sparql);
        upd.execute();
    }

    @Override
    public void commit() {
        conn.commit();
        conn.close();
    }

    public RepositoryConnection getConnection() {
        return repo.getConnection();
    }




}
