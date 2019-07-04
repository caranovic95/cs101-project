package com.miloscaranovic.fts.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FitnessTest implements Serializable {

    private int fitnessTestID;
    private Date date;
    private Trainee trainee;
    private Instructor instructor;
    private FitnessTestResult trainingResult;

    public FitnessTest(Date date, Trainee trainee, Instructor instructor) {
        this.date = date;
        this.trainee = trainee;
        this.instructor = instructor;
        this.trainingResult = new FitnessTestResult(this);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Trainee getTrainee() {
        return trainee;
    }

    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public FitnessTestResult getTrainingResult() {
        return trainingResult;
    }

    public void setTrainingResult(FitnessTestResult trainingResult) {
        this.trainingResult = trainingResult;
    }

    public int getFitnessTestID() {
        return fitnessTestID;
    }

    public void setFitnessTestID(int fitnessTestID) {
        this.fitnessTestID = fitnessTestID;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
        return trainee.toString() + ", " + sdf.format(date);
    }

}
