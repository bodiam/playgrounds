package by.dev.madhead.playground.immutables.immutables;

import org.immutables.value.Value;

@Value.Immutable
public abstract class Person {
	public abstract String getFirstName();

	public abstract String getLastName();

	public abstract int getAge();
}
