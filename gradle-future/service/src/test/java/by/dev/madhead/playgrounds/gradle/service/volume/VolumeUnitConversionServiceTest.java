package by.dev.madhead.playgrounds.gradle.service.volume;

import by.dev.madhead.playgrounds.gradle.model.volume.VolumeUnitSoap;
import by.dev.madhead.playgrounds.gradle.model.volume.Volumes;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VolumeUnitConversionServiceTest {
	@InjectMocks
	private VolumeUnitConversionService subject = new VolumeUnitConversionService();

	@Mock
	private VolumeUnitSoap convertVolumesSoapClient;

	@BeforeMethod(alwaysRun = true)
	public void injectDoubles() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void convert() {
		Mockito
				.when(convertVolumesSoapClient.changeVolumeUnit(1, Volumes.GALLON_US_LIQUID, Volumes.LITER))
				.thenReturn(3.784296607113191);

		Assert.assertEquals(
				subject.convert(1, "gallonUSLiquid", "liter"),
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
