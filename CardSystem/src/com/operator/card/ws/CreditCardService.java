package com.operator.card.ws;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

import com.operator.card.customexceptions.CreditCardNotValid;
import com.operator.card.customexceptions.EmptyCreditCardData;
import com.operator.card.customexceptions.InvalidCreditCardData;
import com.operator.card.customexceptions.InvalidPriceException;
import com.operator.card.customexceptions.InvalidTimesException;
import com.operator.card.customexceptions.MissingDataException;
import com.operator.card.model.CreditCard;
import com.operator.card.util.ValidateCreditCard;

@WebService
@HandlerChain(file = "handlerchain.xml")
public class CreditCardService {

	@WebMethod
	public boolean authorize(@WebParam(name = "cartao") @XmlElement(required = true) CreditCard card,
			@WebParam(name = "valor") @XmlElement(required = true) Double price,
			@WebParam(name = "parcelas") @XmlElement(required = true) Integer times)
			throws MissingDataException, InvalidCreditCardData, EmptyCreditCardData, CreditCardNotValid,
			InvalidPriceException, InvalidTimesException {

//		AES aes = new AES("aaaabbbbccccdddd".getBytes(StandardCharsets.UTF_8));

		if (card == null || price == null || times == null)
			throw new MissingDataException();

		if (card.getCode() == null || card.getFlag() == null || card.getName() == null || card.getNumber() == null
				|| card.getValid() == null)
			throw new InvalidCreditCardData();

		if (card.getCode().trim().isEmpty() || card.getFlag().trim().isEmpty() || card.getName().trim().isEmpty()
				|| card.getNumber().trim().isEmpty() || card.getValid().trim().isEmpty())
			throw new EmptyCreditCardData();

		if (!ValidateCreditCard.isCreditCardValid(card))
			throw new CreditCardNotValid();

		if (price <= 0)
			throw new InvalidPriceException();

		if (times <= 0)
			throw new InvalidTimesException();
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return true;
	}

}
