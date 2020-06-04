package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import modelo.Jogo;

public class PersistenciaJogo {

	private static final String SEPARADO_CAMPO = "##!##";
	private String nomeArquivo;
	private List<Jogo> jogos;

	public PersistenciaJogo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
		this.limparLista();
	}
	
	public PersistenciaJogo() {
		this.nomeArquivo = "jogos.txt";
		this.limparLista();
	}

	/**
	 * Adiciona um jogo a lista de jogos para ser salva
	 * 
	 * @param jogo - jogo a ser incluído na lista.
	 */
	public void adicionar(Jogo jogo) {
		this.jogos.add(jogo);
	}

	/**
	 * Remover o jogo informado da lista de jogos
	 * 
	 * @param jogo - jogo a ser removido
	 * @return informa se o jogo foi removido da lista, retorna true se o jogo
	 *         existia e foi removido, false se o jogo não existe na lista
	 */
	public boolean remover(Jogo jogo) {
		boolean existe = this.jogos.contains(jogo);
		if (existe) {
			this.jogos.remove(jogo);
		}
		return existe;
	}

	/**
	 * Remove um jogo da lista pela posição na lista
	 * 
	 * @param posicao - posição a ser removida
	 * @return informa se o jogo foi removido da lista, retorna true se o jogo
	 *         existia e foi removido, false se o jogo não existe na lista ou se a
	 *         posição é invalida
	 */
	public boolean remover(int posicao) {
		if (posicao >= 0 && posicao < this.jogos.size()) {
			Jogo jogo = this.jogos.get(posicao);
			return this.remover(jogo);
		}
		return false;
	}
	
	/**
	 * Metodo utilizado para limpar a lista de jogos
	 */
	private void limparLista() {
		this.jogos = new ArrayList<Jogo>();
	}

	/**
	 * fazer a leitura do arquivo em disco e adiciona as linhas encontras no lista
	 * de Jogos.
	 * @throws IOException 
	 */
	public void lerArquivo() throws IOException {
		this.limparLista();
		
	    BufferedReader leitor = new BufferedReader(new FileReader (this.nomeArquivo));
	    
	    String         linha = null;
	    try {
	        while((linha = leitor.readLine()) != null) {
	        	// TODO tirar debug
	        	System.out.println(":"+linha+":");
	        	Jogo jogo = criaObjetoJogoFromLinha(linha);
				this.adicionar(jogo);
	        }
	    } finally {
	        leitor.close();
	    }
		
	}

	private Jogo criaObjetoJogoFromLinha(String linha) {
		String [] vetor = linha.split(this.SEPARADO_CAMPO);
		Jogo jogo = new Jogo();
		jogo.setNome(vetor[0]); 
		jogo.setDescricao(vetor[1]);
		jogo.setGenero(vetor[2]);
		try { jogo.setTamanhoInstalador(Long.valueOf(vetor[3])); } catch (Exception e) {}
		try { jogo.setAnoLancamento(Integer.valueOf(vetor[4])); } catch (Exception e) {}
		try { jogo.setMultiplayer(Boolean.valueOf(vetor[5])); } catch (Exception e) {}
		try { jogo.setOnline(Boolean.valueOf(vetor[6])); } catch (Exception e) {}
		try { jogo.setClassificaoIndicativa(Integer.valueOf(vetor[7])); } catch (Exception e) {}
		return jogo;
	}
	
	/**
	 * Faz a gravação da lista de Jogos no disco, sobrescrevendo o arquivo informado
	 * se existir.
	 * @throws IOException 
	 */
	public void gravarArquivo() throws IOException {	
		  FileWriter arq = new FileWriter(this.nomeArquivo); 
		  PrintWriter gravarArq = new PrintWriter(arq);
		  
		  for (Jogo jogo : this.jogos) {
			String linha = preparaLinhaTexto(jogo);
			gravarArq.println(linha);			
		  }
		  arq.close();
		 
	}

	/**
	 * recebe um objeto jogo, e gera uma com os dados para 
	 * serem gravados em arquivo texto 
	 * @param jogo
	 * @return
	 */
	private String preparaLinhaTexto(Jogo jogo) {
		String linha = 
				getValorString(jogo.getNome()) 				+ SEPARADO_CAMPO + 
				getValorString(jogo.getDescricao()) 		+ SEPARADO_CAMPO +
				getValorString(jogo.getGenero()) 			+ SEPARADO_CAMPO +
				getValorString(jogo.getTamanhoInstalador()) + SEPARADO_CAMPO +
				getValorString(jogo.getAnoLancamento())		+ SEPARADO_CAMPO +
				getValorString(jogo.isMultiplayer())		+ SEPARADO_CAMPO +
				getValorString(jogo.isOnline())				+ SEPARADO_CAMPO +
				getValorString(jogo.getClassificaoIndicativa());
		return linha;
	}
	
	private String getValorString(Object object) {
		if(object == null) {
			return "";
		}
		return object.toString();
	}

	/**
	 * obtem a lista de Jogos lidos.
	 * 
	 * @return
	 */
	public List<Jogo> getJogos() {
		return this.jogos;
	}

}