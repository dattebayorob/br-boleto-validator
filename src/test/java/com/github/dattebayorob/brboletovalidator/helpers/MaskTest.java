package com.github.dattebayorob.brboletovalidator.helpers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MaskTest {

	@Test
	@DisplayName("Should remove all spaces from code")
	public void shouldRemoveAllBlankSpacesFromCode() {
		String code = "0000 0000 0000 0000";
		String actual = Mask.unmask(code);
		assertThat(actual).doesNotContain(" ");
	}
	
	@Test
	@DisplayName("Should remove all hyphens from code")
	public void shouldRemoveAllHyphenFromCode() {
		String code = "0000-0000-0000-0000";
		String actual = Mask.unmask(code);
		assertThat(actual).doesNotContain("-");
	}
	
	@Test
	@DisplayName("Should remove all dots from code")
	public void shouldRemoveAllDotsFromCode() {
		String code = "0000.0000.0000.0000";
		String actual = Mask.unmask(code);
		assertThat(actual).doesNotContain(".");
	}
	
	@Test
	@DisplayName("Should remove all blank spaces, hyphens and dots from code")
	public void shouldRemoveAllBlankSpacesHyphensAndDotsFromCode() {
		String code = "0000.0000 0000-0000.0000 0000-0000";
		String actual = Mask.unmask(code);
		assertThat(actual).doesNotContain(" ", "-", ".");
	}

}
