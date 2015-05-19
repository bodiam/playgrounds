package by.dev.madhead.playground.immutables.immutables;

import org.immutables.value.Value;

@Value.Immutable
public interface Bulb {
	enum BulbType {
		LED, INCANDESCENT, ARC, GAS
	}

	@Value.Parameter(order = 42)
	int getLumens();

	@Value.Parameter(order = 0)
	BulbType type();
}
