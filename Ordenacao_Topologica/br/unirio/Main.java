package br.unirio;

public class Main
{
	public static void main(String args[])
	{

		GrafoRandomico grafo = new GrafoRandomico();

		OrdenacaoTopologica or = new OrdenacaoTopologica();

		if(or.executa())
			System.out.println("O conjunto é parcialmente ordenado.");
		else
			System.out.println("O conjunto não é parcialmente ordenado.");


	}
}
