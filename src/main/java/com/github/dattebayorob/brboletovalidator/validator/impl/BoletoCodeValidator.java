package com.github.dattebayorob.brboletovalidator.validator.impl;

import com.github.dattebayorob.brboletovalidator.helpers.Mask;
import com.github.dattebayorob.brboletovalidator.validator.BoletoValidator;

public abstract class BoletoCodeValidator implements BoletoValidator{
	
	@Override
	public boolean validate(String code) {
		String unmaskedCode = Mask.unmask(code);
		if ( unmaskedCode.length() == 44 ) return validateBarCode(unmaskedCode);
		if ( unmaskedCode.length() == getBoletoCodeLength() ) return validateDigitableLine(unmaskedCode);
		return false;
	}
	
	protected abstract Integer getBoletoCodeLength();
	protected abstract boolean validateBarCode(String code);
	protected abstract boolean validateDigitableLine(String code);
}
