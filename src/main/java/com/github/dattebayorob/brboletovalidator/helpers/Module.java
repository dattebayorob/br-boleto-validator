package com.github.dattebayorob.brboletovalidator.helpers;

import static java.lang.Character.getNumericValue;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Module {
	
	private Integer multiplier = null;
	
	public Integer calculateBoletoBancarioVerifyingCodeWithmodule11(String code) {
		
		Integer verifyingCode = 11 - calculateModule11(code);
				
		if ( verifyingCode.equals(0) || verifyingCode.equals(10) || verifyingCode.equals(11))
			return 1;
		
		return verifyingCode;
	}
	
	public Integer calculateBoletoArrecadacaoVerifyingCodeWithmodule11(String code) {
		
		Integer mod11 =calculateModule11(code);
		
		if ( mod11.equals(0) || mod11.equals(1) ) return 0;
		
		if ( mod11.equals(10)) return 1;
		
		return 11 - mod11;
	}
	
	public Integer calculateVerifyingCodeWithmodule10(String code) {
		String reverseCode = reverseCode(code);
		double summation = IntStream.range(0, reverseCode.length() )
					.map(index -> {
						Integer current = getNumericValue(reverseCode.charAt(index));
						Integer sum = current * ((( index + 1 ) % 2) + 1);
						return ( 
							sum > 9
								? (sum / 10) + (sum %10)
								: sum
						);
					})
					.reduce(0, (left, right) -> left+right);
		return (int)((Math.ceil(summation / 10) * 10) - summation);
	}
	
	private String reverseCode(String code) {
		return new StringBuilder(code).reverse().toString();
	}
	
	private Integer calculateModule11(String code) {
		String reverseCode = reverseCode(code);
		double summation = Stream.of(reverseCode.split(""))
				.map(Integer::valueOf)
				.reduce(0, (acc, current) -> acc + current * getMultiplier());
		
		this.multiplier = null;
		
		return (int) summation % 11;
	}
	
	private Integer getMultiplier() {
		if ( multiplier == null ) {
			this.multiplier = 2;
		}else {
			multiplier = multiplier == 9 ? 2 : multiplier + 1;			
		}
		return multiplier;
	}
}
