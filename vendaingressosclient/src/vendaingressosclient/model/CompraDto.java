package vendaingressosclient.model;

public class CompraDto {

	private String cpf;
	private int evento;
	private int lugar_numero;
	private int lugar_versao;
	private double preco;
	private Cartao cartao;
	private int parcelas;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getEvento() {
		return evento;
	}

	public void setEvento(int evento) {
		this.evento = evento;
	}

	public int getLugar_numero() {
		return lugar_numero;
	}

	public void setLugar_numero(int lugar_numero) {
		this.lugar_numero = lugar_numero;
	}

	public int getLugar_versao() {
		return lugar_versao;
	}

	public void setLugar_versao(int lugar_versao) {
		this.lugar_versao = lugar_versao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public int getParcelas() {
		return parcelas;
	}

	public void setParcelas(int parcelas) {
		this.parcelas = parcelas;
	}

	@Override
	public String toString() {
		return "CompraDto [cpf=" + cpf + ", evento=" + evento + ", lugar_numero=" + lugar_numero + ", lugar_versao="
				+ lugar_versao + ", preco=" + preco + ", cartao=" + cartao + ", parcelas=" + parcelas + "]";
	}

}
