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
	 * M�todo utilizado para adicionar um novo jogo
	 * @param jogo - objeto que se deseja salvar
	 * @return - retorna true se tudo ocorreu bem na hora de salvar.
	 * retorna false caso tenha tido algum problema na hora de adicionar.
	 */
	public Retorno adicionar(Jogo jogo) {
		if(jogo.getNome() == null || jogo.getNome().equals("")) {
			return new Retorno(false, "Campo Nome do Jogo � obrigat�rio!");
		}
		this.persistencia.adicionar(jogo);
		try {
			this.persistencia.gravarArquivo();
		} catch (IOException e) {
			this.persistencia.remover(jogo);
			return new Retorno(false, "Erro ao Gravar dados!\nDetalhe erro:"+e.getMessage());			
		}
		return new Retorno(true, "Jogo adicionado com Sucesso");
	}
	
	/**
	 * M�todo utilziado para remover um objeto Jogo 
	 * @param jogo - Objeto Jogo que se deseja remover
	 * @return - retorna true se conseguiu remover o jogo, 
	 * retorna false se houve algum problema durante a remo��o do jogo.
	 */
	public boolean remover(Jogo jogo) {
		
		return false;
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
	 * classificaoIndicativa ser� feito busca absoluta
	 * se Informado multiplay o online ser� feito busca por valor 
	 * busca por 
	 * @param jogo
	 * @return
	 */
	public List<Jogo> pesquisarJogo(Jogo jogo){
		
		return null;
	}
	
	/** 
	 * M�todo utilizado para altera o Jogo informado.
	 * @param nomeAntigoDoJogo nome do jogo que se deseja altera (j� existe na lista)
	 * @param jogoAlterado - dados do jogo que ser�o gravados.
	 * @return
	 */
	public boolean alterar(String nomeAntigoDoJogo, Jogo jogoAlterado) {
		
		return false;
	}
}
