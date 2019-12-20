package com.operator.card.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "creditcard")
public class CreditCard {

	@XmlElement(name = "number", required = true)
	private String number;

	@XmlElement(name = "name", required = true)
	private String name;

	@XmlElement(name = "flag", required = true)
	private String flag;

	@XmlElement(name = "valid", required = true)
	private String valid;

	@XmlElement(name = "code", required = true)
	private String code;

	public CreditCard() {

	}

	public CreditCard(String number, String name, String flag, String valid, String code) {
		super();
		this.number = number;
		this.name = name;
		this.flag = flag;
		this.valid = valid;
		this.code = code;
	}

	@XmlTransient
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@XmlTransient
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlTransient
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@XmlTransient
	public String getValid() {
		return valid;
	}

	public void setValid(String valid) {
		this.valid = valid;
	}

	@XmlTransient
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
