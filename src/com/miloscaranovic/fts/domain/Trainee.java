package com.miloscaranovic.fts.domain;

import java.io.Serializable;

public class Trainee extends Person implements Serializable {

    private int traineeID;

    public int getTraineeID() {
        return traineeID;
    }

    public void setTraineeID(int traineeID) {
        this.traineeID = traineeID;
    }

}
