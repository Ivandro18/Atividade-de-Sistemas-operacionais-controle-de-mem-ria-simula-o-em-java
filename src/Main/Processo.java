package Main;

import java.util.Random;

public class Processo {
	private int id;
	private static int contaId = 1;
	private int endereco;
	private int tamanho;
	
	public Processo() {
		id = contaId++;
		Random random = new Random();
		tamanho = random.nextInt(40) + 10;
	}

	public int getEndereco() {
		return endereco;
	}

	public void setEndereco(int endereco) {
		this.endereco = endereco;
	}

	public int getId() {
		return id;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void imprimeProcesso() {
		System.out.println( "Processo id: " + id + ", Endereco: " + endereco + ", Tamanho: " + tamanho);
	}
	
	

}
