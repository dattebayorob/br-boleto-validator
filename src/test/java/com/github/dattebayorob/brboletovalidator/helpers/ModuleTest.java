package com.github.dattebayorob.brboletovalidator.helpers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ModuleTest {

	Module module = new Module();
	String code = "836200000005667800481000180975657313001589636081";
	
	@Test
	void test() {
		assertEquals(1, module.calculateVerifyingCodeWithmodule10(code));
	}
	
	@Test
	void testCalculateBankingVerifyingCodeWithmodule11() {
		assertEquals(3, module.calculateBoletoBancarioVerifyingCodeWithmodule11("83620000000566780048100018097566433001557389293831"));
	}
	
	@Test
	void testCalculateTaxRevenuesVerifyingCodeWithmodule11() {
		assertEquals(0, module.calculateBoletoArrecadacaoVerifyingCodeWithmodule11(code));
	}

}
