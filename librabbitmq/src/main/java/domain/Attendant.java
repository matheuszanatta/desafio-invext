package domain;

import dto.Theme;

public class Attendant {
    private String name;
    private Theme team;
    private int currentRequisitions;

    public Attendant(String name, Theme team, int currentRequisitions) {
        this.name = name;
        this.team = team;
        this.currentRequisitions = currentRequisitions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Theme getTeam() {
        return team;
    }

    public void setTeam(Theme team) {
        this.team = team;
    }

    public int getCurrentRequisitions() {
        return currentRequisitions;
    }

    public void setCurrentRequisitions(int currentRequisitions) {
        this.currentRequisitions = currentRequisitions;
    }
}
