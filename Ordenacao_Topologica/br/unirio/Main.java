package br.unirio;

public class Main
{
	public static void main(String args[])
	{
		OrdenacaoTopologica ord = new OrdenacaoTopologica();

		String nomeEntrada = "Ordenacao_Topologica/entrada.txt";

		ord.realizaLeitura(nomeEntrada);

//		GrafoRandomico grafoRandomico = new GrafoRandomico(5);

		if(ord.executa())
			System.out.println("O conjunto � parcialmente ordenado.");
		else
			System.out.println("O conjunto n�o � parcialmente ordenado.");
	}
}
