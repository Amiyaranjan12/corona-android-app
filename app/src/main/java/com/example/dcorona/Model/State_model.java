package com.example.dcorona.Model;

public class State_model {

    private String state_name;
    private String state_active;
    private String state_death;
    private String state_recover;

    public State_model(String state_name, String state_active, String state_death, String state_recover) {

        this.state_active=state_active;
        this.state_name=state_name;
        this.state_death=state_death;
        this.state_recover=state_recover;
    }
    public State_model() {

    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getState_active() {
        return state_active;
    }

    public void setState_active(String state_active) {
        this.state_active = state_active;
    }

    public String getState_death() {
        return state_death;
    }

    public void setState_death(String state_death) {
        this.state_death = state_death;
    }

    public String getState_recover() {
        return state_recover;
    }

    public void setState_recover(String state_recover) {
        this.state_recover = state_recover;
    }



}
