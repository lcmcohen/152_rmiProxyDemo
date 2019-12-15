package com.mco152.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * define what things we can do, anywhere
 */
public interface ThingsDoable extends Remote {

    // Define name to be used for RMI registry Todo: generalize localhost
    String MyThingDoerServer = "//localhost/MyThingDoerServer";

    // a thing I can do - say hello to someone
    String helloTo(String name) throws RemoteException;

    // a thing I can do - make sounds
    void makeSounds() throws RemoteException;

    // a thing I can do - make the remote server shut down
    void shutdownServer() throws RemoteException;

}