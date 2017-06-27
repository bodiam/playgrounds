package by.dev.madhead.playgrounds.gradle.service.weight;

import by.dev.madhead.playgrounds.gradle.model.weight.ConvertWeightsSoap;
import by.dev.madhead.playgrounds.gradle.model.weight.WeightUnit;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WeightUnitConversionServiceTest {
	@InjectMocks
	private WeightUnitConversionService subject = new WeightUnitConversionService();

	@Mock
	private ConvertWeightsSoap convertWeightsSoapClient;

	@BeforeMethod(alwaysRun = true)
	public void injectDoubles() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void convert() {
		Mockito
				.when(convertWeightsSoapClient.convertWeight(1, WeightUnit.OUNCES_TROY_APOTH, WeightUnit.GRAMS))
				.thenReturn(32.4);

		Assert.assertEquals(
				subject.convert(1, "OuncesTroyApoth", "Grams"),
				32.4,
				1e-15
		);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void convertUnknownUnit() {
		Assert.assertEquals(
				subject.convert(1, "UnknownUnit", "Grams"),
				32.4,
				1e-15
		);
	}
}
