package br.unirio;


import java.util.Random;

public class GrafoRandomico {

    private int vertices;
    private float probabilidade;
    private Random random = new Random();
    private OrdenacaoTopologica listaAdjacencia;

    public GrafoRandomico(int vertices){
        this.vertices = vertices;
        this.probabilidade = random.nextFloat();
        this.listaAdjacencia = new OrdenacaoTopologica();

        for(int i = 0; i < vertices; i++){
            for(int j = 0; j < vertices; j++){
                float probabilidadeAresta = random.nextFloat();
                if(probabilidadeAresta < probabilidade){

                    if(i != j && !listaAdjacencia.geraCiclo(j, i, vertices)){
                        listaAdjacencia.adicionarAresta(i, j);
                    }
                }
            }
        }

    }

    public boolean executa(){
        return listaAdjacencia.executa();
    }



}