package graphDB.entity.relation;

import graphDB.entity.Dosage;
import graphDB.entity.Medication;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "DOSAGE")
@Data
public class DosageRelationEntity {

    @Id
    private final String name = "DOSAGE";

    @StartNode
    private Medication start;

    @EndNode
    private Dosage end;

    @Property
    private int weight;

    public DosageRelationEntity() {
    }

    public DosageRelationEntity(Medication start, Dosage end) {
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
