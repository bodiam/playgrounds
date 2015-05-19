package by.dev.madhead.playground.immutables.immutables;

import org.immutables.value.Value;

@Value.Immutable
@Value.Style(init = "set*")
public interface CPU {
	int getFrequency();

	int getCoresCount();
}
