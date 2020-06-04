package visao;

import java.util.Scanner;

import controle.ControleJogo;
import modelo.Jogo;

public class VisaoJogo {
	public static int NUMERO_OPCAO_MENU = 5;
	
	public int lerInteiro() {
		Scanner leitor = new Scanner(System.in);		
		int valor = 0;
		valor = leitor.nextInt();
		return valor;
	}
	public String lerString() {
		Scanner leitor = new Scanner(System.in);
		String valor = "";
		valor = leitor.nextLine();
		return valor;
	}
	public long lerLong() {
		Scanner leitor = new Scanner(System.in);
		long valor = 0L;
		valor = leitor.nextLong();
		return valor;
	}	
	public boolean lerBoolean() {
		Scanner leitor = new Scanner(System.in);
		boolean valor = false;
		String valorAux = leitor.nextLine();
		if(valorAux.equalsIgnoreCase("Sim") ||
		   valorAux.equalsIgnoreCase("Verdadeiro") ||
		   valorAux.equalsIgnoreCase("true") ||
		   valorAux.equalsIgnoreCase("S") ||
		   valorAux.equalsIgnoreCase("t")
				) {
			valor = true;
		}
		return valor;
	}
	public int escolherOpcao() {
		int opcao = -1;
		
		print("Digite uma opção:");
		
		do {
			try {
				opcao = lerInteiro();
				if(!isOpcaoValida(opcao)) {
					print("Opção incorreta, digite opcao entre 0 e "+NUMERO_OPCAO_MENU+".");
				}
			} catch (Exception e) {
				print("Erro ao ler opção, redigite.");
			}
		} while (!isOpcaoValida(opcao));
		return opcao;
	}

	/**
	 * retorna se a opção informado (número) é uma opção de menu valida
	 * @param opcao - número da opção
	 * @return true se valido, false caso contrário.
	 */
	public boolean isOpcaoValida(int opcao) {
		return !(opcao<0 || opcao > NUMERO_OPCAO_MENU);
	}
	
	public void desenharMenu() {
		print("Escolha uma das opções para trabalhar:");
		print("1-Para listar todos os jogos.");
		print("2-Para pesquisar um jogo.");
		print("3-Incluir novo Jogo.");
		print("4-Remover jogo.");
		print("5-Alterar um jogo.");
		print("6-Testar jogo.");
		print("0-Sair do sistema.");
	}
	
	public void inicializarSistema() {
		int opcaoMenu = -1;
		do {
			desenharMenu();
			opcaoMenu = escolherOpcao();
			processar(opcaoMenu);			
		}while( opcaoMenu!=0 );
	}
	
	public void processar(int opcaoEscolhida) {
		print("Opcao Escolhida: "+opcaoEscolhida);
		switch(opcaoEscolhida) {
			case(1): listarTodos();	break;
			case(2): pesquisarJogo(); break;
			case(3): incluirJogo(); break;
			case(4): removerJogo(); break;
			case(5): alterarJogo(); break;
			case(6): testarJogo(); break;			
		}
	}
	
	private void testarJogo() {
		// TODO Auto-generated method stub
		
	}

	private void alterarJogo() {
		// TODO Auto-generated method stub
		
	}

	private void removerJogo() {
		// TODO Auto-generated method stub
		
	}

	private void incluirJogo() {
		Jogo jogo = lerJogo();
		ControleJogo ctrl = new ControleJogo();
		boolean ok = ctrl.adicionar(jogo);
		
		
		if(!ok) {
			print("Ocorreu um problema ao inclui o Jogo:");
			//TODO Devemos fazer alguma coisa aqui após indentificar o problema.
		}
		
		print("Jogo: "+jogo);
			
	}
	public Jogo lerJogo() {
		print("Tela de inclusão do Jogo:");		
		
		print("Digite o nome do Jogo:");
		String nomeJogo = lerString();
		
		print("Digite a descrição do Jogo:");
		String descricaoJogo = lerString();
		
		print("Digite o nome do Genero:");
		String generoJogo = lerString();
		
		print("Digite o Tamanho do Instalador:");
		Long tamanhoInstalador = lerLongValido();
		
		print("Digite o ano de lancamento:");
		Integer anoLancamento = lerInteiroValido();
		
		print("O jogo é Multiplayer(Sim ou Não):");
		boolean multiplayer = lerBoolean();
		
		print("É possível Jogar Online? (Sim ou Não):");
		boolean online = lerBoolean();
		
		print("Digite a Idade mínima para jogar: ");
		Integer classificaoIndicativa = lerInteiroValido();
		
		Jogo jogo = new Jogo(nomeJogo, descricaoJogo, generoJogo, tamanhoInstalador, 
				anoLancamento, multiplayer, online, classificaoIndicativa);
		return jogo;
	}
	public Long lerLongValido() {
		Long tamanhoInstalador = -1L;
		do {
			try {
				tamanhoInstalador = lerLong();
			}catch(Exception e) {
				print("Número Inválido, digite um valor numérico:");
			}			
		}while (tamanhoInstalador == -1L);
		return tamanhoInstalador;
	}
	
	public Integer lerInteiroValido() {
		Integer valor = -1;
		do {
			try {
				valor = lerInteiro();
			}catch(Exception e) {
				print("Número Inválido, digite um valor numérico:");
			}			
		}while (valor == -1);
		return valor;
	}

	private void pesquisarJogo() {
		// TODO Auto-generated method stub
		
	}

	private void listarTodos() {
		// TODO Auto-generated method stub
		
	}

	public void print(String valor) {
		System.out.println(valor);
	}
	
	public static void main(String[] args) {
		VisaoJogo vj = new VisaoJogo();
		vj.inicializarSistema();
	}
}
