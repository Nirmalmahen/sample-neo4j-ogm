package graphDB.entity;

import graphDB.entity.relation.HPIRelationEntity;
import graphDB.entity.relation.HistoryRelationEntity;
import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
@Data
public class HPI {

    @Id
    private String value;

    @Relationship(value = "HAS_ILLNESS", direction = "INCOMING")
    private final Set<HPIRelationEntity> incomingRelation = new HashSet<>();

    @Relationship(value = "HAS_FAMILY_HISTORY")
    private final Set<HistoryRelationEntity> relation = new HashSet<>();

    @Property
    private final Set<String> relationList = new HashSet<>();

    public HPI() {
    }

    public HPI(String value) {
        this.value = value;
    }
}
