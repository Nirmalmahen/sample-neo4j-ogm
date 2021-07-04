package graphDB.service;

import graphDB.entity.*;
import graphDB.entity.relation.*;
import graphDB.graphdB.Neo4jSessionFactory;
import graphDB.utils.BaseResponse;
import graphDB.utils.GraphRequest;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

@ApplicationScoped
public class GraphHelperService {

    @Inject
    Neo4jSessionFactory sessionFactory;

    public CompletableFuture<BaseResponse> insert(GraphRequest request) {

        CompletableFuture<BaseResponse> future = new CompletableFuture<>();
        CompletableFuture.supplyAsync(() -> {
            insertGraph(request);
            future.complete(BaseResponse.create(true, "Inserted."));
            return future;
        }).exceptionally(throwable -> {
            future.complete(BaseResponse.create(false, throwable.getCause().getMessage()));
            return null;
        });
        return future;
    }

    private void insertGraph(GraphRequest request) {
        SessionFactory factory = sessionFactory.produceSessionFactory();
        Session session =factory.openSession();

        Diagnosis diagnosis = processDiagnosis(request.getDiagnosis(), session);
        Gender gender = processGender(request.getGender(), session, diagnosis);
        Age age = processAge(session, gender, request.getAge());
        Ethnicity ethnicity = processEthnicity(session, age, request.getEthnicity());
        HPI hpi = processHpi(session, ethnicity, request.getHpi());
        FamilyHistory familyHistory = processFamilyHistory(session, hpi, request.getHistory());
        Medication medication = processMedication(session, familyHistory, request.getMedicine());
        processDosage(request.getDosage(), session, medication);
        sessionFactory.disposeSessionFactory(factory);
    }

    private <T> T check(Session session, String value, Class<T> tClass) {
        return session.load(tClass, value, 1);
    }

    private Diagnosis processDiagnosis(String diagnosisValue, Session session) {
        Diagnosis diagnosis = new Diagnosis(diagnosisValue);
        Diagnosis resultDiagnosis = check(session, diagnosis.getValue(), Diagnosis.class);
        if (resultDiagnosis == null) {
            session.save(diagnosis, 0);
            return diagnosis;
        }
        return resultDiagnosis;
    }

    private Gender processGender(String genderValue, Session session, Diagnosis diagnosis) {
        Gender gender = new Gender(genderValue);
        Gender resultGender = check(session, gender.getValue(), Gender.class);
        if (resultGender == null) {
            gender.getRelationList().add(diagnosis.getValue());
            GenderRelationEntity diagnosedIn = new GenderRelationEntity(diagnosis, gender);
            session.save(diagnosedIn, 0);
            return gender;
        }
        if (resultGender.getRelationList().contains(diagnosis.getValue())) {
            resultGender.getIncomingRelation().stream()
                    .filter(x -> x.getStart().getValue().equals(diagnosis.getValue())).forEach(x -> {
                x.setWeight(x.getWeight() + 1);
                session.save(x, 0);
            });
        } else {
            resultGender.getRelationList().add(diagnosis.getValue());
            GenderRelationEntity diagnosedIn = new GenderRelationEntity(diagnosis, resultGender);
            session.save(diagnosedIn, 0);
        }
        return resultGender;
    }

    private Age processAge(Session session, Gender gender, int ageValue) {
        Age age = new Age(ageValue);
        Age resultAge = check(session, age.getValue(), Age.class);
        if (resultAge == null) {
            age.getRelationList().add(gender.getValue());
            AgeRelationEntity aged = new AgeRelationEntity(gender, age);
            session.save(aged, 0);
            return age;
        }
        if (resultAge.getRelationList().contains(gender.getValue())) {
            resultAge.getIncomingRelation().stream().filter(x -> x.getStart().getValue().equals(gender.getValue()))
                    .forEach(x -> {
                        x.setWeight(x.getWeight() + 1);
                        session.save(x, 0);
                    });
        } else {
            resultAge.getRelationList().add(gender.getValue());
            AgeRelationEntity aged = new AgeRelationEntity(gender, resultAge);
            session.save(aged, 0);
        }
        return resultAge;
    }

    private Ethnicity processEthnicity(Session session, Age age, String race) {
        Ethnicity ethnicity = new Ethnicity(race);
        Ethnicity resultEthnicity = check(session, ethnicity.getValue(), Ethnicity.class);
        if (resultEthnicity == null) {
            ethnicity.getRelationList().add(age.getValue());
            EthnicRelationEntity ethnicRelationEntity = new EthnicRelationEntity(age, ethnicity);
            session.save(ethnicRelationEntity, 0);
            return ethnicity;
        }
        if (resultEthnicity.getRelationList().contains(age.getValue())) {
            resultEthnicity.getIncomingRelation().stream().filter(x -> x.getStart().getValue().equals(age.getValue()))
                    .forEach(x -> {
                        x.setWeight(x.getWeight() + 1);
                        session.save(x, 0);
                    });
        } else {
            resultEthnicity.getRelationList().add(age.getValue());
            EthnicRelationEntity ethnicRelationEntity = new EthnicRelationEntity(age, resultEthnicity);
            session.save(ethnicRelationEntity, 0);
        }
        return resultEthnicity;
    }

