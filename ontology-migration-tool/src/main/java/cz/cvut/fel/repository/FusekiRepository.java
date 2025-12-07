package cz.cvut.fel.repository;

import org.apache.jena.ontology.Ontology;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;

public class FusekiRepository implements OntologyRepository {
    private final String endpoint;
    private final String user;
    private final String password;
    private RDFConnection conn;
    private StringBuilder transaction;

    public FusekiRepository(String endpoint, String user, String password) {
        this.endpoint = endpoint;
        this.user = user;
        this.password = password;
    }

    @Override
    public void begin() {
        transaction = new StringBuilder();
        this.conn = RDFConnectionFactory.connectPW(endpoint, user, password);
    }

    @Override
    public void update(String sparql){
       if(conn == null){
           throw new IllegalStateException("Connection failed");
       }
       transaction.append(sparql).append("\n");
    }

    @Override
    public void commit() {
        if(transaction!=null && transaction.length() > 0){
            conn.update(transaction.toString());
        }
        if(conn!=null){
            conn.close();  //???
            conn = null;
        }
        transaction = null;
    }

    @Override
    public boolean ask(String sparql){
        try(RDFConnection qConn = RDFConnectionFactory.connectPW(endpoint, user, password)){
            return qConn.queryAsk(sparql);
        }
    }
}
