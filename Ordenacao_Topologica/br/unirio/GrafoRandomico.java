package br.unirio;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GrafoRandomico {

    private int vertices;
    private float probabilidade;
    private boolean[][] matrixAdjacencia;

    public GrafoRandomico() {

        this.vertices = 20;
        this.probabilidade = (float) Math.random();

        System.out.println("A probabilidade de haver uma aresta entre dois vértices é: " + probabilidade);

        matrixAdjacencia = new boolean[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            for (int j = i + 1; j < vertices; j++) {

                float probabilidadeAresta = (float) Math.random();

                if (probabilidadeAresta < probabilidade && !formaCiclo(i, j)) {
                    addAresta(i, j);
                }
            }
        }

        salvaGrafo();
    }

    private void addAresta(int v, int w) {
        matrixAdjacencia[v][w] = true;
    }

    private boolean formaCiclo(int v, int w) {
        boolean[] visitado = new boolean[vertices];
        return dfs(v, w, visitado);
    }

    private boolean dfs(int atual, int alvo, boolean[] visitado) {
        if (visitado[atual]) {
            return atual == alvo;
        }

        visitado[atual] = true;

        for (int i = 0; i < vertices; i++) {
            if (matrixAdjacencia[atual][i]) {
                if (dfs(i, alvo, visitado)) {
                    return true;
                }
            }
        }

        return false;
    }

    // Método para salvar o grafo em um arquivo
    private void salvaGrafo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Ordenacao_Topologica/entrada.txt"))) {
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (matrixAdjacencia[i][j]) {
                        writer.write(i + " < " + j);
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}