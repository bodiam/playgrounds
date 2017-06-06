package by.dev.madhead.playgrounds.gradle.service.processor;

import by.dev.madhead.playgrounds.gradle.model.weight.ConvertWeights;
import by.dev.madhead.playgrounds.gradle.model.weight.ConvertWeightsSoap;
import by.dev.madhead.playgrounds.gradle.model.weight.WeightUnit;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class WeightProcessor implements Processor {
	@Override
	public void process(Exchange exchange) throws Exception {
		final ConvertWeights service = new ConvertWeights();
		final ConvertWeightsSoap client = service.getConvertWeightsSoap();

		final double howManyGramsInAnOunce = client.convertWeight(1, WeightUnit.OUNCES_AVOIR, WeightUnit.GRAMS);

		System.out.println(howManyGramsInAnOunce);
	}
}
