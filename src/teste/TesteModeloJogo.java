package teste;

import modelo.Jogo;

public class TesteModeloJogo {
	public static void main(String[] args) {
		Jogo game1 = new Jogo();
		System.out.println("Jogo:"+game1);
		Jogo jogo2 = geraJogoExemplo1();
		System.out.println(jogo2);
	}

	public static Jogo geraJogoExemplo1() {
		Jogo jogo2 = new Jogo(
				"Tetris", 
				"Jogo de encaixar",
				"Puzze",
				1L,
				2013,
				true,
				true,
				12
				);
		return jogo2;
	}
}
