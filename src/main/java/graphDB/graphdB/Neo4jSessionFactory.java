package graphDB.graphdB;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.neo4j.driver.exceptions.Neo4jException;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.ws.rs.Produces;

@ApplicationScoped
public class Neo4jSessionFactory {

    private static final String[] PACKAGES = {"graphDB.entity", "graphDB.entity.relation"};

    @ConfigProperty(name = "quarkus.neo4j.uri")
    private String databaseUri;

    @ConfigProperty(name = "quarkus.neo4j.authentication.username")
    private String username;

    @ConfigProperty(name = "quarkus.neo4j.authentication.password")
    private String password;

    @Produces
    public SessionFactory produceSessionFactory() {
        Configuration neoConfig = new Configuration.Builder().uri(databaseUri).credentials(username, password)
                .useNativeTypes().build();
        try {
            return new SessionFactory(neoConfig, PACKAGES);
        } catch (Neo4jException e) {
            return null;
        }

    }

    public void disposeSessionFactory(@Disposes SessionFactory sessionFactory) {
        sessionFactory.close();
    }

}
