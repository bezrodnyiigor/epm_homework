package com.epm.recipe.persistence.jdbc.config;

import com.epm.recipe.persistence.RecipeRepository;
import com.epm.recipe.persistence.jdbc.JdbcRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


@ComponentScan({ "com.epm.recipe.persistence.jdbc" })
@Configuration

public class JdbcPersistenceConfiguration {
    @Autowired
    DataSource dataSource;

    @Bean
    public DataSource dataSource() {

        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .addScript("test-data.sql")
                .build();

        return db;
    }
    @Bean
    public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean()
    public RecipeRepository recipeRepository() {
        return new JdbcRecipeRepository();
    }

}
