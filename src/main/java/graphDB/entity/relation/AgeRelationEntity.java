package graphDB.entity.relation;

import graphDB.entity.Age;
import graphDB.entity.Gender;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "AGED")
@Data
public class AgeRelationEntity {

    @Id
    private final String name = "AGED";

    @StartNode
    Gender start;

    @EndNode
    Age end;

    @Property
    private int weight;

    public AgeRelationEntity() {
    }

    public AgeRelationEntity(Gender start, Age end) {
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
