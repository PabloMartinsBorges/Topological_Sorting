package br.unirio;

public class Main {
    public static void main(String args[]) {

        int[] numVertices = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 500, 1000, 5000, 10000, 20000, 30000};

        GrafoRandomico[] grafos = new GrafoRandomico[numVertices.length];

        for (int i = 0; i < grafos.length; i++) {
            grafos[i] = new GrafoRandomico(numVertices[i]);
            System.out.println("Grafo com " + numVertices[i] + " n�mero de v�rtices criado, com " + grafos[i].getNumArestas() + " Arestas!");
        }

        CriarRegistro registro = new CriarRegistro("Ordenacao_Topologica/registroTopologico.csv");

        for (int i = 0; i < grafos.length; i++) {
            long mediaTempo = 0;
            for (int j = 1; j <= 10; j++) {

                GrafoRandomico grafoTeste = grafos[i].clone();

                long t0 = System.currentTimeMillis();

                if (grafoTeste.executa())
                    System.out.println("O conjunto � parcialmente ordenado.");
                else
                    System.out.println("O conjunto n�o � parcialmente ordenado.");

                long t1 = System.currentTimeMillis();

                long tempoProcessamento = t1 - t0;

                System.out.println("Tempo: " + tempoProcessamento + " || n�mero de v�rtices: " + numVertices[i]);

                mediaTempo += tempoProcessamento;
            }
            double mediaTempoFrac = (double) mediaTempo / 10;
            registro.RegistrarTempo(numVertices[i], mediaTempoFrac, grafos[i].getNumArestas());
        }
    }
}
