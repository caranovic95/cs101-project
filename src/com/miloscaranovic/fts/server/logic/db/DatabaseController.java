/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miloscaranovic.fts.server.logic.db;

import com.miloscaranovic.fts.domain.Admin;
import com.miloscaranovic.fts.domain.FitnessTest;
import com.miloscaranovic.fts.domain.FitnessTestResult;
import com.miloscaranovic.fts.domain.Instructor;
import com.miloscaranovic.fts.domain.Trainee;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseController {

    private static DatabaseController INSTANCE = new DatabaseController();

    private DatabaseController() {
    }

    private static java.sql.Connection con = null;
    private static String url = "jdbc:mysql://localhost/fts_baza";
    private static String username = "root";
    private static String password = "";

    public static DatabaseController getInstance() {
        if(INSTANCE==null)
        {
            INSTANCE = new DatabaseController();
        }
        
        return INSTANCE;
    }

    public ArrayList<Instructor> getAllInstructors() throws SQLException {
        ArrayList<Instructor> lista = new ArrayList<Instructor>();
        con = DriverManager.getConnection(url, username, password);
        String query = "SELECT instructorID, name, lastName, dateOfBirth FROM instructor";
        Statement st = (Statement) con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int instructorID = rs.getInt(1);
            String name = rs.getString(2);
            String lastName = rs.getString(3);
            Date dateOfBirth = rs.getDate(4);

            Instructor instruktor = new Instructor();
            instruktor.setInstructorID(instructorID);
            instruktor.setName(name);
            instruktor.setLastName(lastName);
            instruktor.setDateOfBirth(dateOfBirth);

            lista.add(instruktor);
        }
        rs.close();
        st.close();
        return lista;

    }

    public ArrayList<Trainee> getAllTrainees() throws SQLException {
        ArrayList<Trainee> lista = new ArrayList<Trainee>();
        con = DriverManager.getConnection(url, username, password);
        String query = "SELECT traineeID, name, lastname, dateOfBirth FROM trainee";
        Statement st = (Statement) con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int traineeId = rs.getInt(1);
            String name = rs.getString(2);
            String lastName = rs.getString(3);
            Date dateOfBirth = rs.getDate(4);

            Trainee trainee = new Trainee();
            trainee.setTraineeID(traineeId);
            trainee.setName(name);
            trainee.setLastName(lastName);
            trainee.setDateOfBirth(dateOfBirth);

            lista.add(trainee);
        }
        st.close();
        return lista;
    }

    public ArrayList<FitnessTest> getAllFitnessTest() throws SQLException {
        ArrayList<FitnessTest> listaTestova = new ArrayList<FitnessTest>();
        con = DriverManager.getConnection(url, username, password);
        String query = "SELECT ft.fitnessTestID, ft.date, i.instructorID, i.name, i.lastName,"
                + " i.dateOfBirth, t.traineeID, t.name, t.lastName, t.dateOfBirth, ftr.runningResult,"
                + " ftr.weightLiftingResult, ftr.pushUpsResult,"
                + "ftr.squatsResult, ftr.sitUpResult, ftr.otherResult, ftr.fitnessTestResultID "
                + "FROM fitnesstest ft "
                + "INNER JOIN instructor i on ft.instructor = i.instructorID "//insturktor iz fitTesta isto sto i instruktor iz id-a
                + "INNER JOIN trainee t on ft.trainee = t.traineeID "//fitnes test id (referenca na trainee id) isto sto i trainee id
                + "INNER JOIN fitnesstestresult ftr ON ft.trainingResult = ftr.fitnessTestResultID";
        Statement st = (Statement) con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int fitnessTestID = rs.getInt(1);
            Date fitnessTestDate = rs.getDate(2);
            int instructorID = rs.getInt(3);
            String instruktorName = rs.getString(4);
            String instruktorLastName = rs.getString(5);
            Date instruktorDateOfBirth = rs.getDate(6);
            int traineeID = rs.getInt(7);
            String traineeName = rs.getString(8);
            String traineeLastName = rs.getString(9);
            Date traineeDateOfBirth = rs.getDate(10);
            String ftrRunningResult = rs.getString(11);
            String ftrWeightLiftingResult = rs.getString(12);
            String ftrPushUpsResult = rs.getString(13);
            String ftrSquatsResult = rs.getString(14);
            String ftrSitUpResult = rs.getString(15);
            String ftrOtherResult = rs.getString(16);
            int ftrId = rs.getInt(17);

            Instructor inst1 = new Instructor();
            inst1.setInstructorID(instructorID);
            inst1.setName(instruktorName);
            inst1.setLastName(instruktorLastName);
            inst1.setDateOfBirth(traineeDateOfBirth);
            Trainee trainee1 = new Trainee();
            trainee1.setTraineeID(traineeID);
            trainee1.setName(traineeName);
            trainee1.setLastName(traineeLastName);
            trainee1.setDateOfBirth(traineeDateOfBirth);
            FitnessTest ft1 = new FitnessTest(fitnessTestDate, trainee1, inst1);
            ft1.setFitnessTestID(fitnessTestID);
            FitnessTestResult ftr1 = new FitnessTestResult(ft1);

            ftr1.setFitnessTestResultID(ftrId);
            ftr1.setOtherResult(ftrOtherResult);
            ftr1.setPushUpsResult(ftrPushUpsResult);
            ftr1.setRunningResult(ftrRunningResult);
            ftr1.setSitUpResult(ftrSitUpResult);
            ftr1.setSquatsResult(ftrSquatsResult);
            ftr1.setWeightLiftingResult(ftrWeightLiftingResult);

            listaTestova.add(ft1);

        }
        st.close();
        return listaTestova;
    }

    public void insertFitnessTest(FitnessTest fitnessTest) throws SQLException {
        insertFitnessTestResult(fitnessTest.getTrainingResult());

        con = DriverManager.getConnection(url, username, password);
        PreparedStatement st = (PreparedStatement) con.prepareStatement("INSERT INTO fitnesstest(date, trainee, instructor, trainingResult) " + "VALUES( ?,  ?,  ?,  ?)");
        st.setDate(1, new java.sql.Date(fitnessTest.getDate().getTime()));
        st.setInt(2, fitnessTest.getTrainee().getTraineeID());
        st.setInt(3, fitnessTest.getInstructor().getInstructorID());
        st.setInt(4, fitnessTest.getTrainingResult().getFitnessTestResultID());

        st.execute();
        con.close();
    }

    private int getFitnessTestResultId() throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT COALESCE(MAX(FITNESSTESTRESULTID)+1,1) FROM FITNESSTESTRESULT");
        int id = 0;
        while (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        st.close();
        con.close();
        return id;
    }

    private void insertFitnessTestResult(FitnessTestResult fitnessTestResult) throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        PreparedStatement st = (PreparedStatement) con.prepareStatement("INSERT INTO FITNESSTESTRESULT(fitnessTestResultID, runningResult, weightLiftingResult, pushUpsResult, squatsResult, sitUpResult, otherResult) VALUES (?,?,?,?,?,?,?)");
        fitnessTestResult.setFitnessTestResultID(getFitnessTestResultId());
        st.setInt(1, fitnessTestResult.getFitnessTestResultID());
        st.setString(2, fitnessTestResult.getRunningResult());
        st.setString(3, fitnessTestResult.getWeightLiftingResult());
        st.setString(4, fitnessTestResult.getPushUpsResult());
        st.setString(5, fitnessTestResult.getSquatsResult());
        st.setString(6, fitnessTestResult.getSitUpResult());
        st.setString(7, fitnessTestResult.getOtherResult());

        st.execute();
        con.close();
    }

    public void deleteFitnessTest(FitnessTest fitnessTest) throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        PreparedStatement st = (PreparedStatement) con.prepareStatement("DELETE FROM FITNESSTEST WHERE FITNESSTESTID = ?");
        st.setInt(1, fitnessTest.getFitnessTestID());

        st.executeUpdate();
        con.close();

        deleteFitnessTestResult(fitnessTest.getTrainingResult());
    }

    private void deleteFitnessTestResult(FitnessTestResult fitnessTestResult) throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        PreparedStatement st = (PreparedStatement) con.prepareStatement("DELETE FROM FITNESSTESTRESULT WHERE FITNESSTESTRESULTID = ?");
        st.setInt(1, fitnessTestResult.getFitnessTestResultID());

        st.executeUpdate();
        con.close();
    }

    private void updateFitnessTestResult(FitnessTestResult fitnessTestResult) throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        PreparedStatement st = (PreparedStatement) con.prepareStatement("UPDATE FITNESSTESTRESULT SET runningResult = ?, weightLiftingResult = ?, pushUpsResult = ?, squatsResult = ?, sitUpResult = ?, otherResult = ? WHERE FITNESSTESTRESULTID = ?");
        st.setString(1, fitnessTestResult.getRunningResult());
        st.setString(2, fitnessTestResult.getWeightLiftingResult());
        st.setString(3, fitnessTestResult.getPushUpsResult());
        st.setString(4, fitnessTestResult.getSquatsResult());
        st.setString(5, fitnessTestResult.getSitUpResult());
        st.setString(6, fitnessTestResult.getOtherResult());

        st.setInt(7, fitnessTestResult.getFitnessTestResultID());

        st.execute();
        con.close();
    }

    public void updateFitnessTest(FitnessTest fitnessTest) throws SQLException {
        updateFitnessTestResult(fitnessTest.getTrainingResult());

        con = DriverManager.getConnection(url, username, password);
        PreparedStatement st = (PreparedStatement) con.prepareStatement("UPDATE FITNESSTEST SET DATE = ?, TRAINEE = ?, INSTRUCTOR = ?, TRAININGRESULT = ? WHERE FITNESSTESTID = ?");
        st.setDate(1, new java.sql.Date(fitnessTest.getDate().getTime()));
        st.setInt(2, fitnessTest.getTrainee().getTraineeID());
        st.setInt(3, fitnessTest.getInstructor().getInstructorID());
        st.setInt(4, fitnessTest.getTrainingResult().getFitnessTestResultID());

        st.setInt(5, fitnessTest.getFitnessTestID());

        st.execute();
        con.close();
    }

    public boolean login(Admin admin) throws SQLException {
        con = DriverManager.getConnection(url, username, password);
        String upit = "SELECT ADMINID FROM ADMIN WHERE USERNAME = ? AND PASSWORD = ?";
        int adminID;
        try (PreparedStatement st = con.prepareStatement(upit)) {
            st.setString(1, admin.getUsername());
            st.setString(2, admin.getPassword());
            try (ResultSet rs = st.executeQuery()) {
                adminID = Integer.MIN_VALUE;
                while (rs.next()) {
                    adminID = rs.getInt(1);
                }
            }
        }

        if (adminID != Integer.MIN_VALUE) {
            return true;
        } else {
            return false;
        }
    }

}
