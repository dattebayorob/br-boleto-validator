package com.github.dattebayorob.brboletovalidator.helpers;

public class Mask {
	public static String unmask(String code) {
		return code.replaceAll("( |\\.|-)", "");
	}
}
