package by.dev.madhead.playground.immutables.immutables;

import org.immutables.value.Value;

@Value.Immutable
public @interface MyAnnotation {
	String[] value();

	boolean enable() default true;
}
