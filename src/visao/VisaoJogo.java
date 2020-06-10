package visao;

import java.util.List;
import java.util.Scanner;

import controle.ControleJogo;
import modelo.Jogo;
import utils.Retorno;

public class VisaoJogo {
	public static int NUMERO_OPCAO_MENU = 5;
	private ControleJogo ctrl;
	
	public VisaoJogo() {
		this.ctrl = new ControleJogo();
	}
	
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
	public Boolean lerBoolean() {
		Scanner leitor = new Scanner(System.in);
		Boolean valor = false;
		String valorAux = leitor.nextLine();
		
		if(valorAux.equals("")){ return null; }
		
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
		return this.escolherOpcao(NUMERO_OPCAO_MENU);
	}
	public int escolherOpcao(int numeroMaximoOpcoes) {
		int opcao = -1;
		
		print("Digite uma opção:");
		
		do {
			try {
				opcao = lerInteiro();
				if(!isOpcaoValida(opcao,numeroMaximoOpcoes)) {
					print("Opção incorreta, digite opcao entre 0 e "+numeroMaximoOpcoes+".");
				}
			} catch (Exception e) {
				print("Erro ao ler opção, redigite.");
			}
		} while (!isOpcaoValida(opcao,numeroMaximoOpcoes));
		return opcao;
	}

	/**
	 * retorna se a opção informado (número) é uma opção de menu valida
	 * @param opcao - número da opção
	 * @return true se valido, false caso contrário.
	 */
	public boolean isOpcaoValida(int opcao) {
		return isOpcaoValida(opcao, NUMERO_OPCAO_MENU);
	}
	/**
	 * retorna se a opção informado (número) é uma opção de menu valida
	 * @param opcao - número da opção
	 * @return true se valido, false caso contrário.
	 */
	public boolean isOpcaoValida(int opcao, int numMaximoOpcoes) {
		return !(opcao<0 || opcao > numMaximoOpcoes);
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
		this.listarTodos();
		print("Digite o número do jogo como opção (0 para voltar:");
		int total = this.ctrl.listarTodos().size();
		int op = this.escolherOpcao(total);
		if(op == 0 ) { return ; };
		
		List<Jogo> lista = this.ctrl.listarTodos();
		Jogo j = lista.get(op-1);
		/*
		 * for(int i = 0; i < lista.size(); i++ ) { j = lista.get(i); if(i+1 == op ) {
		 * break; } }
		 */
		print("Jogo: "+j);
		Jogo jogo = lerJogo(j);
		
	}

	private void removerJogo() {
		// TODO Auto-generated method stub
		
	}

	private void incluirJogo() {
		Jogo jogo = lerJogo(null);
		
		Retorno ok = this.ctrl.adicionar(jogo);
		// TODO tratar o erro para não perder o que foi digitado e pedir
		// para digitar o que faltou.
		if(!ok.isSucesso()) {
			print("Ocorreu um problema ao inclui o Jogo!");
			print("Erro: "+ok.getMensagem());
		}else {
			print(ok.getMensagem());
		}		
	}
	public Jogo lerJogo(Jogo pJogo) {
		print("Tela de inclusão do Jogo:");
		
		if(pJogo != null) {
			print("Digite Enter para manter os Valores Atuais!");
		}
		
		if(pJogo !=null && pJogo.getNome() != null && !pJogo.getNome().equals("") ){
			print("Nome Atual: "+pJogo.getNome());
		}
		
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
		Boolean multiplayer = lerBoolean();
		
		print("É possível Jogar Online? (Sim ou Não):");
		Boolean online = lerBoolean();
		
		print("Digite a Idade mínima para jogar: ");
		Integer classificaoIndicativa = lerInteiroValido();
		
		Jogo jogo; 
		if(pJogo == null ) {
			jogo = new Jogo(nomeJogo, descricaoJogo, generoJogo, tamanhoInstalador, 
				anoLancamento, multiplayer, online, classificaoIndicativa);
		}else {
			jogo = pJogo;
			if(!nomeJogo.equals("")) {
				jogo.setNome(nomeJogo);
			}
		}
		// TODO remover debug
		print("Jogo após leitura:"+jogo);
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
		print("Lista de todos Jogos Cadastrados");
		List<Jogo> lista = this.ctrl.listarTodos();
		print("------------------------------------------------------");
		for(int i = 0; i< lista.size(); i++) {
			Jogo j = lista.get(i);
			print((i+1)+":\t Nome:"+j.getNome()+ "("+j.getGenero()+") - Ano: "+j.getAnoLancamento());
			print("\t Descrição: "+j.getDescricao());
			print("\t Classificação Indicativa: "+j.getClassificaoIndicativa());
			print("------------------------------------------------------");
		}
		
	}

	public void print(String valor) {
		System.out.println(valor);
	}
	
	public static void main(String[] args) {
		VisaoJogo vj = new VisaoJogo();
		vj.inicializarSistema();
	}
}
