package com.operator.card.util;

import java.time.YearMonth;
import java.util.regex.Pattern;

import com.operator.card.model.CreditCard;

public class ValidateCreditCard {

	public static boolean isCreditCardValid(CreditCard card) {

		Pattern datePattern = Pattern.compile("\\d{2}-\\d{4}");
		Pattern numberPattern = Pattern.compile("\\d{16}");
		Pattern codePattern = Pattern.compile("\\d{3}");

		if (!datePattern.matcher(card.getValid()).matches() || !numberPattern.matcher(card.getNumber()).matches()
				|| !codePattern.matcher(card.getCode()).matches())
			return false;

		int month = Integer.parseInt(card.getValid().split("-")[0]);
		int year = Integer.parseInt(card.getValid().split("-")[1]);
		YearMonth ym = YearMonth.of(year, month);
		YearMonth actualYear = YearMonth.now();

		if (ym.compareTo(actualYear) < 0)
			return false;

		if (!card.getFlag().equalsIgnoreCase("visa") && !card.getFlag().equalsIgnoreCase("mastercard"))
			return false;

		return true;
	}

}
