package graphDB.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import graphDB.entity.relation.GenderRelationEntity;
import lombok.Data;

@NodeEntity
@Data
public class Diagnosis {

    @Id
    private String value;

    @Relationship(type = "DIAGNOSED_IN")
    private final Set<GenderRelationEntity> relation = new HashSet<>();

    public Diagnosis() {
    }

    public Diagnosis(String value) {
        this.value = value;
    }
}
