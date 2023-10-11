package Main;

public class FirstFit extends Memoria{
	
	@Override
	public void alocaNaMemoria(Processo processo) {
		alocou = false;
	
	 if ((mapaDeAlocacao.size() == 0) || (mapaDeAlocacao.get(0).getEndereco() > processo.getTamanho())) {
		addComeco(processo);
	}
	else if (mapaDeAlocacao.size() == 1) {
		if (mapaDeAlocacao.get(0).getEndereco() < processo.getTamanho()) {
			processo.setEndereco(mapaDeAlocacao.get(0).getEndereco()+ mapaDeAlocacao.get(0).getTamanho());
			mapaDeAlocacao.add(processo);
			alocou = true;
			}
		else {
			addComeco(processo);
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
	if (alocou) {
		espacoOcupado += processo.getTamanho();
		qtdProcessosAlocados++;
	}
	else qtdProcessosNaoAlocados ++;
}

}
