package by.dev.madhead.playgrounds.gradle.service.weight;

import by.dev.madhead.playgrounds.gradle.model.weight.ConvertWeightsSoap;
import by.dev.madhead.playgrounds.gradle.model.weight.WeightUnit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/weight")
@Produces(MediaType.TEXT_PLAIN)
@Api(
		description = "Weight Unit Convertor",
		tags = {
				"weight",
				"conversion"
		}
)
public class WeightUnitConversionService {
	@Resource
	private ConvertWeightsSoap convertWeightsSoapClient;

	@GET
	@ApiOperation(value = "Convert weight units", response = Double.class)
	public double convert(
			@QueryParam("weight") final double weight,
			@QueryParam("from") final String from,
			@QueryParam("to") final String to
	) {
		return convertWeightsSoapClient.convertWeight(
				weight,
				WeightUnit.fromValue(from),
				WeightUnit.fromValue(to)
		);
	}
}
