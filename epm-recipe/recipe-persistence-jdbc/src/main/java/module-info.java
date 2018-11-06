module recipe.persistence.jdbc {
    requires recipe.domain;
    requires recipe.persistence;
    requires spring.context;
    requires spring.jdbc;
    requires spring.beans;
    requires java.sql;


    opens com.epm.recipe.persistence.jdbc to spring.core;
    opens com.epm.recipe.persistence.jdbc.config to spring.core;
    exports com.epm.recipe.persistence.jdbc;
    exports com.epm.recipe.persistence.jdbc.config;

}