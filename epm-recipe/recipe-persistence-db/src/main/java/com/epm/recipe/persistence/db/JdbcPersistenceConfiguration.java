package com.epm.recipe.persistence.db;

import com.epm.recipe.persistence.RecipeRepository;
//import com.epm.recipe.persistence.jdbc.JdbcRecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@ComponentScan({ "com.epm.recipe.persistence.jdbc" })
@Configuration

public class JdbcPersistenceConfiguration {
    @Autowired
    DataSource dataSource;

//    @Bean
//    public DataSource dataSource() {
//
//        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//        EmbeddedDatabase db = builder
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript("schema.sql")
//                .addScript("test-data.sql")
//                .build();
//
//        return db;
//    }

    @Bean(name = "dataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/recipe_db");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
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
