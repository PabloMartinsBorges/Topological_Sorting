package br.unirio;

public class Main
{
	public static void main(String args[])
	{

		int[] numVertices = {  10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 200, 300, 400, 500, 1000 };

		GrafoRandomico[] grafos = new GrafoRandomico[numVertices.length];

		for (int i = 0; i < grafos.length; i++){
			grafos[i] = new GrafoRandomico(numVertices[i]);
			System.out.println("Grafo com " + numVertices[i] + " número de vértices criado!");
		}

		CriarRegistro registro = new CriarRegistro("Ordenacao_Topologica/registroTopologico.csv");

		for (int i = 0; i < grafos.length; i++){
			long mediaTempo = 0;
			for (int j = 1; j <= 10; j++){
				long t0 = System.currentTimeMillis();

				if(grafos[i].executa())
					System.out.println("O conjunto é parcialmente ordenado.");
				else
					System.out.println("O conjunto não é parcialmente ordenado.");

				long t1 = System.currentTimeMillis();

				long tempoProcessamento = t1 - t0;

				System.out.println("Tempo: " + tempoProcessamento + " || número de vértices: " + numVertices[i]);

				mediaTempo += tempoProcessamento;
			}
			mediaTempo = mediaTempo/10;
			registro.RegistrarTempo(numVertices[i], mediaTempo, mediaTempo/1000);
		}

	}
}
