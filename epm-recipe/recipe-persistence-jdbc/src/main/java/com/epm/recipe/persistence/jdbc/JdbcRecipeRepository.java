package com.epm.recipe.persistence.jdbc;

import com.epm.recipe.domain.Recipe;
import com.epm.recipe.persistence.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class JdbcRecipeRepository  implements RecipeRepository{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Recipe> findAll() {
        String sql = "select * from recipe";
        return namedParameterJdbcTemplate.query(sql, (rs, rowNum) ->
                new Recipe(rs.getString("title"),rs.getLong("id")));

    }


    @Override
    public void insert(Recipe recipe) {
        String sql = "insert into recipe (id,title) values (:id,:title)";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id",recipe.id);
        namedParameters.addValue("title",recipe.title);
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

    @Override
    public void update(Recipe recipe) {
        if(getById(recipe.id) != null) {
            String sql = "update recipe SET title=:title where id=:id";
            MapSqlParameterSource namedParameters = new MapSqlParameterSource();
            namedParameters.addValue("id", recipe.id);
            namedParameters.addValue("title", recipe.title);
            namedParameterJdbcTemplate.update(sql, namedParameters);
        }else{
            throw new RuntimeException("Error update: There isn't Recipe with id= " + recipe.id);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from recipe where id=:id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id",id);
        namedParameterJdbcTemplate.update(sql, namedParameters);
    }

//    public void setDataSource(DataSource dataSource) {
//        this.dataSource = dataSource;
//        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
//    }

    @Override
    public Recipe getById(Long recipeId) {
        String sql = "select * from recipe where id=:id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("id",recipeId);
        return Objects.requireNonNull(
                namedParameterJdbcTemplate.queryForObject(sql, namedParameters, (resultSet,row) ->
                        new Recipe(resultSet.getString("title"),resultSet.getLong("id"))
                ));
    }


////    @Override
//    public void afterPropertiesSet(){
//        if (dataSource == null) {
//            throw new BeanCreationException("Must set dataSource on JdbcRecipeRepository");
//        }
//
//        if (namedParameterJdbcTemplate == null) {
//            throw new BeanCreationException("Null NamedParameterJdbcTemplate on JdbcRecipeRepository");
//        }
//    }
}
