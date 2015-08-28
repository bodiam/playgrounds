package by.dev.madhead.playgrounds.guice.annotations

import com.google.inject.BindingAnnotation
import java.lang.annotation.ElementType.FIELD
import java.lang.annotation.ElementType.METHOD
import java.lang.annotation.ElementType.PARAMETER
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import java.lang.annotation.Target

@BindingAnnotation
@Target(FIELD, PARAMETER, METHOD)
@Retention(RUNTIME)
annotation class English
