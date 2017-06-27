package by.dev.madhead.playgrounds.gradle.service.cooking;

import by.dev.madhead.playgrounds.gradle.model.cooking.CookingUnitSoap;
import by.dev.madhead.playgrounds.gradle.model.cooking.Cookings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/cooking")
@Produces(MediaType.TEXT_PLAIN)
@Api(
		description = "Cooking Unit Convertor",
		tags = {
				"cooking",
				"conversion"
		}
)
public class CookingUnitConversionService {
	@Autowired
	private CookingUnitSoap convertCookingSoapClient;

	@GET
	@ApiOperation(value = "Convert cooking units", response = Double.class)
	public double convert(
			@QueryParam("value") final double value,
			@QueryParam("from") final String from,
			@QueryParam("to") final String to
	) {
		return convertCookingSoapClient.changeCookingUnit(
				value,
				Cookings.fromValue(from),
				Cookings.fromValue(to)
		);
	}
}
