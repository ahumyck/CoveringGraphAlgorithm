package com.company;

public class StarPlan {
    private String starPlan;
    private int cost;

    public StarPlan(String starPlan, int cost) {
        this.starPlan = starPlan;
        this.cost = cost;
    }

    public void setStarPlan(String starPlan) {
        this.starPlan = starPlan;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return starPlan;
    }
}
