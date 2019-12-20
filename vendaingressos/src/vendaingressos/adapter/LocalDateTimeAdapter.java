package vendaingressos.adapter;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

	public LocalDateTime unmarshal(String value) throws Exception {
		System.out.println("pegou 1");
		return (LocalDateTime.parse(value));
	}

	public String marshal(LocalDateTime value) throws Exception {
		System.out.println("pegou 2");
		return (value.toString());
	}

}