package com.org.capg.recipe.exception;

/**
 * The Class RecipeNotFoundException.
 */
public class RecipeNotFoundException extends RuntimeException {

	/**
	 * Instantiates a new recipe not found exception.
	 *
	 * @param message
	 */
	public RecipeNotFoundException(String message) {
		super(message);
	}

}
