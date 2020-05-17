package com.github.dattebayorob.brboletovalidator.validator.impl;

import static java.lang.Character.getNumericValue;

import java.util.stream.Stream;

import com.github.dattebayorob.brboletovalidator.helpers.Converter;
import com.github.dattebayorob.brboletovalidator.helpers.Module;
import com.github.dattebayorob.brboletovalidator.model.Block;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoletoBancarioValidator extends com.github.dattebayorob.brboletovalidator.validator.impl.BoletoCodeValidator{
	
	private final Module module;
	private final Converter converter;
	
	@Override
	protected boolean validateBarCode(String code) {
		
		if ( !code.matches("^[0-9]{44}$") ) return false;
		
		Integer verifyingDigit = getNumericValue(code.charAt(4));
		String block = String.format("%s%s", code.substring(0, 4), code.substring(5)); 
		
		return module.calculateBoletoBancarioVerifyingCodeWithmodule11(block).equals(verifyingDigit);
	}

	@Override
	protected boolean validateDigitableLine(String code) {
		
		if ( !code.matches("^[0-9]{47}$") ) return false;

		boolean isBlocksValid = 
				Stream.of(
						Block.fromCode(code, 0, 9),
						Block.fromCode(code, 10, 20),
						Block.fromCode(code, 21, 31)
				).allMatch(
						block -> module.calculateVerifyingCodeWithmodule10(block.getCode()).equals(block.getVerifyingDigit())
				);
		
		boolean isVerifyingDigtValid = validateBarCode(converter.toBoletoBancarioBarCode(code));
		
		return isBlocksValid && isVerifyingDigtValid;
	}

	@Override
	protected Integer getBoletoCodeLength() {
		return 47;
	}

}
