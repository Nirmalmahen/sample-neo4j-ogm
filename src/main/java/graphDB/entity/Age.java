package graphDB.entity;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import graphDB.entity.relation.AgeRelationEntity;
import graphDB.entity.relation.EthnicRelationEntity;
import graphDB.enums.AgeGroup;
import lombok.Data;

@NodeEntity
@Data
public class Age {

    @Id
    private String value;

    @Relationship(type = "AGED", direction = "INCOMING")
    private final Set<AgeRelationEntity> incomingRelation = new HashSet<>();

    @Relationship(type = "IS_OF_RACE")
    private final Set<EthnicRelationEntity> relation = new HashSet<>();

    @Property
    private final Set<String> relationList = new HashSet<>();

    public Age() {
    }

    public Age(int value) {
        this.value = AgeGroup.getAge(value).name();
    }
}
