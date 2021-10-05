import java.util.*;
import java.io.FileReader;
import java.io.BufferedReader;

public class Graph {

    // vertex set
    protected HashMap< Integer, Vertex> vertex_set;

    public Graph() {
        vertex_set = new HashMap<>();
    }

    public void print() {
        for( Vertex v : vertex_set.values())
            v.print();

        System.out.println("");
    }

    public void add_vertex( int id ) {
        Vertex v = new Vertex( id );
        vertex_set.put( v.id, v );
    }

    public void add_edge( Integer id1, Integer id2) {
        Vertex v1 = vertex_set.get( id1 );
        Vertex v2 = vertex_set.get( id2 );

        if ( v1 == null || v2 == null ) {
            if (v1 == null) {
                add_vertex(id1);
                v1 = vertex_set.get( id1 );
            }
            if (v2 == null){
                add_vertex(id2);
                v2 = vertex_set.get( id2 );
            }
        }

        v1.add_neighbor( v2 );
        v2.add_neighbor( v1 );
    }

    // verify if is trivially perfect
    public boolean quasiThresholdRecognition() {
        // adds a universal vertex
        this.add_vertex(-1);
        Vertex universal = this.vertex_set.get(-1);
        universal.visit();

        // Create a list of vertices
        List<Vertex> lista = new ArrayList<>();

        // Add a edge to the universal vertex and set parent
        this.vertex_set.replaceAll((k, v) -> {
            if (v.id != -1) {
                this.add_edge(-1, v.id);
                v.setParent(-1);
                lista.add(v);
            }

            return v;
        });

        // Order the list by vertex degree decreasing
        Collections.sort(lista);

        // For every vertex on the list visit
        for (Vertex actual : lista) {
            actual.visit();

            // Se p(actual) != p(neig) the graph is not trivially perfect
            for (Vertex neig : actual.nbhood.values()) {
                if (!neig.isVisited() && neig.getParent() != actual.getParent()) {
                    return false;
                }
            }

            // set p(neig) = actual
            for (Vertex neig : actual.nbhood.values()) {
                if (!neig.isVisited()) {
                    neig.setParent(actual.id);
                }
            }
        }

        return true;
    }

}
