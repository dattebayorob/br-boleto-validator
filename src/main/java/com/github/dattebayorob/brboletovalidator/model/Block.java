package com.github.dattebayorob.brboletovalidator.model;

import lombok.Getter;

@Getter
public class Block {
	
	public Block(String code, Integer verifyInteger) {
		this.code = code;
		this.verifyingDigit = verifyInteger;
	}
	
	private String code;
	private Integer verifyingDigit;
	
	public static Block fromCode(String code, Integer startIndex, Integer finalIndex) {
		return new Block(code.substring(startIndex, finalIndex), Character.getNumericValue(code.charAt(finalIndex)));
	}
	
}
