package vendaingressos;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.jws.HandlerChain;

import com.operator.card.ws.CreditCard;
import com.operator.card.ws.CreditCardNotValid_Exception;
import com.operator.card.ws.CreditCardService;
import com.operator.card.ws.CreditCardServiceService;
import com.operator.card.ws.EmptyCreditCardData_Exception;
import com.operator.card.ws.InvalidCreditCardData_Exception;
import com.operator.card.ws.InvalidPriceException_Exception;
import com.operator.card.ws.InvalidTimesException_Exception;
import com.operator.card.ws.MissingDataException_Exception;

import vendaingressos.helper.AES;
import vendaingressos.helper.Utils;



public class MainTeste {

	public static void main(String[] args) throws Exception {

		CreditCardServiceService service = new CreditCardServiceService();
		CreditCardService port = service.getCreditCardServicePort();

		CreditCard card = new CreditCard();
		card.setCode("456");
		card.setFlag("visa");
		card.setName("Marcos Oleiro");
		card.setNumber("1598465715483217");
		card.setValid("09-2025");

		AES aes = new AES("MiZygpJusCpRrf0r".getBytes(StandardCharsets.UTF_8));
		String cardXmlString = Utils.toXmlString(card);
//		System.out.println(cardXmlString);
		byte[] encryptedCard = aes.encrypt(cardXmlString.getBytes(StandardCharsets.UTF_8));
//		byte[] decryptedCard = aes.decrypt(encryptedCard);

		System.out.println(encryptedCard);
//		System.out.println(decryptedCard);
		System.out.println(new String(encryptedCard));

		
		byte[] encode = Base64.getEncoder().encode(encryptedCard);
		
		String aux = new String(encode);
		System.out.println(aux);
//		
//		byte[] decode = aes.decrypt(Base64.getDecoder().decode(encode));
//		
//		System.out.println(new String(decode));
//		new ByteArrayInputStream(aes.decrypt(Base64.getDecoder()
//				.decode(
//		try {
//			if (port.authorize(encode, 1000, 01)) {
//				System.out.println("Sucesso");
//			} else {
//				System.out.println("Falha");
//			}
//
//		} catch (CreditCardNotValid_Exception | EmptyCreditCardData_Exception | InvalidCreditCardData_Exception
//				| InvalidPriceException_Exception | InvalidTimesException_Exception
//				| MissingDataException_Exception e) {
//			e.printStackTrace();
//		}

	}
}
