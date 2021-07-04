package graphDB.entity.relation;

import graphDB.entity.Ethnicity;
import graphDB.entity.HPI;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "HAS_ILLNESS")
@Data
public class HPIRelationEntity {

    @Id
    private final String name = "HAS_ILLNESS";

    @StartNode
    private Ethnicity start;

    @EndNode
    private HPI end;

    @Property
    private int weight;

    public HPIRelationEntity() {
    }

    public HPIRelationEntity(Ethnicity start, HPI end) {
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
