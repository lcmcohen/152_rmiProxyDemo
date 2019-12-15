package com.mco152.rmiserver;

import com.mco152.rmiinterface.ThingsDoable;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

/**
 * Remote Method server, demonstrates Proxy Pattern
 */
public class RemoteServer extends UnicastRemoteObject implements ThingsDoable {

    // let Java know where the RMI registry server is running
    static {
        System.setProperty("java.rmi.server.hostname", "localhost");
    }

    protected RemoteServer() throws RemoteException {
        super();
    }

    /**
     * Start up a RMI server that can run a method remotely
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            // Bind our service's name to this instance of the class in the registry
            Naming.rebind(MyThingDoerServer, new RemoteServer());

            System.out.println("Server ready. Registry Bindings are: " +
                    Arrays.asList(Naming.list(MyThingDoerServer)));
            System.out.println("Lookup of MyThingDoerServer=" + Naming.lookup(MyThingDoerServer));
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
        }
    }


    /**
     * This is the remote, real version of the  method whose proxy is running on client
     *
     * @param name
     * @return
     * @throws RemoteException
     */
    @Override
    public String helloTo(String name) throws RemoteException {
        System.out.println(name + " is trying to contact!" + "\b\b\b");
        return "(running remotely) Server says hello to " + name;
    }

    @Override
    public void makeSounds() throws RemoteException {
            System.out.println("Server is making some beautiful Music");
            SoundUtils.play5Tones();
    }

    @Override
    public void shutdownServer() throws RemoteException {
        System.exit(4);
    }

}
