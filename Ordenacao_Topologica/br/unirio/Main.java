package br.unirio;

public class Main
{
	public static void main(String args[])
	{

		int[] numVertices = {10, 20, 30, 40, 50, 100, 200,
				500, 1000, 5000, 10000, 20000, 30000, 50000, 100000};
		OrdenacaoTopologica or = new OrdenacaoTopologica();
		CriarRegistro registro = new CriarRegistro("Ordenacao_Topologica/registroTopologico.csv");

		for (int i = 0; i < numVertices.length; i++){
			long mediaTempo = 0;
			for (int j = 1; j <= 10; j++){
				long t0 = System.currentTimeMillis();

				GrafoRandomico grafoRandomico = new GrafoRandomico(numVertices[i]);

				if(grafoRandomico.executa())
					System.out.println("O conjunto é parcialmente ordenado.");
				else
					System.out.println("O conjunto não é parcialmente ordenado.");

				long t1 = System.currentTimeMillis();

				long tempoProcessamento = t1 - t0;

				System.out.println("Tempo: " + tempoProcessamento);

				mediaTempo += tempoProcessamento;
			}
			mediaTempo = mediaTempo/10;
			registro.RegistrarTempo(numVertices[i], mediaTempo, mediaTempo/1000);
		}


	}
}
