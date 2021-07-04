package graphDB.entity.relation;

import graphDB.entity.FamilyHistory;
import graphDB.entity.HPI;
import lombok.Data;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "HAS_FAMILY_HISTORY")
@Data
public class HistoryRelationEntity {

    @Id
    private final String name = "HAS_FAMILY_HISTORY";

    @StartNode
    private HPI start;

    @EndNode
    private FamilyHistory end;

    @Property
    private int weight;

    public HistoryRelationEntity() {
    }

    public HistoryRelationEntity(HPI start, FamilyHistory end) {
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
