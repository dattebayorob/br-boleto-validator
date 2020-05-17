package com.github.dattebayorob.brboletovalidator.validator.impl;

import static java.lang.Character.getNumericValue;

import java.util.function.Predicate;
import java.util.stream.Stream;

import com.github.dattebayorob.brboletovalidator.helpers.Converter;
import com.github.dattebayorob.brboletovalidator.helpers.Module;
import com.github.dattebayorob.brboletovalidator.model.Block;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoletoArrecadacaoValidator extends BoletoCodeValidator{

	private final Module module;
	private final Converter converter;
	
	@Override
	protected boolean validateBarCode(String code) {

		if ( !code.matches("^[0-9]{44}$") || !isBoletoArrecadacaoCode(code)) return false;
		
		Integer currencyCode = getCurrencyCode(code);
		Integer verifyingDigit = getVerifyingDigit(code);
		String block = String.format("%s%s", code.substring(0,3), code.substring(4));
		
		if ( currencyCode.equals(6) || currencyCode.equals(7) ) 
			return module.calculateVerifyingCodeWithmodule10(block).equals(verifyingDigit);
		
		if ( currencyCode.equals(8) || currencyCode.equals(9) ) 
			return module.calculateBoletoArrecadacaoVerifyingCodeWithmodule11(block).equals(verifyingDigit);
		
		return false;
	}

	@Override
	protected boolean validateDigitableLine(String code) {
		
		if ( !code.matches("^[0-9]{48}$") || !isBoletoArrecadacaoCode(code)) return false;
		
		boolean isVerifyingDigitValid = validateBarCode(converter.toBoletoArrecadacaoBarCode(code));
		
		Integer currencyCode = getCurrencyCode(code);
		
		Predicate<Block> isBlockValid = null;
		
		if ( currencyCode.equals(6) || currencyCode.equals(7) ) {
			isBlockValid = block -> module.calculateVerifyingCodeWithmodule10(block.getCode()).equals(block.getVerifyingDigit());
		} else if ( currencyCode.equals(8) || currencyCode.equals(9) ) {
			isBlockValid = block -> module.calculateBoletoArrecadacaoVerifyingCodeWithmodule11(block.getCode()).equals(block.getVerifyingDigit());
		} else {
			return false;			
		}
		
		boolean isBlocksValid = 
				Stream.of(0,1,2,3)
				.map( index -> {
					int start = (11 * (index)) + index;
					int end = (11 * (index+1)) + index;
					return Block.fromCode(code, start, end);
				})
				.allMatch(isBlockValid);
		
		return isBlocksValid && isVerifyingDigitValid;
		
	}
	
	protected boolean isBoletoArrecadacaoCode(String code ) {
		return  getNumericValue(code.charAt(0)) == 8;
	}
	
	private Integer getCurrencyCode(String code) {
		return getNumericValue(code.charAt(2));
	}
	
	private Integer getVerifyingDigit(String code) {
		return getNumericValue(code.charAt(3));
	}

	@Override
	protected Integer getBoletoCodeLength() {
		return 48;
	}

}
