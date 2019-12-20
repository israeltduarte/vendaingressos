package vendaingressos.model;

import vendaingressos.dao.Token;

public class Lugar {

	private Status status;
	private Integer id;
	private Integer evento;
	private Integer versao;
	private Token token;
	private Integer numero;
	private Double preco;

	public Lugar() {
	};

	public Lugar(Status status, Integer id, Integer evento, Integer versao, Token token, Integer numero, Double preco) {
		super();
		this.status = status;
		this.id = id;
		this.evento = evento;
		this.versao = versao;
		this.token = token;
		this.numero = numero;
		this.preco = preco;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEvento() {
		return this.evento;
	}

	public void setEvento(Integer evento) {
		this.evento = evento;
	}

	public Integer getVersao() {
		return versao;
	}

	public void setVersao(Integer versao) {
		this.versao = versao;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Lugar [status=" + status + ", id=" + id + ", evento=" + evento + ", versao=" + versao + ", token="
				+ token + ", numero=" + numero + ", preco=" + preco + "]";
	}

	public String toStringLugarDisponivel() {
		return "Lugar [id=" + id + ", versao=" + versao + ", numero=" + numero + ", preco=" + preco + "]";
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

}
