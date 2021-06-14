package com.org.capg.recipe.repository;

import com.org.capg.recipe.entity.RecipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The Interface RecipeRepository.
 */
@Repository
public interface RecipeRepository extends JpaRepository<RecipeEntity, Integer> {

}
