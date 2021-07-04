package graphDB.entity.relation;

import graphDB.entity.Diagnosis;
import graphDB.entity.Gender;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "DIAGNOSED_IN")
@Data
public class GenderRelationEntity {

    @Id
    private final String name = "DIAGNOSED_IN";

    @StartNode
    private Diagnosis start;

    @EndNode
    private Gender end;

    @Property
    private int weight;

    public GenderRelationEntity() {
    }

    public GenderRelationEntity(Diagnosis start, Gender end) {
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