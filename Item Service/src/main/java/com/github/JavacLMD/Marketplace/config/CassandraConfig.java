package com.github.JavacLMD.Marketplace.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CassandraCqlSessionFactoryBean;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories;

import java.util.List;

@Configuration
@EnableReactiveCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${spring.data.cassandra.keyspace-name}")
    private String keyspace = "project_two";

    @Value("${spring.data.cassandra.contact-points}")
    private String contactPoints = "localhost";

    @Value("${spring.data.cassandra.schema-action}")
    private SchemaAction schemaAction = SchemaAction.CREATE_IF_NOT_EXISTS;

    @Override
    protected String getKeyspaceName() {
        return keyspace;
    }

    @Override
    protected String getContactPoints() {
        return contactPoints;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return schemaAction;
    }

    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"com.github.JavacLMD.Marketplace.domain"};
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        CreateKeyspaceSpecification specification =
                CreateKeyspaceSpecification.createKeyspace(keyspace)
                        .ifNotExists()
                        .with(KeyspaceOption.DURABLE_WRITES, true);
        return List.of(specification);
    }

}