    private HPI processHpi(Session session, Ethnicity ethnicity, String hpiValue) {
        HPI hpi = new HPI(hpiValue);
        HPI resultHpi = check(session, hpi.getValue(), HPI.class);
        if (resultHpi == null) {
            hpi.getRelationList().add(ethnicity.getValue());
            HPIRelationEntity hpiRelationEntity = new HPIRelationEntity(ethnicity, hpi);
            session.save(hpiRelationEntity, 0);
            return hpi;
        }
        if (resultHpi.getRelationList().contains(ethnicity.getValue())) {
            resultHpi.getIncomingRelation().stream().filter(x -> x.getStart().getValue().equals(ethnicity.getValue()))
                    .forEach(x -> {
                        x.setWeight(x.getWeight() + 1);
                        session.save(x, 0);
                    });
        } else {
            resultHpi.getRelationList().add(ethnicity.getValue());
            HPIRelationEntity hpiRelationEntity = new HPIRelationEntity(ethnicity, resultHpi);
            session.save(hpiRelationEntity, 0);
        }
        return resultHpi;
    }

    private FamilyHistory processFamilyHistory(Session session, HPI hpi, String history) {
        FamilyHistory familyHistory = new FamilyHistory(history);
        FamilyHistory resultHistory = check(session, familyHistory.getValue(), FamilyHistory.class);
        if (resultHistory == null) {
            familyHistory.getRelationList().add(hpi.getValue());
            HistoryRelationEntity historyRelationEntity = new HistoryRelationEntity(hpi, familyHistory);
            session.save(historyRelationEntity, 0);
            return familyHistory;
        }
        if (resultHistory.getRelationList().contains(hpi.getValue())) {
            resultHistory.getIncomingRelation().stream().filter(x -> x.getStart().getValue().equals(hpi.getValue()))
                    .forEach(x -> {
                        x.setWeight(x.getWeight() + 1);
                        session.save(x, 0);
                    });
        } else {
            resultHistory.getRelationList().add(hpi.getValue());
            HistoryRelationEntity historyRelationEntity = new HistoryRelationEntity(hpi, resultHistory);
            session.save(historyRelationEntity, 0);
        }
        return resultHistory;
    }

    private Medication processMedication(Session session, FamilyHistory familyHistory, String medicine) {
        Medication medication = new Medication(medicine);
        Medication resultMedication = check(session, medication.getValue(), Medication.class);
        if (resultMedication == null) {
            medication.getRelationList().add(familyHistory.getValue());
            PrescriptionRelationEntity prescription = new PrescriptionRelationEntity(familyHistory, medication);
            session.save(prescription, 0);
            return medication;
        }
        if (resultMedication.getRelationList().contains(familyHistory.getValue())) {
            resultMedication.getIncomingRelation().stream()
                    .filter(x -> x.getStart().getValue().equals(familyHistory.getValue())).forEach(x -> {
                x.setWeight(x.getWeight() + 1);
                session.save(x, 0);
            });
        } else {
            resultMedication.getRelationList().add(familyHistory.getValue());
            PrescriptionRelationEntity prescription = new PrescriptionRelationEntity(familyHistory, resultMedication);
            session.save(prescription, 0);
        }
        return resultMedication;
    }

    private void processDosage(String dosageValue, Session session, Medication medication) {
        Dosage dosage = new Dosage(dosageValue);
        Dosage resultDosage = check(session, dosage.getValue(), Dosage.class);
        if (resultDosage == null) {
            dosage.getRelationList().add(medication.getValue());
            DosageRelationEntity dosageRelationEntity = new DosageRelationEntity(medication, dosage);
            session.save(dosageRelationEntity, 0);
        } else {
            if (resultDosage.getRelationList().contains(medication.getValue())) {
                resultDosage.getIncomingRelation().stream()
                        .filter(x -> x.getStart().getValue().equals(medication.getValue())).forEach(x -> {
                    x.setWeight(x.getWeight() + 1);
                    session.save(x, 0);
                });
            } else {
                resultDosage.getRelationList().add(medication.getValue());
                DosageRelationEntity dosageRelationEntity = new DosageRelationEntity(medication, resultDosage);
                session.save(dosageRelationEntity, 0);
            }
        }
    }

}
