package br.unirio;


import java.util.Random;

public class GrafoRandomico {

    private int numVertices;
    private int numArestas;
    private OrdenacaoTopologica listaAdjacencia;
    private Random random = new Random();

    public GrafoRandomico(int numVertices){
        this.numVertices = numVertices;
        this.numArestas = calculaQuantidadeAresta();
        this.listaAdjacencia = new OrdenacaoTopologica();

        for(int i = 0; i < numArestas; i++){
            int v = random.nextInt(numVertices);
            int a = random.nextInt(numVertices);
            if(v == a || listaAdjacencia.geraCiclo(a, v, numVertices) || listaAdjacencia.geraArestaDupla(v, a)){
                i--;
                continue;
            }
            listaAdjacencia.adicionarAresta(v, a);

        }



    }

    private int calculaQuantidadeAresta(){
        int max = (numVertices*(numVertices-1))/2;
        int min = numVertices;
        int intervalo = max - min + 1;
        return (int)(Math.random() * intervalo) + min;
    }

    public boolean executa(){
        return listaAdjacencia.executa();
    }



}