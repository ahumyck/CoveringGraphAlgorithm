package com.company.universe;

public class MutableGalaxy {
    private Galaxy galaxy;
    private boolean spaceToGrow;

    public MutableGalaxy(Galaxy galaxy, boolean spaceToGrow) {
        this.galaxy = galaxy;
        this.spaceToGrow = spaceToGrow;
    }

    public Galaxy getGalaxy() {
        return galaxy;
    }

    public void setGalaxy(Galaxy galaxy) {
        this.galaxy = galaxy;
    }

    public boolean isSpaceToGrow() {
        return spaceToGrow;
    }

    public void setSpaceToGrow(boolean spaceToGrow) {
        this.spaceToGrow = spaceToGrow;
    }

    public long getWeight(){
        return galaxy.getWeight();
    }

    @Override
    public String toString() {
        return galaxy.toString();
    }
}
