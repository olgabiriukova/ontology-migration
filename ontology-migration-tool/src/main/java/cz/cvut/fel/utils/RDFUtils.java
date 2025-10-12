package cz.cvut.fel.utils;

public class RDFUtils {
    public static String getRDFFormat(String filename) {
        if (filename.endsWith(".ttl")) {
            return "TTL";
        } else if (filename.endsWith(".rdf") || filename.endsWith(".xml")) {
            return "RDF/XML";
        } else {
            throw new IllegalArgumentException("Unknown RDF file extension: " + filename);
        }
    }
}
