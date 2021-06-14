package com.org.capg.recipe.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class ExceptionDetails {

    /**
     * The timestamp.
     */
    private Date timestamp;

    /**
     * The message.
     */
    private String message;

    /**
     * The details.
     */
    private String details;

}
