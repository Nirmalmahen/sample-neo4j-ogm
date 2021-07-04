package graphDB.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import graphDB.entity.relation.AgeRelationEntity;
import graphDB.entity.relation.GenderRelationEntity;
import lombok.Data;

@NodeEntity
@Data
public class Gender {

    @Id
    private String value;

    @Relationship(type = "DIAGNOSED_IN", direction = "INCOMING")
    private final Set<GenderRelationEntity> incomingRelation = new HashSet<>();

    @Relationship(type = "AGED")
    private final Set<AgeRelationEntity> relation = new HashSet<>();

    @Property
    private final Set<String> relationList = new HashSet<>();

    public Gender() {
    }

    public Gender(String value) {
        this.value = value;
    }
}
