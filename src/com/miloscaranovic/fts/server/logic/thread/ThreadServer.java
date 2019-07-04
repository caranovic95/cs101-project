/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.miloscaranovic.fts.server.logic.thread;

import com.miloscaranovic.fts.server.remote.ClientCommunication;
import java.net.Socket;

public class ThreadServer extends Thread {

    @Override
    public void run() {
        try {
            boolean kraj = false;
            while (!kraj) {
                Socket s = ClientCommunication.getInstance().getServerSocket().accept();
                ThreadClient nit = new ThreadClient(s);
                nit.start();
            }
        } catch (Exception ex) {
            System.err.println("Unexpected error " + ex.getMessage());
        }
    }
}
