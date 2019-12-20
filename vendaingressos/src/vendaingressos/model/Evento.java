package vendaingressos.model;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import vendaingressos.adapter.LocalDateTimeAdapter;

@XmlRootElement(name = "evento")
@XmlAccessorType(XmlAccessType.FIELD)
public class Evento {

	@XmlAttribute
	private int id;

	@XmlElement
	private String descricao;

	@XmlElement
	private Double precoLugar;

	@XmlElement(type = String.class)
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	@XmlSchemaType(name = "date")
	private LocalDateTime dataHora;

	@XmlElement
	private String local;

	@XmlElement
	private String quantidadelugares;

	@XmlElement(type = String.class)
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	@XmlSchemaType(name = "date")
	private LocalDateTime inicioVenda;

	@XmlElement(type = String.class)
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	@XmlSchemaType(name = "date")
	private LocalDateTime fimVenda;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime data) {
		this.dataHora = data;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getquantidadelugares() {
		return quantidadelugares;
	}

	public void setquantidadelugares(String quantidadeLugares) {
		this.quantidadelugares = quantidadeLugares;
	}

	public LocalDateTime getInicioVenda() {
		return inicioVenda;
	}

	public void setInicioVenda(LocalDateTime inicioVenda) {
		this.inicioVenda = inicioVenda;
	}

	public LocalDateTime getFimVenda() {
		return fimVenda;
	}

	public void setFimVenda(LocalDateTime fimVenda) {
		this.fimVenda = fimVenda;
	}

	public Evento() {

	}

	public Evento(int id, String descricao, LocalDateTime dataHora, String local, String quantidadelugares,
			LocalDateTime inicioVenda, LocalDateTime fimVenda) {
		this.id = id;
		this.descricao = descricao;
		this.dataHora = dataHora;
		this.local = local;
		this.quantidadelugares = quantidadelugares;
		this.inicioVenda = inicioVenda;
		this.fimVenda = fimVenda;
	}

	@Override
	public String toString() {
		return "Evento [id=" + id + ", descricao=" + descricao + ", data=" + dataHora + ", local=" + local
				+ ", quantidadelugares=" + quantidadelugares + ", inicioVenda=" + inicioVenda + ", fimVenda=" + fimVenda
				+ "]";
	}

	public Double getPrecoLugar() {
		return precoLugar;
	}

	public void setPrecoLugar(Double precoLugar) {
		this.precoLugar = precoLugar;
	}

}
