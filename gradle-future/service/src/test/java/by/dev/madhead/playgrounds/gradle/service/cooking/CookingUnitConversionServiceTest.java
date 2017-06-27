package by.dev.madhead.playgrounds.gradle.service.cooking;

import by.dev.madhead.playgrounds.gradle.model.cooking.CookingUnitSoap;
import by.dev.madhead.playgrounds.gradle.model.cooking.Cookings;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CookingUnitConversionServiceTest {
	@InjectMocks
	private CookingUnitConversionService subject = new CookingUnitConversionService();

	@Mock
	private CookingUnitSoap convertCookingSoapClient;

	@BeforeMethod(alwaysRun = true)
	public void injectDoubles() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void convert() {
		Mockito
				.when(convertCookingSoapClient.changeCookingUnit(1, Cookings.GALLON_US, Cookings.LITER))
				.thenReturn(3.784296607113191);

		Assert.assertEquals(
				subject.convert(1, "gallonUS", "liter"),
				3.784296607113191,
				1e-15
		);
	}

	@Test(expectedExceptions = IllegalArgumentException.class)
	public void convertUnknownUnit() {
		Assert.assertEquals(
				subject.convert(1, "UnknownUnit", "liter"),
				3.784296607113191,
				1e-15
		);
	}
}
