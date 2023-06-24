package com.elevator.entity;

public class Floor {
    int floorNumber;
    ButtonPanel buttonPanel;

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public ButtonPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(ButtonPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    @Override
    public String toString() {
        return "Floor{" +
                "floorNumber=" + floorNumber +
                ", buttonPanel=" + buttonPanel +
                '}';
    }

    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }
}
