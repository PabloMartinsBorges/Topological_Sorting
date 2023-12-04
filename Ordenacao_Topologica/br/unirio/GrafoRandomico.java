package br.unirio;


import java.util.Random;

public class GrafoRandomico implements Cloneable {

    private int numVertices;
    private int numArestas;
    private OrdenacaoTopologica listaAdjacencia;
    private Random random = new Random();

    public GrafoRandomico(int numVertices) {
        this.numVertices = numVertices;
        this.numArestas = calculaQuantidadeAresta();
        this.listaAdjacencia = new OrdenacaoTopologica();
        gerarArestasAleatorias();
    }


    public void gerarArestasAleatorias(){
        int v = 0;
        for (int i = 0; i < numArestas ; i++) {

            if(v == numVertices){
                v = 0;
            }
            int a = random.nextInt(numVertices);

            if (v == a || !listaAdjacencia.geraArestaPossivel(v, a, numVertices)) {
                i--;
            } else {
                listaAdjacencia.adicionarAresta(v, a);
            }
            v++;
        }
    }

    private int calculaQuantidadeAresta() {
        int min = numVertices;
        double max = (((numVertices * (numVertices - 1)) / 2)  * 0.01) + min;
        double intervalo = max - min + 1;
        return (int) (Math.random() * intervalo) + min;
    }

    public boolean executa() {
        return listaAdjacencia.executa();
    }

    @Override
    public GrafoRandomico clone() {
        try {
            GrafoRandomico clone = (GrafoRandomico) super.clone();
            clone.listaAdjacencia = this.listaAdjacencia.reconstroi();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }


}