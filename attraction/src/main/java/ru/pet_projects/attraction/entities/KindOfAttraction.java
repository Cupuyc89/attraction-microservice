package ru.pet_projects.attraction.entities;

public enum KindOfAttraction {
    MUSEUM("Музей"), PARK("Парк"), PALACE("Дворец"),
    RESERVE("Заповедник"), ARCHAEOLOGICAL("Археологический объект"),
    CIRCUS("Цирк"), OTHER("Другое"), THEATER("Театр");

    private final String type;

    KindOfAttraction(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
