package by.dev.madhead.playground.immutables.immutables;

import by.dev.madhead.playground.immutables.annotations.Nullable;
import org.immutables.value.Value;

import java.util.Date;
import java.util.Optional;

@Value.Immutable
public abstract class Bottle {
	public static enum Material {
		GLASS, PLASTIC
	}

	public abstract Material getMaterial();

	@Value.Default
	public double getVolume() {
		return 0.0D;
	}

	public abstract Optional<String> getSticker();

	@Nullable
	public abstract Date productionDate();
}
