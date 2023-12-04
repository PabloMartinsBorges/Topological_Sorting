package br.unirio;

public class Main {
    public static void main(String args[]) {

        int[] numVertices = { 30000, 50000, 100000};

        GrafoRandomico[] grafos = new GrafoRandomico[numVertices.length];

        for (int i = 0; i < grafos.length; i++) {
            grafos[i] = new GrafoRandomico(numVertices[i]);
            System.out.println("Grafo com " + numVertices[i] + " número de vértices criado!");
        }

        CriarRegistro registro = new CriarRegistro("Ordenacao_Topologica/registroTopologico.csv");

        for (int i = 0; i < grafos.length; i++) {
            long mediaTempo = 0;
            for (int j = 1; j <= 10; j++) {

                GrafoRandomico grafoTeste = grafos[i].clone();

                long t0 = System.currentTimeMillis();

                if (grafoTeste.executa())
                    System.out.println("O conjunto é parcialmente ordenado.");
                else
                    System.out.println("O conjunto não é parcialmente ordenado.");

                long t1 = System.currentTimeMillis();

                long tempoProcessamento = t1 - t0;

                System.out.println("Tempo: " + tempoProcessamento + " || número de vértices: " + numVertices[i]);

                mediaTempo += tempoProcessamento;
            }
            mediaTempo = mediaTempo/10;
            double mediaTempoFrac = (double) mediaTempo / 10;
            registro.RegistrarTempo(numVertices[i], mediaTempoFrac, mediaTempo / 1000);
        }
    }
}
