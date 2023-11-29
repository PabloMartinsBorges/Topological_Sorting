package br.unirio;

public class Main
{
	public static void main(String args[])
	{


		OrdenacaoTopologica or = new OrdenacaoTopologica();
		GrafoRandomico grafoRandomico = new GrafoRandomico(100);

		if(grafoRandomico.executa())
			System.out.println("O conjunto é parcialmente ordenado.");
		else
			System.out.println("O conjunto não é parcialmente ordenado.");


	}
}
