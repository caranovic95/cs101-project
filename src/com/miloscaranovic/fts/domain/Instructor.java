package com.miloscaranovic.fts.domain;

import java.io.Serializable;

public class Instructor extends Person implements Serializable {

    private int instructorID;

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

}
