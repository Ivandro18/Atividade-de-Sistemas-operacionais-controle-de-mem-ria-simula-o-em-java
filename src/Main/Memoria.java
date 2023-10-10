package Main;

import java.util.LinkedList;
import java.util.Random;

public class Memoria {
	private int espacoOcupado = 0;
	private int processosNaoAlocados;
	private int enderecoDeBusca;
	private LinkedList<Processo> mapaDeAlocacao ;
	
	public Memoria() {
		this.espacoOcupado = 0;
		this.processosNaoAlocados = 0;
		this.mapaDeAlocacao = new LinkedList<>();
	}

	public void geradorDeProcesso () {
		Processo processo = new Processo();
		enderecoDeBusca = 0;
		alocaNaMemoria(processo);
	}
	
	
	public void alocaNaMemoria(Processo processo) {
		boolean alocou = false;
		if (mapaDeAlocacao.size() == 0) {
			processo.setEndereco(0);
			mapaDeAlocacao.add(processo);
			alocou = true;
		}
		else if (mapaDeAlocacao.get(0).getEndereco() > processo.getTamanho()) {
			processo.setEndereco(0);
			mapaDeAlocacao.addFirst(processo);
			alocou = true;
		}
		else if (mapaDeAlocacao.size() == 1) {
			if (mapaDeAlocacao.get(0).getEndereco() < processo.getTamanho()) {
				processo.setEndereco(mapaDeAlocacao.get(0).getEndereco()+ mapaDeAlocacao.get(0).getTamanho());
				mapaDeAlocacao.add(processo);
				alocou = true;
				}
			else {
				processo.setEndereco(0);
				mapaDeAlocacao.addFirst(processo);
				alocou = true;
			}
			}else {
			for (int i = 0; i < mapaDeAlocacao.size(); i++) {
				if (calculaEspacoLivre(i) >= processo.getTamanho()) {
					processo.setEndereco(mapaDeAlocacao.get(i).getEndereco() + mapaDeAlocacao.get(i).getTamanho());
					mapaDeAlocacao.add(i + 1, processo);
					alocou = true;
					break;
				}
			}
		}
		if (alocou)espacoOcupado += processo.getTamanho();
		else processosNaoAlocados ++;
	}
	
	private int calculaEspacoLivre(int endereco) {
		int tamanhoLivre = 1000;
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
		System.out.println("Espaço ocupado: " + espacoOcupado);
		System.out.println("Quantidade de processos não alocados por falta de espaço: " + processosNaoAlocados);
		
	}
	
	public void removeAleatorio () {
		if (mapaDeAlocacao.size() > 0) {
			Random r = new Random();
			int d = r.nextInt(mapaDeAlocacao.size());
			//System.out.print("processo removido ");
			//mapaDeAlocacao.get(d).imprimeProcesso();
			espacoOcupado -= mapaDeAlocacao.get(d).getTamanho();
			mapaDeAlocacao.remove(d);
			
		}
	}
	
}
	