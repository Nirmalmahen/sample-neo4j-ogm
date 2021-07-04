package graphDB.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import graphDB.entity.relation.EthnicRelationEntity;
import graphDB.entity.relation.HPIRelationEntity;
import graphDB.enums.EthnicGroup;
import lombok.Data;

@NodeEntity
@Data
public class Ethnicity {

    @Id
    private String value;

    @Relationship(type = "IS_OF_RACE", direction = "INCOMING")
    private final Set<EthnicRelationEntity> incomingRelation = new HashSet<>();

    @Relationship(value = "HAS_ILLNESS")
    private final Set<HPIRelationEntity> relation = new HashSet<>();

    @Property
    private final Set<String> relationList = new HashSet<>();

    public Ethnicity() {
    }

    public Ethnicity(String value) {
        this.value = EthnicGroup.valueOf(value).name();
    }
}
