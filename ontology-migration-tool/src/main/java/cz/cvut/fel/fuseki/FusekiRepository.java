package cz.cvut.fel.fuseki;

import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;

public class FusekiRepository {
    private final String endpoint;
    private final String user;
    private final String password;

    public FusekiRepository(String endpoint, String user, String password) {
        this.endpoint = endpoint;
        this.user = user;
        this.password = password;
    }

    private RDFConnection createConnection() {
        return RDFConnectionFactory.connectPW(endpoint, user, password);
    }

    public void update(String sparql){
        try(RDFConnection conn = createConnection()) {
            conn.update(sparql);
        }

    }
}
