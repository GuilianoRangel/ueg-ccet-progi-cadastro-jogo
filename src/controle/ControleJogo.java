package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modelo.Jogo;
import persistencia.PersistenciaJogo;
import utils.Retorno;

public class ControleJogo {
	PersistenciaJogo persistencia;
	
	public ControleJogo() {
		this.persistencia = new PersistenciaJogo();
		try {
			this.persistencia.lerArquivo();
		} catch (IOException e) {
			throw new RuntimeException("Erro ao Ler Arquivo",e);
		}
	}
	
	/**
	 * Método utilizado para adicionar um novo jogo
	 * @param jogo - objeto que se deseja salvar
	 * @return - retorna true se tudo ocorreu bem na hora de salvar.
	 * retorna false caso tenha tido algum problema na hora de adicionar.
	 */
	public Retorno adicionar(Jogo jogo) {
		Retorno rt = validarJogo(jogo);
		if(!rt.isSucesso()) { return rt; };
		
		this.persistencia.adicionar(jogo);
		try {
			this.persistencia.gravarArquivo();
		} catch (IOException e) {
			this.persistencia.remover(jogo);
			return new Retorno(false, "Erro ao Gravar dados!\nDetalhe erro:"+e.getMessage());			
		}
		return new Retorno(true, "Jogo adicionado com Sucesso");
	}
	
	public Retorno validarJogo(Jogo jogo) {
		if(jogo.getNome() == null || jogo.getNome().trim().equals("")) {
			return new Retorno(false, "Campo Nome do Jogo é obrigatório!");
		}
		return new Retorno(true, "Jogo Válido");
	}
	
	/**
	 * Método utilziado para remover um objeto Jogo 
	 * @param jogo - Objeto Jogo que se deseja remover
	 * @return - retorna true se conseguiu remover o jogo, 
	 * retorna false se houve algum problema durante a remoção do jogo.
	 */
	public boolean remover(Jogo jogo) {
		// validarRemocao(
		int posicao = this.getJogoPosicao(jogo);
		boolean ok = false;
		if(posicao == -1) {
			return ok;
		}
		Jogo auxJogo = this.persistencia.getJogos().get(posicao);
		ok = this.persistencia.remover(posicao);
		
		try {
			this.persistencia.gravarArquivo();
		} catch (IOException e) {
			this.persistencia.adicionar(auxJogo);
			ok =  false;
		}
		
		return ok;
	}
	
	/**
	 * retorna a lista de todos os Jogos cadastrados no sistema.
	 * @return lista de Jogos.
	 */
	public List<Jogo> listarTodos(){
		List<Jogo> lista = this.persistencia.getJogos();
		return lista;
	}
	
	/**
	 * Pesquisa um jogo pelos atributos de Jogo preenchido.
	 * Casos seja informado o seja o nome, descricao, genero
	 * sera feito uma busca por parte do que for informado,
	 * se for informado o atributo tamanhoIntalador, anoLancamento,
	 * classificaoIndicativa será feito busca absoluta
	 * se Informado multiplay o online será feito busca por valor 
	 * busca por 
	 * @param jogo
	 * @return
	 */
	public List<Jogo> pesquisarJogo(Jogo jogo){
		
		return null;
	}
	
	/** 
	 * Método utilizado para altera o Jogo informado.
	 * @param nomeAntigoDoJogo nome do jogo que se deseja altera (já existe na lista)
	 * @param jogoAlterado - dados do jogo que serão gravados.
	 * @return
	 */
	public Retorno alterar(String nomeAntigoDoJogo, Jogo jogoAlterado) {

		Retorno rt = validarJogo(jogoAlterado);
		if(!rt.isSucesso()) { return rt; }; 
				
		int posicao = getJogoPosicaoByName(nomeAntigoDoJogo);
		if(posicao != -1) {
				Jogo auxJogo = this.persistencia.getJogos().get(posicao);
				this.persistencia.getJogos().set(posicao, jogoAlterado);				
				try {
					this.persistencia.gravarArquivo();
				} catch (IOException e) {
					this.persistencia.getJogos().set(posicao, auxJogo);
					return new Retorno(false, "Erro ao Salvar a Alteração do jogo: "+e.getMessage());
				}
				return new Retorno(true, "Jogo Alterado com sucesso!");
		}		
		return new Retorno(false, "Jogo não encontroado na lista de Jogos!");
	}
	
	private int getJogoPosicaoByName(String nome) {
		Jogo auxJogo = new Jogo();
		auxJogo.setNome(nome);
		return getJogoPosicao(auxJogo);
	}
	/**
	 * Retorna a posição do jogo informado, sendo feito a busca pelo nome(no sensitive case) do jogo
	 * @param jogo - jogo que contem o nome da ser buscado
	 * @return -1 se o jogo não foi encontrado, ou a posição do jogo na lista.
	 */
	private int getJogoPosicao(Jogo jogo) {
		List<Jogo> listaJogos = this.persistencia.getJogos();

		int retorno = -1; 
		
		for (int i = 0; i < listaJogos.size(); i++) {
			Jogo auxJogo = listaJogos.get(i);
			if (auxJogo.getNome().equalsIgnoreCase(jogo.getNome())) {
				retorno = i;
				break;
			}
		}
		
		return retorno;
	}
}
