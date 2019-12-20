package vendaingressosclient.model;

public class Cartao {

	private String bandeira;
	private String numero;
	private String titular;
	private String validade;
	private String codeSeguranca;

	public Cartao() {

	}

	public Cartao(String bandeira, String numero, String titular, String validade, String codeSeguranca) {
		super();
		this.bandeira = bandeira;
		this.numero = numero;
		this.titular = titular;
		this.validade = validade;
		this.codeSeguranca = codeSeguranca;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getValidade() {
		return validade;
	}

	public void setValidade(String validade) {
		this.validade = validade;
	}

	public String getCodeSeguranca() {
		return codeSeguranca;
	}

	public void setCodeSeguranca(String codeSeguranca) {
		this.codeSeguranca = codeSeguranca;
	}

	@Override
	public String toString() {
		return "Cartao [bandeira=" + bandeira + ", numero=" + numero + ", titular=" + titular + ", validade=" + validade
				+ ", codeSeguranca=" + codeSeguranca + "]";
	}

}
