package teste;

import java.io.IOException;

import modelo.Jogo;
import persistencia.PersistenciaJogo;

public class TestePersistenciaJogo {
	public static void main(String[] args) {
		PersistenciaJogo p = new PersistenciaJogo();
		//testeSalvarArquivo(p);
		
		try {
			p.lerArquivo();
			for (Jogo jogo : p.getJogos()) {
				System.out.println(jogo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	public static void testeSalvarArquivo(PersistenciaJogo p) {
		Jogo j1 = new Jogo();
		p.adicionar(j1);
		Jogo j2 = TesteModeloJogo.geraJogoExemplo1();
		p.adicionar(j2);
		
		try {
			p.gravarArquivo();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
