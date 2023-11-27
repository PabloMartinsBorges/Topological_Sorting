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

		public void insereInicio(Elo elemento){
            if (id != null) {
                EloSuc eloTemp = new EloSuc();
                eloTemp.id = id;
                this.prox = eloTemp;
            }
            id = elemento;
        }
		public EloSuc remove(EloSuc eloSuc, int elem )
		{

			EloSuc p;
			EloSuc ant = null;

			for( p = eloSuc; ((p.id != null) && (p.id.chave != elem)); p = p.prox)
				ant = p;


			if (p.id == null)
				return null;

			if (p.id == eloSuc.id)
				eloSuc = p.prox;
			else
				ant.prox = p.prox;



			return eloSuc;
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

	public void insereInicio(int elemento){
		Elo p = new Elo();
		p.chave = elemento;
        if (prim != null) {
            prim.prox = prim;
        }
        prim = p;
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

				if(busca(elemX) == null){
					insereFim(elemX);
				}
				if(busca(elemY) == null){
					insereFim(elemY);
				}
				Elo eloX = busca(elemX);
				Elo eloY = busca(elemY);
				if(eloX.listaSuc == null){
					EloSuc novoSuc = new EloSuc();
					novoSuc.id = eloY;
					eloX.listaSuc = novoSuc;
				}
				else{
					eloX.listaSuc.insereInicio(eloY);
				}
				eloY.contador++;
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
					t = t.remove(t, t.id.chave);
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
		debug();
		gerarListaSemPredecessores();
		debug();
		gerarSequenciaDeSaida();
		if(n != 0){
			System.out.println(n);
			return false;
		}
		debug();



		return true;
	}
}