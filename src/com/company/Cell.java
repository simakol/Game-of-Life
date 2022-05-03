package com.company;

public class Cell {
    private boolean state;
    private int aliveNeighbours;

    public Cell(boolean state, int aliveNeighbours) {
        this.state = state;
        this.aliveNeighbours = aliveNeighbours;
    }

    public Cell() {
        this.state = false;
        this.aliveNeighbours = 0;
    }

    public void setState(boolean newState) {
        this.state = newState;
    }

    public boolean getState() {
        return this.state;
    }

    public void setAliveNeighbours(int newAmount) {
        if (newAmount >= 0 && newAmount <= 8) this.aliveNeighbours = newAmount;
        else this.aliveNeighbours = 0;

    }

    public int getAliveNeighbours() {
        return this.aliveNeighbours;
    }
}