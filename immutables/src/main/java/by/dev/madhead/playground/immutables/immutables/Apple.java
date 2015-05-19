package by.dev.madhead.playground.immutables.immutables;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Apple {
	public abstract String getColor();

	public abstract double getWeight();

	@Value.Auxiliary
	public String getName() {
		return "Apple";
	}
}
