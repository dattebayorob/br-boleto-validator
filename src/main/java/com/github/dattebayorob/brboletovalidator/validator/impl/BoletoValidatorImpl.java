package com.github.dattebayorob.brboletovalidator.validator.impl;

import com.github.dattebayorob.brboletovalidator.helpers.Converter;
import com.github.dattebayorob.brboletovalidator.helpers.Mask;
import com.github.dattebayorob.brboletovalidator.helpers.Module;
import com.github.dattebayorob.brboletovalidator.validator.BoletoValidator;

public final class BoletoValidatorImpl implements BoletoValidator{
	
	private final BoletoArrecadacaoValidator boletoArrecadacaoValidator;
	private final BoletoBancarioValidator boletoBancarioValidator;
	
	public BoletoValidatorImpl() {
		
		Converter converter = new Converter();
		Module module = new Module();
		
		this.boletoArrecadacaoValidator = new BoletoArrecadacaoValidator(module, converter);
		this.boletoBancarioValidator = new BoletoBancarioValidator(module, converter);
	}

	protected BoletoValidatorImpl(
			BoletoArrecadacaoValidator boletoArrecadacaoValidator,
			BoletoBancarioValidator boletoBancarioValidator
	) {
		this.boletoArrecadacaoValidator = boletoArrecadacaoValidator;
		this.boletoBancarioValidator = boletoBancarioValidator;
	}

	public boolean validate(String code) {
		String unmaskedCode = Mask.unmask(code);
		return boletoArrecadacaoValidator.isBoletoArrecadacaoCode(unmaskedCode)
					? boletoArrecadacaoValidator.validate(unmaskedCode)
					: boletoBancarioValidator.validate(unmaskedCode);
	}

}
