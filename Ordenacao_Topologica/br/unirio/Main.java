package br.unirio;

public class Main
{
	public static void main(String args[])
	{


		OrdenacaoTopologica or = new OrdenacaoTopologica();
		GrafoRandomico grafoRandomico = new GrafoRandomico(100);

		if(grafoRandomico.executa())
			System.out.println("O conjunto � parcialmente ordenado.");
		else
			System.out.println("O conjunto n�o � parcialmente ordenado.");


	}
}
