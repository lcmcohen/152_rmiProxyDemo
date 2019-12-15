package com.mco152.rmiclient;

import com.mco152.rmiinterface.ThingsDoable;

import javax.swing.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;


/**
 * Client that requests some actions to be done, from some class that could be local or remote
 */
public class ClientOperation {

    private static ThingsDoable doThingsForMe;  // point to some local or remote object that can do stuff for us

    public ClientOperation() {
        try {
            // point our interface variable to a remote method we look up in registry
            doThingsForMe = (ThingsDoable) Naming.lookup(ThingsDoable.MyThingDoerServer);
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            System.err.println("Falling back to local Mock, as unable to init RMI: " + e);
            System.err.println("Make sure rmiserver is started and has entry for " + ThingsDoable.MyThingDoerServer);
            doThingsForMe = new LocalMockImplementation();  // cant get Proxy object from RMI, fallback to local Mock
        }
    }

    public static void main(String[] args) {
        ClientOperation client = new ClientOperation();
        client.doStuff();
    }

    /**
     * Make some requests to whatever 'doThingsForMe' points to, wherever.
     */
    private void doStuff() {
        try {
            String txt = JOptionPane.showInputDialog("What is your name?");

            //This is what I actually want to do.
            String response = doThingsForMe.helloTo(txt);
            JOptionPane.showMessageDialog(null, response); // display what we received from helloTo

            // now lets do something else, lets make some noise
            System.out.println("CLIENT REQUESTING SOUNDS ...");
            doThingsForMe.makeSounds();
            System.out.println("Oh, THAT WAS BEAUTIFUL!!!");
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(null, exc);
        }
    }

}
