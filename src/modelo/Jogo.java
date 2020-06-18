package modelo;

public class Jogo {
	/**
	 * utilizado definir o nome do jogo (não é descrição)
	 */
	private String nome;
	/**
	 * Descrição do jogo, a história do Jogo (enredo)
	 */
	private String descricao;
	/**
	 * Utilizado para determinar o Genero do Jogo
	 * GENERO: FPS, RPG, MMORPG ETC
	 */
	private String genero;
	/** 
	 * Tamanho do Jogo em GB do instalador
	 */
	private Long tamanhoInstador;
	
	/** 
	 * ano em que o Jogo foi lançado.
	 */
	private Integer anoLancamento;
	
	/**
	 * Indica se o jogo suporte jogo multiplayer
	 */
	private Boolean multiplayer;
	
	/**
	 * Indica se o jogo pode ser jogado on-line
	 */
	private Boolean online;
	
	/**
	 * indica a idade mínima recomendada para jogar
	 */
	private Integer classificaoIndicativa;

	@Override
	public String toString() {
		return "Jogo [nome=" + nome + ", descricao=" + descricao + ", genero=" + genero + ", tamanhoInstador="
				+ tamanhoInstador + ", anoLancamento=" + anoLancamento + ", multiplayer=" + multiplayer + ", online="
				+ online + ", classificaoIndicativa=" + classificaoIndicativa + "]";
	}

	public Jogo(
			String nome, 
			String descricao, 
			String genero, 
			Long tamanhoInstalador, 
			Integer anoLancamento,
			Boolean multiplayer, 
			Boolean online, 
			Integer classificaoIndicativa) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.genero = genero;
		this.tamanhoInstador = tamanhoInstalador;
		this.anoLancamento = anoLancamento;
		this.multiplayer = multiplayer;
		this.online = online;
		this.classificaoIndicativa = classificaoIndicativa;
	}
	public Jogo() {
		this.nome="Não Definido";
		this.descricao="";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public Long getTamanhoInstalador() {
		return tamanhoInstador;
	}

	public void setTamanhoInstalador(Long tamanhoInstador) {
		this.tamanhoInstador = tamanhoInstador;
	}

	public Integer getAnoLancamento() {
		return anoLancamento;
	}

	public void setAnoLancamento(Integer anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	public boolean isMultiplayer() {
		return multiplayer;
	}

	public void setMultiplayer(boolean multiplayer) {
		this.multiplayer = multiplayer;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public Integer getClassificaoIndicativa() {
		return classificaoIndicativa;
	}

	public void setClassificaoIndicativa(Integer classificaoIndicativa) {
		this.classificaoIndicativa = classificaoIndicativa;
	}
	
	
}
