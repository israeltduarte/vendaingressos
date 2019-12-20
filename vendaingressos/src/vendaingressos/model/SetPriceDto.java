package vendaingressos.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "setPrice")
@XmlAccessorType(XmlAccessType.FIELD)
public class SetPriceDto {

	@XmlElement(required = true)
	Double price;
	@XmlElement(required = true)
	int begin;
	@XmlElement(required = true)
	int end;

	public SetPriceDto(Double price, int begin, int end) {
		super();
		this.price = price;
		this.begin = begin;
		this.end = end;
	}

	public SetPriceDto() {

	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public int getBegin() {
		return begin;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}
