package graphDB.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import graphDB.entity.relation.DosageRelationEntity;
import lombok.Data;

@NodeEntity
@Data
public class Dosage {

    @Id
    private String value;

    @Relationship(value = "DOSAGE", direction = "INCOMING")
    private final Set<DosageRelationEntity> incomingRelation = new HashSet<>();

    @Property
    private final Set<String> relationList = new HashSet<>();

    public Dosage() {
    }

    public Dosage(String value) {
        this.value = value;
    }
}
