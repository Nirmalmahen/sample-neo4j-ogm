package graphDB.entity.relation;

import graphDB.entity.Age;
import graphDB.entity.Ethnicity;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "IS_OF_RACE")
@Data
public class EthnicRelationEntity {

    @Id
    private final String name = "IS_OF_RACE";

    @StartNode
    private Age start;

    @EndNode
    private Ethnicity end;

    @Property
    private int weight;

    public EthnicRelationEntity() {
    }

    public EthnicRelationEntity(Age start, Ethnicity end) {
        this.start = start;
        this.end = end;
        this.weight = 1;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
