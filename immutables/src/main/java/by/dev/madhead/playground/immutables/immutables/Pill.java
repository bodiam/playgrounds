package by.dev.madhead.playground.immutables.immutables;

import org.immutables.value.Value;

@Value.Immutable(intern = true)
public interface Pill {
	@Value.Parameter
	String color();
}
