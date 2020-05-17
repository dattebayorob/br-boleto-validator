package com.github.dattebayorob.brboletovalidator.helpers;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Converter {
	
	public String toBoletoArrecadacaoBarCode(String code) {
		return Stream.of(0,1,2,3)
				.map(Integer::valueOf)
				.map( index -> {
					int start = (11 * (index)) + index;
					int end = (11 * (index + 1)) + index;
					return code.substring(start,end);
				}).collect(Collectors.joining());
	}

	public String toBoletoBancarioBarCode(String code) {
		return Stream.of(
				bankIdentification(code),
				currencyCode(code),
				verifyingDigit(code),
				dueDate(code),
				value(code),
				firstBlock(code),
				secondBlock(code),
				thirdBlock(code)
		).collect(Collectors.joining());
	}
	
	public String reverseCode(String code) {
		return new StringBuffer(code).reverse().toString();
	}
	
	private String bankIdentification(String code) {
		return code.substring(0,3);
	}
	
	private String currencyCode(String code) {
		return code.substring(3,4);
	}
	
	private String verifyingDigit(String code) {
		return code.substring(32,33);
	}
	
	private String dueDate(String code) {
		return code.substring(33, 37);
	}
	
	private String value(String code) {
		return code.substring(37,47);
	}
	
	private String firstBlock(String code) {
		return code.substring(4,9);
	}
	
	private String secondBlock(String code) {
		return code.substring(10,20);
	}
	
	private String thirdBlock(String code) {
		return code.substring(21,31);
	}
}
