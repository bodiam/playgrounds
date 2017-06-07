package by.dev.madhead.playgrounds.gradle.service.volume;

import by.dev.madhead.playgrounds.gradle.model.volume.VolumeUnitSoap;
import by.dev.madhead.playgrounds.gradle.model.volume.Volumes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/volume")
@Produces(MediaType.TEXT_PLAIN)
@Api(
		description = "Volume Unit Convertor",
		tags = {
				"volume",
				"conversion"
		}
)
public class VolumeUnitConversionService {
	@Autowired
	private VolumeUnitSoap convertVolumesSoapClient;

	@GET
	@ApiOperation(value = "Convert volume units", response = Double.class)
	public double convert(
			@QueryParam("volume") final double volume,
			@QueryParam("from") final String from,
			@QueryParam("to") final String to
	) {
		return convertVolumesSoapClient.changeVolumeUnit(
				volume,
				Volumes.fromValue(from),
				Volumes.fromValue(to)
		);
	}
}
