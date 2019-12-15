package com.mco152.rmiclient;

import com.mco152.rmiinterface.ThingsDoable;

import java.rmi.RemoteException;

/**
 * A class that mocks what the Real/Subject class can do. Can be used as substitute for proxy.
 */
class LocalMockImplementation implements ThingsDoable {

    @Override
    public String helloTo(String name) throws RemoteException {
        return "(running locally - LocalMockImplementation)   Server says hello to " + name;
    }

    @Override
    public void makeSounds() {
        System.err.println("LocalMockImplementation would makeSounds if it could");
    }

    @Override
    public void shutdownServer() {
        System.err.println("LocalMockImplementation would shutdownServer if it could");
    }

}