package Main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.table.AbstractTableModel;

public abstract class Memoria {
	protected int espacoOcupado;
	protected int qtdProcessosNaoAlocados;
	protected int qtdProcessosCriados;
	protected int qtdProcessosRemovidos;
	protected int qtdProcessosAlocados;
	protected int enderecoDeBusca;
	protected LinkedList<Processo> mapaDeAlocacao ;
	protected static final int TAMANHO_DA_MEMORIA = 1000;
	protected int indice;
	protected boolean alocou = false;
	
	
	public Memoria() {
		this.espacoOcupado = 0;
		this.qtdProcessosNaoAlocados = 0;
		this.mapaDeAlocacao = new LinkedList<>();
		this.qtdProcessosCriados = 0;
		this.qtdProcessosRemovidos = 0;
		this.enderecoDeBusca = 0;
	}
	

	public void geradorDeProcesso () {
		Processo processo = new Processo();
		qtdProcessosCriados++;
		alocaNaMemoria(processo);
	}
	
	protected abstract void alocaNaMemoria(Processo processo) ;
	
	protected void addComeco(Processo processo) {
		processo.setEndereco(0);
		mapaDeAlocacao.addFirst(processo);
		alocou = true;
	}

	protected int calculaEspacoLivre(int endereco) {
		int tamanhoLivre = TAMANHO_DA_MEMORIA;
		if ((endereco + 1) < mapaDeAlocacao.size() ) {
			tamanhoLivre = mapaDeAlocacao.get(endereco + 1).getEndereco();
		}
		tamanhoLivre -= mapaDeAlocacao.get(endereco).getEndereco();  
		tamanhoLivre -= mapaDeAlocacao.get(endereco).getTamanho();
	return tamanhoLivre;		
}
	
	public void imprimeMapaDeAlocacao (){
		for (Processo p : mapaDeAlocacao) {
			p.imprimeProcesso();
		}
		System.out.println("\nEspaço ocupado: " + espacoOcupado);
		System.out.println("Quantidade de processos alocados: " + qtdProcessosAlocados);
		System.out.println("Quantidade de processos criados: " + qtdProcessosCriados);
		System.out.println("Quantidade de processos removidos: " + qtdProcessosRemovidos);
		System.out.println("Quantidade de processos não alocados por falta de espaço: " + qtdProcessosNaoAlocados);
		
	}
	
	public void removeAleatorio () {
		if (mapaDeAlocacao.size() > 0) {
			Random r = new Random();
			int d = r.nextInt(mapaDeAlocacao.size());
			//System.out.print("processo removido ");
			//mapaDeAlocacao.get(d).imprimeProcesso();
			espacoOcupado -= mapaDeAlocacao.get(d).getTamanho();
			mapaDeAlocacao.remove(d);
			qtdProcessosRemovidos ++;
			qtdProcessosAlocados--;
			
		}
	}
	


}
	