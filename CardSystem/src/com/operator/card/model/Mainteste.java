package com.operator.card.model;

import com.operator.card.customexceptions.CreditCardNotValid;
import com.operator.card.util.ValidateCreditCard;

public class Mainteste {

	public static void main(String[] args) throws Exception {

		CreditCard card = new CreditCard();
		card.setFlag("visa");
		card.setName("Marcos Oleiro");
		card.setNumber("1111222233334444");
		card.setValid("12-2025");
		card.setCode("467");

		if (!ValidateCreditCard.isCreditCardValid(card))
			throw new CreditCardNotValid();

		System.out.println("tudo ok");
//		System.out.println(Year.now());
//		System.out.println(ValidateCreditCard.isDateValid(card.getValid()));

//		JAXBContext context = JAXBContext.newInstance(card.getClass());
//		Marshaller marshaller = context.createMarshaller();
//		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
//		StringWriter sw = new StringWriter();
//		marshaller.marshal(card, sw);
//		String cardXML = sw.toString();
//		System.out.println(cardXML);
//
//		AES aes = new AES("MiZygpJusCpRrf0r".getBytes(StandardCharsets.UTF_8));
//		byte[] encrypted = aes.encrypt(cardXML.getBytes(StandardCharsets.UTF_8));
//		byte[] decrypted = aes.decrypt(encrypted);
//		System.out.println(new String(encrypted));
//		System.out.println(new String(decrypted));

	}

}
