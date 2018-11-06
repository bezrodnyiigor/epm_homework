package com.epm.recipe.persistence;

import com.epm.recipe.domain.Recipe;

import java.util.List;

public interface RecipeRepository {
    List<Recipe> findAll();
    Recipe getById(Long id);
    void insert(Recipe recipe);
    void update(Recipe recipe);
    void delete(Long recipeId);
}