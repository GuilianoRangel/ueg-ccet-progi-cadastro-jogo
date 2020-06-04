package controle;

import java.util.List;

import modelo.Jogo;

public class ControleJogo {
	
	/**
	 * Método utilizado para adicionar um novo jogo
	 * @param jogo - objeto que se deseja salvar
	 * @return - retorna true se tudo ocorreu bem na hora de salvar.
	 * retorna false caso tenha tido algum problema na hora de adicionar.
	 */
	public boolean adicionar(Jogo jogo) {
	
		return false;
	}
	
	/**
	 * Método utilziado para remover um objeto Jogo 
	 * @param jogo - Objeto Jogo que se deseja remover
	 * @return - retorna true se conseguiu remover o jogo, 
	 * retorna false se houve algum problema durante a remoção do jogo.
	 */
	public boolean remover(Jogo jogo) {
		
		return false;
	}
	
	/**
	 * retorna a lista de todos os Jogos cadastrados no sistema.
	 * @return lista de Jogos.
	 */
	public List<Jogo> listarTodos(){
	
		return null;
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
	public boolean alterar(String nomeAntigoDoJogo, Jogo jogoAlterado) {
		
		return false;
	}
}
