package vendaingressos.helper;

import java.nio.charset.StandardCharsets;

import com.operator.card.ws.CreditCard;

import vendaingressos.dao.Token;
import vendaingressos.model.Cartao;

public class Utils {

	public static String toXmlString(CreditCard card) {

		String xmlString = "";

		xmlString += "<number>" + card.getNumber() + "</number>";
		xmlString += "<name>" + card.getName() + "</name>";
		xmlString += "<flag>" + card.getFlag() + "</flag>";
		xmlString += "<valid>" + card.getValid() + "</valid>";
		xmlString += "<code>" + card.getCode() + "</code>";
		return xmlString;

	}

	public static Token generateToken(String cpf, int spotId) {

		AES aes = new AES("MiZygpJusCpRrf0r".getBytes(StandardCharsets.UTF_8));
		byte[] crypt = null;

		Token token = new Token();

		String key = cpf + "/" + String.valueOf(spotId);

		try {
			crypt = aes.encrypt(key.getBytes(StandardCharsets.UTF_8));
		} catch (Exception e) {
			e.printStackTrace();
		}

		token.setToken(crypt.toString());

		return token;
	}

	public static String degenerateToken(String token) {

		AES aes = new AES("MiZygpJusCpRrf0r".getBytes(StandardCharsets.UTF_8));
		byte[] crypt = null;

		try {
			crypt = aes.decrypt(token.getBytes(StandardCharsets.UTF_8));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new String(crypt);

	}
	
	public static CreditCard from(Cartao card) {
		CreditCard c = new CreditCard();
		c.setCode(card.getCodeSeguranca());
		c.setFlag(card.getBandeira());
		c.setName(card.getTitular());
		c.setNumber(card.getNumero());
		c.setValid(card.getValidade());
		return c;
	}

}
