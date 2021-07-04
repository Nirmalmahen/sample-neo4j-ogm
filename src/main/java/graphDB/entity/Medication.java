package graphDB.entity;

import graphDB.entity.relation.DosageRelationEntity;
import graphDB.entity.relation.PrescriptionRelationEntity;
import lombok.Data;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

@NodeEntity
@Data
public class Medication {

    @Id
    private String value;

    @Relationship(value = "PRESCRIBED", direction = "INCOMING")
    private final Set<PrescriptionRelationEntity> incomingRelation = new HashSet<>();

    @Relationship(value = "DOSAGE")
    private final Set<DosageRelationEntity> relation = new HashSet<>();

    @Property
    private final Set<String> relationList = new HashSet<>();

    public Medication() {
    }

    public Medication(String value) {
        this.value = value;
    }
}
