/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.miloscaranovic.fts.server.remote;

import java.io.IOException;
import java.net.ServerSocket;


public class ClientCommunication {
    
    private int port;
    private ServerSocket serverSocket;
    
    private ClientCommunication(int port) {
        this.port = port;
    }
    private static ClientCommunication INSTANCE;

    public static ClientCommunication getInstance() throws IOException, ClassNotFoundException, Exception {
        if (INSTANCE == null) {
            INSTANCE = new ClientCommunication(9000);
            INSTANCE.pokreniServer();
        }
        return INSTANCE;
    }

    private void pokreniServer() throws IOException, ClassNotFoundException, Exception {
        serverSocket = new ServerSocket(port);
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

}
