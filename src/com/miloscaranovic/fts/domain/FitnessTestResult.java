package com.miloscaranovic.fts.domain;

import java.io.Serializable;

public class FitnessTestResult implements Serializable {

    private int fitnessTestResultID;
    private FitnessTest fitnessTest;

    private String runningResult;
    private String weightLiftingResult;
    private String pushUpsResult;
    private String squatsResult;
    private String sitUpResult;
    private String otherResult;

    public FitnessTestResult(FitnessTest fitnessTest) {
        this.fitnessTest = fitnessTest;
        fitnessTest.setTrainingResult(this);
    }

    public FitnessTest getFitnessTest() {
        return fitnessTest;
    }

    public void setFitnessTest(FitnessTest fitnessTest) {
        this.fitnessTest = fitnessTest;
    }

    public String getRunningResult() {
        return runningResult;
    }

    public void setRunningResult(String runningResult) {
        this.runningResult = runningResult;
    }

    public String getWeightLiftingResult() {
        return weightLiftingResult;
    }

    public void setWeightLiftingResult(String weightLiftingResult) {
        this.weightLiftingResult = weightLiftingResult;
    }

    public String getPushUpsResult() {
        return pushUpsResult;
    }

    public void setPushUpsResult(String pushUpsResult) {
        this.pushUpsResult = pushUpsResult;
    }

    public String getSquatsResult() {
        return squatsResult;
    }

    public void setSquatsResult(String squatsResult) {
        this.squatsResult = squatsResult;
    }

    public String getSitUpResult() {
        return sitUpResult;
    }

    public void setSitUpResult(String sitUpResult) {
        this.sitUpResult = sitUpResult;
    }

    public String getOtherResult() {
        return otherResult;
    }

    public void setOtherResult(String otherResult) {
        this.otherResult = otherResult;
    }

    public int getFitnessTestResultID() {
        return fitnessTestResultID;
    }

    public void setFitnessTestResultID(int fitnessTestResultID) {
        this.fitnessTestResultID = fitnessTestResultID;
    }

}
