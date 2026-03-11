package com.example.springlabb.DTO;

import jakarta.validation.Payload;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@NotNull
@NotEmpty
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoEntry {
    String message() default "{Must not be null or empty}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default {};

}
