package br.unirio;

public class Main
{
	public static void main(String args[])
	{

		GrafoRandomico grafo = new GrafoRandomico();

		OrdenacaoTopologica or = new OrdenacaoTopologica();

		if(or.executa())
			System.out.println("O conjunto � parcialmente ordenado.");
		else
			System.out.println("O conjunto n�o � parcialmente ordenado.");


	}
}
