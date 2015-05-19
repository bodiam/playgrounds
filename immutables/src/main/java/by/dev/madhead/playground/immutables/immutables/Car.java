package by.dev.madhead.playground.immutables.immutables;

import org.immutables.value.Value;

@Value.Immutable
public interface Car {
	double getSweptVolume();

	int getDoorsCount();

	double getMaxSpeed();

	Person getOwner();
}
