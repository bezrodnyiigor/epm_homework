module recipe.persistence.db.main {
    requires java.sql;
    requires recipe.domain;
    requires recipe.persistence;
    requires spring.context;
    requires spring.jdbc;
    requires spring.beans;
    opens com.epm.recipe.persistence.db to spring.core;
    exports com.epm.recipe.persistence.db;
}