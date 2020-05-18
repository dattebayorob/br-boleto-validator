package com.github.dattebayorob.brboletovalidator.validator.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.github.dattebayorob.brboletovalidator.helpers.Converter;
import com.github.dattebayorob.brboletovalidator.helpers.Module;

class BoletoArrecadacaoValidatorTest {
	
	@Spy Module module;
	@Spy Converter converter;
	BoletoArrecadacaoValidator boletoArrecadacaoValidator;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		this.boletoArrecadacaoValidator = spy(
				new BoletoArrecadacaoValidator(module,converter)
		);
	}
	
	@Test
	void shouldValidateBoletoArrecadacaoWithMask() {
		String code = "82690000001-7 19400009500-1 09647784900-2 03007131025-1";
		assertTrue(boletoArrecadacaoValidator.validate(code));
	}
	
	@Test
	void shouldValidateBoletoArreacadacaoDigitableLineCodeModule10WithoutMask() {
		String code = "836200000005667800481000180975657313001589636081";
		assertTrue(boletoArrecadacaoValidator.validate(code));
	}
	
	@Test
	void shouldReturnFalseIfBoletoArrecadacaoDigitableLineCodeWithModule10() {
		String code = "836200000005667800481800180975657313001589636081";
		assertFalse(boletoArrecadacaoValidator.validate(code));
	}
	
	@Test
	void shouldValidateBoletoArrecadacaoDigitableLineCodeWithModule11WhenCurrencyCodeIsBrasil() {
		String code = "85890000460-9 52460179160-5 60759305086-5 83148300001-0";
		assertTrue(boletoArrecadacaoValidator.validate(code));
	}
	
	@Test
	void shouldValidateBoletoArrecadacaoDigitableLineCodeWithModule11ReturningFalse() {
		String code = "848900000002404201622015809051904292586034111220";
		assertFalse(boletoArrecadacaoValidator.validateDigitableLine(code));
	}
	
	@Test
	void shouldValidateBoletoArrecadacaoBarCodeWithModule10WhenCurrencyCodeIsNotReal() {
		String code = "83620000000667800481001809756573100158963608";
		assertTrue(boletoArrecadacaoValidator.validateBarCode(code));
	}
	
	@Test
	void shouldValidateBoletoArrecadacaoBarCodeWithModule11whenCurrencyIsReal() {
		String code = "84890000000404201622018060519042958603411122";
		assertTrue(boletoArrecadacaoValidator.validateBarCode(code));
	}
	
	@Test
	void shouldValidateBoletoArrecadacaoWithInvalidLength() {
		String code = "8464000000087350024030015034903370804040612";
		assertFalse(boletoArrecadacaoValidator.validateBarCode(code));
	}
	
	@Test
	void shouldValidate() {
		String code = "858000000070438403281922630720192528304729600523";
		assertTrue(boletoArrecadacaoValidator.validate(code));
	}
}
