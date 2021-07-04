package graphDB.entity.relation;

import graphDB.entity.FamilyHistory;
import graphDB.entity.Medication;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "PRESCRIBED")
@Data
public class PrescriptionRelationEntity {

    @Id
    private final String name = "PRESCRIBED";

    @StartNode
    private FamilyHistory start;

    @EndNode
    private Medication end;

    @Property
    private int weight;

    public PrescriptionRelationEntity() {
    }

    public PrescriptionRelationEntity(FamilyHistory start, Medication end) {
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
