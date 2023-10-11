package Main;

public class Main {

	public static void main(String[] args) {
//		LinkedList<Integer> t = new LinkedList<>();
//		
//		t.add(1);
//		t.add(2);
//		t.add(3);
//		t.add(4);
//		t.add(2, 10);
//		t.remove(2);
//		
//		
//		for (Integer i : t) {
//			System.out.println(i);			
//		}
//		
//		System.out.println(t.get(0));
		
		Memoria memoria = new FirstFit();
		
		for (int i = 0; i < 50; i++) {
			
			memoria.geradorDeProcesso();
			memoria.geradorDeProcesso();
			memoria.removeAleatorio();
		}
		
		memoria.imprimeMapaDeAlocacao();
	}

}
