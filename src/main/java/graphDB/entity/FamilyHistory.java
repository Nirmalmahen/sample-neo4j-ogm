package graphDB.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import graphDB.entity.relation.HistoryRelationEntity;
import graphDB.entity.relation.PrescriptionRelationEntity;
import lombok.Data;

@NodeEntity
@Data
public class FamilyHistory {

    @Id
    private String value;

    @Relationship(value = "HAS_FAMILY_HISTORY", direction = "INCOMING")
    private final Set<HistoryRelationEntity> incomingRelation = new HashSet<>();

    @Relationship(value = "PRESCRIBED")
    private final Set<PrescriptionRelationEntity> relation = new HashSet<>();

    @Property
    private final Set<String> relationList = new HashSet<>();

    public FamilyHistory() {
    }

    public FamilyHistory(String value) {
        this.value = value;
    }
}
