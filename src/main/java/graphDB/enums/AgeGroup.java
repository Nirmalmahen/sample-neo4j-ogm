package graphDB.enums;

public enum AgeGroup {
    CHILD, TEEN, TWENTIES, THIRTIES, FORTIES, FIFTIES, SIXTIES, SEVENTIES, OLD;

    public static AgeGroup getAge(int age) {
        if (age >= 0 && age <= 15) {
            return AgeGroup.CHILD;
        }
        if (age >= 16 && age <= 20) {
            return AgeGroup.TEEN;
        }
        if (age >= 21 && age <= 30) {
            return AgeGroup.TWENTIES;
        }
        if (age >= 31 && age <= 40) {
            return AgeGroup.THIRTIES;
        }
        if (age >= 41 && age <= 50) {
            return AgeGroup.FORTIES;
        }
        if (age >= 51 && age <= 60) {
            return AgeGroup.FIFTIES;
        }
        if (age >= 61 && age <= 70) {
            return AgeGroup.SIXTIES;
        }
        if (age >= 71 && age <= 80) {
            return AgeGroup.SEVENTIES;
        } else
            return AgeGroup.OLD;
    }
}

