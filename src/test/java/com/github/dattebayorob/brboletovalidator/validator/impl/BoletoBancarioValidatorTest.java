package com.github.dattebayorob.brboletovalidator.validator.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import com.github.dattebayorob.brboletovalidator.helpers.Converter;
import com.github.dattebayorob.brboletovalidator.helpers.Module;

class BoletoBancarioValidatorTest {

	Module module;
	Converter converter;
	BoletoBancarioValidator boletoBancarioValidator;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		module = spy(new Module());
		converter = spy(new Converter());
		this.boletoBancarioValidator = spy(
				new BoletoBancarioValidator(module,converter)
		);
	}
	
	@Test
	void shouldValidateBoletoBancarioDigitableCodeWithMask() {
		String code = "00190.00009  02866.659077  43304.582174  9  82450000021898";
		assertTrue(boletoBancarioValidator.validate(code));
	}
	
	@Test
	void shouldValidateBoletoBancarioBarCode() {
		String code = "00193373700000001000500940144816060680935031";
		assertTrue(boletoBancarioValidator.validateBarCode(code));
		
		code = "00153373700000001000500940144816060680935031";
		assertFalse(boletoBancarioValidator.validateBarCode(code));
	}
	
	@Test
	void shouldValidateLengthOfBoletoBancarioBarCode() {
		String code = "0015337300000001000500940144816060680935031";
		assertFalse(boletoBancarioValidator.validateBarCode(code));
	}
	
	@Test
	void shouldValidate() {
		String code = "23793381286000782713695000063305975520000370000";
		assertTrue(boletoBancarioValidator.validate(code));
	}

}
