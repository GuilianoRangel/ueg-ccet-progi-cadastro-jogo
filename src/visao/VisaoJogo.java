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
	
	public Integer lerInteiro() {
		Scanner leitor = new Scanner(System.in);		
		int valor = 0;
		String auxValor = leitor.nextLine();
		if(auxValor.equals("")) { return null; }
		valor = Integer.valueOf(auxValor);
		return valor;
	}
	public String lerString() {
		Scanner leitor = new Scanner(System.in);
		String valor = "";
		valor = leitor.nextLine();
		return valor;
	}
	public Long lerLong() {
		Scanner leitor = new Scanner(System.in);
		Long valor = null;
		String auxValor = leitor.nextLine();
		if(auxValor.equals("")) { return null; }
		valor = Long.valueOf(auxValor);		
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
		return this.escolherOpcao("Digite uma opção:", numeroMaximoOpcoes);
	}
	public int escolherOpcao(String mensagem, int numeroMaximoOpcoes) {
		int opcao = -1;

		println(mensagem);

		do {
			try {
				opcao = lerInteiro();
				if (!isOpcaoValida(opcao, numeroMaximoOpcoes)) {
					println("Valor incorreto, digite valor entre 0 e " + numeroMaximoOpcoes + ".");
				}
			} catch (Exception e) {
				println("Erro ao ler valor, redigite.");
			}
		} while (!isOpcaoValida(opcao, numeroMaximoOpcoes));
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
		println("Escolha uma das opções para trabalhar:");
		println("1-Para listar todos os jogos.");
		println("2-Para pesquisar um jogo.");
		println("3-Incluir novo Jogo.");
		println("4-Remover jogo.");
		println("5-Alterar um jogo.");
		println("6-Testar jogo.");
		println("0-Sair do sistema.");
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
		println("Opcao Escolhida: "+opcaoEscolhida);
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
		int total = this.ctrl.listarTodos().size();
		int op = this.escolherOpcao("Digite o número do jogo para alterar (0 para voltar):",total);
		if(op == 0 ) { return ; };
		
		List<Jogo> lista = this.ctrl.listarTodos();
		Jogo j = lista.get(op-1);
		String nomeAntigo = j.getNome();
		/*
		 * for(int i = 0; i < lista.size(); i++ ) { j = lista.get(i); if(i+1 == op ) {
		 * break; } }
		 */
		println("Jogo: "+j);
		boolean erro = true;
		
		do {	
			j = lerJogo(j);

			Retorno rt = this.ctrl.alterar(nomeAntigo, j);
			if (rt.isSucesso()) {
				println(rt.getMensagem());
				erro = false;
			} else {
				println("ERRO ao Altera:" + rt.getMensagem());
				println("Deseja corrigir os dados (1-sim,0-não)?");
				int opCorrigir = this.escolherOpcao(1);
				if(opCorrigir == 0) {
					erro = false;
				}
			}
		} while (erro);
		
	}

	private void removerJogo() {
		clearScreen();
		this.listarTodos();
		int total = this.ctrl.listarTodos().size();
		int op = this.escolherOpcao("Digite o número do jogo para remover (0 para voltar):",total);
		if(op == 0 ) { return ; };
		
		List<Jogo> lista = this.ctrl.listarTodos();
		Jogo j = lista.get(op-1);
		clearScreen();
		println("Confirme os dados do Jogo a remover!");
		
		imprimirJogo(j, true);
		
		op = this.escolherOpcao("Confirmar Exclusão (1-Sim, 0-Não):",1);
		if(op == 1) {
			boolean ok = this.ctrl.remover(j);
			if(ok) {
				println("Remoção realizada com sucesso!");
			}else {
				println("Erro ao Remover!");
			}
		}		
	}

	private void incluirJogo() {
		Jogo jogo = lerJogo(null);
		
		Retorno ok = this.ctrl.adicionar(jogo);
		// TODO tratar o erro para não perder o que foi digitado e pedir
		// para digitar o que faltou.
		if(!ok.isSucesso()) {
			println("Ocorreu um problema ao inclui o Jogo!");
			println("Erro: "+ok.getMensagem());
		}else {
			println(ok.getMensagem());
		}		
	}
	
	public String lerDadosString(String valorAtual, String nomeAtributo) {
		if(valorAtual != null && !valorAtual.equals("") ) {
			println(nomeAtributo + " atual:"+ valorAtual);			
		}
		println(nomeAtributo+ ":");
		String valor = lerString();
		if(valor.equals("")) {
			valor = valorAtual;
		}
		return valor;
	}
	
	public Long lerDadosLong(Long valorAtual, String nomeAtributo) {
		if(valorAtual != null ) {
			println(nomeAtributo + " atual:"+ valorAtual);			
		}
		println(nomeAtributo+ ":");
		Long valor = lerLongValido();
		if(valor == null) {
			valor = valorAtual;
		}
		return valor;
	}
	
	public Integer lerDadosInteger(Integer valorAtual, String nomeAtributo) {
		if(valorAtual != null ) {
			println(nomeAtributo + " atual:"+ valorAtual);			
		}
		println(nomeAtributo+ ":");
		Integer valor = lerInteiroValido();
		if(valor == null) {
			valor = valorAtual;
		}
		return valor;
	}
	
	public Boolean lerDadosBoolean(Boolean valorAtual, String nomeAtributo) {
		if(valorAtual != null ) {
			println(nomeAtributo + " atual:"+ valorAtual);			
		}
		println(nomeAtributo+ ":");
		Boolean valor = lerBoolean();
		if(valor == null) {
			valor = valorAtual;
		}
		return valor;
	}
	
	public Jogo lerJogo(Jogo pJogo) {
		println("Tela de preenchimento do Jogo:");
		
		if(pJogo != null) {
			println("Digite Enter para manter os Valores Atuais!");
		}
		
		String nomeJogo = lerDadosString(pJogo!=null?pJogo.getNome():"", "Nome");
		
		String descricaoJogo = lerDadosString(pJogo!=null?pJogo.getDescricao():"", "Descrição");
		
		String generoJogo = lerDadosString(pJogo!=null?pJogo.getGenero():"","Genero");
		
		Long tamanhoInstalador = lerDadosLong(pJogo!=null?pJogo.getTamanhoInstalador():null,"Tamanho do Instalador");
		
		Integer anoLancamento = lerDadosInteger(pJogo!=null?pJogo.getAnoLancamento():null,"Ano de lancamento");
		
		Boolean multiplayer = lerDadosBoolean(pJogo!=null?pJogo.isMultiplayer():null, "Multiplayer(Sim ou Não)");
				
		Boolean online = lerDadosBoolean(pJogo!=null?pJogo.isOnline():null, "É possível Jogar Online? (Sim ou Não)");
		
		Integer classificaoIndicativa = lerDadosInteger(pJogo!=null?pJogo.getClassificaoIndicativa():null, "Idade mínima para jogar");
		
		Jogo jogo; 

		jogo = new Jogo(nomeJogo, descricaoJogo, generoJogo, tamanhoInstalador, 
			anoLancamento, multiplayer, online, classificaoIndicativa);
		
		return jogo;
	}
	public Long lerLongValido() {
		Long tamanhoInstalador = -1L;
		do {
			try {
				tamanhoInstalador = lerLong();
			}catch(Exception e) {
				println("Número Inválido, digite um valor numérico:");
			}			
		}while (tamanhoInstalador !=null && tamanhoInstalador == -1L);
		return tamanhoInstalador;
	}
	
	public Integer lerInteiroValido() {
		Integer valor = -1;
		do {
			try {
				valor = lerInteiro();
			}catch(Exception e) {
				println("Número Inválido, digite um valor numérico:");
			}			
		}while (valor !=null && valor == -1);
		return valor;
	}

	private void pesquisarJogo() {
		// TODO Auto-generated method stub
		
	}

	private void listarTodos() {
		println("Lista de todos Jogos Cadastrados");
		List<Jogo> lista = this.ctrl.listarTodos();
		println("------------------------------------------------------");
		for(int i = 0; i< lista.size(); i++) {
			Jogo j = lista.get(i);
			print((i+1)+":");
			imprimirJogo(j, false);
			println("------------------------------------------------------");
		}
		
	}
	
	private void imprimirJogo(Jogo jogo, boolean detalhada) {
		println("\t Nome: "+jogo.getNome()+ "("+jogo.getGenero()+") - Ano: "+jogo.getAnoLancamento());
		if (detalhada) {
			println("\t Descrição: " + jogo.getDescricao());
			println("\t Classificação Indicativa: " + jogo.getClassificaoIndicativa());
		}
	}

	public void println(String valor) {
		System.out.println(valor);
	}
	public void print(String valor) {
		System.out.print(valor);
	}
	
	public static void clearScreen() {
		for(int i=0;i<=50;i++) {
			System.out.print("\n");
		}

	}
	
	public static void main(String[] args) {
		VisaoJogo vj = new VisaoJogo();
		vj.inicializarSistema();
	}
}
