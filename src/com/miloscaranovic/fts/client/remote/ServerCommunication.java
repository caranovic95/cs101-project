/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miloscaranovic.fts.client.remote;

import com.miloscaranovic.fts.domain.Admin;
import com.miloscaranovic.fts.domain.FitnessTest;
import com.miloscaranovic.fts.domain.FitnessTestResult;
import com.miloscaranovic.fts.domain.Instructor;
import com.miloscaranovic.fts.domain.Trainee;
import com.miloscaranovic.fts.remote.TransferObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ServerCommunication {

    Socket socket;
    ObjectOutputStream out;
    ObjectInputStream in;

    private ServerCommunication() throws UnknownHostException, IOException {
        socket = new Socket("127.0.0.1", 9000);
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }
    private static ServerCommunication INSTANCE;

    public static ServerCommunication getInstance() throws UnknownHostException, IOException {
        if (INSTANCE == null) {
            INSTANCE = new ServerCommunication();
        }
        return INSTANCE;
    }

    public FitnessTest insertFitnessTest(FitnessTest fitnessTest) throws Exception {
        TransferObject to = new TransferObject(TransferObject.INSERT_FITNESS_TEST, fitnessTest);
        // klijent hoce da mu se ubaci novi fitnes test
        // punimo transfer objekt i saljemo ga
        out.writeObject(to);
        to = (TransferObject) in.readObject();
        if (to.getException() != null) {
            Exception e = (Exception) to.getException();
            throw e;
        } else {
            return (FitnessTest) to.getResult();
        }
    }

    public FitnessTest updateFitnessTest(FitnessTest fitnessTest) throws Exception {
        TransferObject to = new TransferObject(TransferObject.UPDATE_FITNESS_TEST, fitnessTest);
        out.writeObject(to);
        to = (TransferObject) in.readObject();
        if (to.getException() != null) {
            Exception e = (Exception) to.getException();
            throw e;
        } else {
            return (FitnessTest) to.getResult();
        }
    }

    public void deleteFitnessTest(FitnessTest fitnessTest) throws Exception {
        TransferObject to = new TransferObject(TransferObject.DELETE_FITNESS_TEST, fitnessTest);
        out.writeObject(to);
        to = (TransferObject) in.readObject();
        if (to.getException() != null) {
            Exception e = (Exception) to.getException();
            throw e;
        }
    }

    public boolean login(Admin admin) throws Exception {
        TransferObject to = new TransferObject(TransferObject.ADMIN_LOGIN, admin);
        out.writeObject(to);
        to = (TransferObject) in.readObject();
        if (to.getException() != null) {
            Exception e = (Exception) to.getException();
            throw e;
        }
        return (boolean) to.getResult();
    }

    public ArrayList<Instructor> getAllInstructors() throws Exception {
        TransferObject to = new TransferObject(TransferObject.GET_ALL_INSTRUCTORS, null);
        out.writeObject(to);
        to = (TransferObject) in.readObject();
        if (to.getException() != null) {
            Exception e = (Exception) to.getException();
            throw e;
        }
        return (ArrayList<Instructor>) to.getResult();
    }

    public ArrayList<Trainee> getAllTrainess() throws Exception {
        TransferObject to = new TransferObject(TransferObject.GET_ALL_TRAINEES, null);
        out.writeObject(to);
        to = (TransferObject) in.readObject();
        if (to.getException() != null) {
            Exception e = (Exception) to.getException();
            throw e;
        }
        return (ArrayList<Trainee>) to.getResult();
    }

    public ArrayList<FitnessTest> getAllFitnessTests() throws Exception {
        TransferObject to = new TransferObject(TransferObject.GET_ALL_FITNESS_TESTS, null);
        out.writeObject(to);
        to = (TransferObject) in.readObject();
        if (to.getException() != null) {
            Exception e = (Exception) to.getException();
            throw e;
        }
        return (ArrayList<FitnessTest>) to.getResult();
    }

}
