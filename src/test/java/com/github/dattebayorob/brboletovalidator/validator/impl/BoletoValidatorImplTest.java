package com.github.dattebayorob.brboletovalidator.validator.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.dattebayorob.brboletovalidator.helpers.Mask;
import com.github.dattebayorob.brboletovalidator.validator.BoletoValidator;

class BoletoValidatorImplTest {

	@Mock BoletoArrecadacaoValidator boletoArrecadacaoValidator;
	@Mock BoletoBancarioValidator boletoBancarioValidator;
	BoletoValidator boletoValidator;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		this.boletoValidator = new BoletoValidatorImpl(boletoArrecadacaoValidator, boletoBancarioValidator);
	}
	
	@Test
	@DisplayName("Should validate with Tax Revenues implementation if code starts with 8")
	public void shouldValidateWithTaxRevenuesImplementationIfCodeStartsWith8() {
		String code = "80000000000-0 00000000000-0 00000000000-0 00000000000-0";
		String expected = Mask.unmask(code);
		when(boletoArrecadacaoValidator.isBoletoArrecadacaoCode(expected)).thenReturn(true);
		when(boletoArrecadacaoValidator.validate(expected)).thenReturn(true);
		
		boolean isCodeValid = boletoValidator.validate(code);
		
		verifyNoInteractions(boletoBancarioValidator);
		assertTrue(isCodeValid);
	}
	
	@Test
	@DisplayName("Should validate with Banking implementation if code doesnt starts with 8")
	public void shouldValidateWithBankingImplementationIfCodeStartsDoesntWith8() {
		String code = "40000000000-0 00000000000-0 00000000000-0 00000000000-0";
		String expected = Mask.unmask(code);
		when(boletoArrecadacaoValidator.isBoletoArrecadacaoCode(expected)).thenReturn(false);		
		when(boletoBancarioValidator.validate(expected)).thenReturn(true);
		
		boolean isCodeValid = boletoValidator.validate(code);
		
		verify(boletoArrecadacaoValidator, times(1)).isBoletoArrecadacaoCode(expected);
		verifyNoMoreInteractions(boletoArrecadacaoValidator);
		assertTrue(isCodeValid);
	}
	
	@Test
	public void test() {
		double t = 5.01;
		System.out.println((int) t);
	}

}
