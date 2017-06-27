package by.dev.madhead.playgrounds.gradle.service.cooking;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CookingUnitConversionServiceIntegrationTest {
	@Test
	public void convert() throws IOException {
		final HttpClient client = HttpClients.createDefault();
		final HttpGet request = new HttpGet("http://localhost:8080/cooking?value=1&from=gallonUS&to=liter");
		final HttpResponse response = client.execute(request);

		Assert.assertEquals(response.getStatusLine().getStatusCode(), HttpStatus.SC_OK);
		Assert.assertEquals(EntityUtils.toString(response.getEntity()), "3.784296607113191");
	}
}
