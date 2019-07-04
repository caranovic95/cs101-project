/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miloscaranovic.fts.server.logic.thread;

import com.miloscaranovic.fts.domain.Admin;
import com.miloscaranovic.fts.domain.FitnessTest;
import com.miloscaranovic.fts.remote.TransferObject;
import com.miloscaranovic.fts.server.logic.db.DatabaseController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ThreadClient extends Thread {

    Socket soket;
    ObjectOutputStream out;
    ObjectInputStream in;

    public ThreadClient(Socket soket) throws IOException {
        this.soket = soket;
        out = new ObjectOutputStream(soket.getOutputStream());
        in = new ObjectInputStream(soket.getInputStream());
    }
    
    // klasa koja nasledjuje klasu nit i za svakog klijenta koji se konektuje na server pokrece 
    //se jedna nit te klase

    @Override
    public void run() {
        try {
            while (true) {
                TransferObject transferniObjekat = (TransferObject) in.readObject();//objekat koji klijent salje serveru i server salje klijentu
                switch (transferniObjekat.getOperation()) {
                    case TransferObject.INSERT_FITNESS_TEST:
                        try {
                            DatabaseController.getInstance().insertFitnessTest((FitnessTest) transferniObjekat.getParameter());
                            transferniObjekat.setResult(transferniObjekat.getParameter());
                        } catch (Exception ex) {
                            transferniObjekat.setException(ex);
                        }
                        break;
                    case TransferObject.UPDATE_FITNESS_TEST:
                        try {
                            DatabaseController.getInstance().updateFitnessTest((FitnessTest) transferniObjekat.getParameter());
                            transferniObjekat.setResult(transferniObjekat.getParameter());
                        } catch (Exception ex) {
                            transferniObjekat.setException(ex);
                        }
                        break;
                    case TransferObject.DELETE_FITNESS_TEST:
                        try {
                            DatabaseController.getInstance().deleteFitnessTest((FitnessTest) transferniObjekat.getParameter());
                            transferniObjekat.setResult(null);
                        } catch (Exception ex) {
                            transferniObjekat.setException(ex);
                        }
                        break;
                    case TransferObject.GET_ALL_FITNESS_TESTS:
                        try {
                            transferniObjekat.setResult(DatabaseController.getInstance().getAllFitnessTest());
                        } catch (Exception ex) {
                            transferniObjekat.setException(ex);
                        }
                        break;
                    case TransferObject.GET_ALL_INSTRUCTORS:// server uzme objekat i taj objekat ima rezultat, parametar i bojekat
                        try { // vraca objekat klijentu
                            transferniObjekat.setResult(DatabaseController.getInstance().getAllInstructors());
                        } catch (Exception ex) {
                            transferniObjekat.setException(ex);
                        }
                        break;

                    case TransferObject.GET_ALL_TRAINEES:
                        try {
                            transferniObjekat.setResult(DatabaseController.getInstance().getAllTrainees());
                        } catch (Exception ex) {
                            transferniObjekat.setException(ex);
                        }
                        break;
                    case TransferObject.ADMIN_LOGIN:
                        try {
                            transferniObjekat.setResult(DatabaseController.getInstance().login((Admin) transferniObjekat.getParameter()));
                        } catch (Exception ex) {
                            transferniObjekat.setException(ex);
                        }
                        break;
                }
                transferniObjekat.setParameter(null);
                transferniObjekat.setOperation(0);
                out.writeObject(transferniObjekat);
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Unexpected exception " + ex.getMessage());
        }
    }
}
