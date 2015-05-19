package by.dev.madhead.playground.immutables.immutables;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface House {
	List<Person> getTenants();
}
