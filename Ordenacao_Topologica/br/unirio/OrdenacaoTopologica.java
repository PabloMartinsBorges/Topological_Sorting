package br.unirio;

import java.io.*;
import java.util.Scanner;

public class OrdenacaoTopologica
{
	private class Elo
	{
		/* Identificação do elemento. */
		public int chave;
		
		/* Número de predecessores. */
		public int contador;
		
		/* Aponta para o próximo elo da lista. */
		public Elo prox;
		
		/* Aponta para o primeiro elemento da lista de sucessores. */
		public EloSuc listaSuc;
		
		public Elo()
		{
			prox = null;
			contador = 0;
			listaSuc = null;
		}
		
		public Elo(int chave, int contador, Elo prox, EloSuc listaSuc)
		{
			this.chave = chave;
			this.contador = contador;
			this.prox = prox;
			this.listaSuc = listaSuc;
		}

		protected void insereSucInicio( Elo elemento ){
			EloSuc novoElo = new EloSuc();
			novoElo.id = elemento;
			novoElo.prox = listaSuc;
			listaSuc = novoElo;
		}

		protected boolean removeSuc( int elem )
		{

			EloSuc p;
			EloSuc ant = null;

			for( p = listaSuc; ((p.id != null) && (p.id.chave != elem)); p = p.prox)
				ant = p;


			if (p.id == null)
				return false;

			if (p.id == listaSuc.id)
				listaSuc = p.prox;
			else
				ant.prox = p.prox;



			return true;
		}
	}
	
	private class EloSuc
	{
		/* Aponta para o elo que é sucessor. */
		public Elo id;
		
		/* Aponta para o próximo elemento. */
		public EloSuc prox;
		
		public EloSuc()
		{
			id = null;
			prox = null;
		}
		
		public EloSuc(Elo id, EloSuc prox)
		{
			this.id = id;
			this.prox = prox;
		}


	}


	/* Ponteiro (referência) para primeiro elemento da lista. */
	private Elo prim;
	
	/* Número de elementos na lista. */
	private int n;
		
	public OrdenacaoTopologica()
	{
		prim = null;
		n = 0;
	}

	public Elo busca(int elemento){

		Elo p;

		for(p = prim; p != null; p = p.prox)
		{
			if(p.chave == elemento)
				return p;
		}

		return null;

	}

	public void insereFim(int elemento){
		Elo q = new Elo();
		q.chave = elemento;
		if(prim == null){
			prim = q;
		}
		else{
			Elo p = prim;
			while(p.prox != null){
				p = p.prox;
			}
			p.prox = q;
		}
		this.n++;
	}

	public boolean remove(int elem)
	{

		Elo p;
		Elo ant = null;

		for( p = prim; ((p != null) && (p.chave != elem)); p = p.prox)
			ant = p;


		if (p == null)
			return false;

		if (p == prim)
			prim = prim.prox;
		else
			ant.prox = p.prox;

		p = null;

		return true;
	}

	public void adicionarAresta(int x, int y){
		if(busca(x) == null){
			insereFim(x);
		}
		if(busca(y) == null){
			insereFim(y);
		}
		Elo eloX = busca(x);
		Elo eloY = busca(y);
		eloX.insereSucInicio(eloY);
		eloY.contador++;
	}

	private boolean geraCiclo(int visitado, int buscado, boolean visitados[], boolean temCiclo ){

		if(buscado == visitado){
			return true;
		}
		Elo eloVisitado = busca(visitado);
		Elo eloBuscado = busca(buscado);
		if(eloVisitado == null || eloBuscado == null){
			return false;
		}
		visitados[eloVisitado.chave] = true;
		if(!temCiclo ){
			EloSuc eloSucTemp = eloVisitado.listaSuc;
			while (eloSucTemp != null){
				if(!visitados[eloSucTemp.id.chave]){
					temCiclo = geraCiclo(eloSucTemp.id.chave, buscado, visitados, false);
					if(temCiclo){
						break;
					}
				}
				eloSucTemp = eloSucTemp.prox;
			}
		}
		visitados[eloVisitado.chave] = false;
		return temCiclo;
	}

	protected boolean geraCiclo( int eloVisitado, int eloBuscado, int v ){
		boolean[] visitados = new boolean[v];
		return geraCiclo(eloVisitado, eloBuscado, visitados, false);
	}

	protected boolean geraCiclo( int eloVisitado, int eloBuscado ){
		boolean[] visitados = new boolean[Math.max(eloBuscado, eloVisitado)];
		return geraCiclo(eloVisitado, eloBuscado, visitados, false);
	}



	
	/* Método responsável pela leitura do arquivo de entrada. */
	public void realizaLeitura(String nomeEntrada){
		try{

			File arquivo = new File(nomeEntrada);
			FileReader fr = new FileReader( arquivo );
			Scanner scanner = new Scanner(fr);
			while (scanner.hasNextLine()){

				String[] entrada = scanner.nextLine().replace(" ", "").split("<");
				int elemX = Integer.parseInt(entrada[0]);
				int elemY = Integer.parseInt(entrada[1]);

				if(!geraCiclo(elemY, elemX ))
					adicionarAresta(elemX, elemY);

			}



		}catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado!");
		}
	}

    private void gerarListaSemPredecessores(){
        Elo p = prim;
        Elo q = new Elo();
        prim = null;
        while(p != null){
            q = p;
            p = q.prox;
            if(q.contador == 0){
                q.prox = prim;
                prim = q;
            }
        }
    }

    private void gerarSequenciaDeSaida(){
		Elo q = prim;
		while (q != null){
			System.out.print(q.chave + " ");
			EloSuc t = q.listaSuc;
			n--;
			while (t != null){
				t.id.contador--;
				if(t.id.contador == 0){
					Elo novoElo = new Elo(t.id.chave, 0, null, t.id.listaSuc);
					insereFim(novoElo);
					q.removeSuc(t.id.chave);
				}
				else {
					t = t.prox;
				}
			}
			remove(q.chave);
			q = q.prox;
		}
		System.out.println();
    }


	public void insereFim(Elo q){
		if(prim == null){
			prim = q;
		}
		else{
			Elo p = prim;
			while(p.prox != null){
				p = p.prox;
			}
			p.prox = q;
		}
	}


	/* Método para impressão do estado atual da estrutura de dados. */
	private void debug()
	{
		System.out.println("Debug");
		imprimePredecessores(prim);

	}

	private void imprimePredecessores(Elo raiz){
		String sucessores = "";
		if(raiz == null)
			return ;
		System.out.println(raiz.chave + " predecessores: " + raiz.contador + ", sucessores: " + imprimeSucessores(raiz.listaSuc, sucessores));
		imprimePredecessores(raiz.prox);
	}

	private String imprimeSucessores(EloSuc raiz, String sucessores){
		if(raiz == null)
			return sucessores + "NULL";
		return  raiz.id.chave + "->" + imprimeSucessores(raiz.prox, sucessores);
	}

	/* Método responsável por executar o algoritmo. */
	public boolean executa()
	{
//		realizaLeitura("Ordenacao_Topologica/entrada1.txt");
		gerarListaSemPredecessores();
		gerarSequenciaDeSaida();
		if(n != 0){
			System.out.println(n + " //");
			return false;
		}

		return true;
	}
}