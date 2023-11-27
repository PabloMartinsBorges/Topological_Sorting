package br.unirio;

import java.io.*;
import java.util.*;

public class GrafoRandomico {
    public int vertices;
    public int edges;



    Random random = new Random();
    // An adjacency list to represent a graph
    public OrdenacaoTopologica lista;

    // Creating the constructor
    public GrafoRandomico(int vertices)  {

        // Set a maximum limit for the
        // number of vertices say 20
        this.vertices = vertices;

        // compute the maximum possible number of edges
        // and randomly choose the number of edges less than
        // or equal to the maximum number of possible edges
        this.edges = (vertices * (vertices - 1)) / 2;

        // Creating an adjacency list
        // representation for the random graph
        lista = new OrdenacaoTopologica();
        String[] grafoString = new String[edges];

        // A for loop to randomly generate edges
        for (int i = 0; i < edges; i++) {
            // Randomly select two vertices to
            // create an edge between them
            int v = random.nextInt(vertices);
            int w = random.nextInt(vertices);

            String novaAresta = v + " < " + w;

            boolean contemAresta = false;

            for(String aresta : grafoString){
                if(aresta == novaAresta){
                    contemAresta = true;
                    break;
                }
            }

            // Check if there is already an edge between v
            // and w
            if ((v == w) || contemAresta) {
                // Reduce the value of i
                // so that again v and w can be chosen
                // for the same edge count
                i = i - 1;
                continue;
            }
            else {
                grafoString[i] = novaAresta;
            }

        }

        for(String arestas : grafoString){
            System.out.println(arestas);
        }

    }

}
