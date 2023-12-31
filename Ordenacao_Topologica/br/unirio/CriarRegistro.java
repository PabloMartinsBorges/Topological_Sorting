package br.unirio;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CriarRegistro {

    private String url;

    public CriarRegistro() {
        url = "Ordenacao_Topologica/registroTopologico.csv";
    }

    public CriarRegistro(String endereco) {
        url = endereco;
    }

    public void RegistrarTempo(int nVertices, double tempoMili, int nArestas) {
        CriarArquivo(url);

        try {
            List<String> existentes = LinhaExistentes(url);
            String coluna = "Numero de Vertices" + ";" + "Tempo Medio (milissegundos) " + ";" + "Numero de Arestas" + ";";
            existentes.add(coluna);
            String registro = nVertices + ";" + tempoMili + ";" + nArestas + ";";
            existentes.add(registro);
            // adicionar no csv
            String todasLinhas = UnicaLinha(existentes);
            FileWriter arquivo = new FileWriter(url);
            arquivo.write(todasLinhas);
            arquivo.close();

        } catch (Exception error) {
            System.out.println("Erro ao gerar o arquivo " + url);
        }
    }


    public void RegistrarTempo(int nVertices, long tempo) {
        CriarArquivo(url);

        try {
            List<String> existentes = LinhaExistentes(url);
            String coluna = "Numero de Grafos" + ";" + "Tempo" + ";";
            existentes.add(coluna);
            String registro = nVertices + ";" + tempo + ";";
            existentes.add(registro);
            // adicionar no csv
            String todasLinhas = UnicaLinha(existentes);
            FileWriter arquivo = new FileWriter(url);
            arquivo.write(todasLinhas);
            arquivo.close();

        } catch (Exception error) {
            System.out.println("Erro ao gerar o arquivo " + url);
        }
    }

    public String UnicaLinha(List<String> existentes) {

        String unicaLinha = "";
        for (String linha : existentes) {
            unicaLinha += linha + " \n";
        }
        return unicaLinha;
    }

    public void CriarArquivo(String url) {

        try {
            File arquivo = new File(url);
            arquivo.createNewFile();
        } catch (Exception erro) {
            System.out.println("Erro ao criar arquivo.");
        }
    }

    public List<String> LinhaExistentes() {

        List<String> result = new ArrayList<String>();

        try {
            Path path = Paths.get(url);
            result = Files.readAllLines(path);
        } catch (Exception erro) {
            System.out.println("Erro ao ler o arquivo.");
        }

        return result;
    }


    public List<String> LinhaExistentes(String caminho) {

        List<String> result = new ArrayList<String>();

        try {
            Path path = Paths.get(caminho);
            result = Files.readAllLines(path);
        } catch (Exception erro) {
            System.out.println("Erro ao ler o arquivo.");
        }

        return result;
    }
}