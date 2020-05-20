package com.mercurius.task.annotation;

import com.mercurius.task.autoconfigure.JobParserAutoConfigurartion;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(JobParserAutoConfigurartion.class)
public @interface EnableElasticJob {
}
