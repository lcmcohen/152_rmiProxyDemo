# rmiProxyDemo
MCO152A Demonstrate use of of JAVA RMI, as example of Proxy Pattern
Currently assumes all components executed on same machine - localhost
Must start up rmiregistry

To start Server from command line assuming you are in "RmiProxyDemo/build/classes/java/main"(change folder names as needed):
java  -Djava.rmi.server.codebase=file:~/LCM/projCode/RmiProxyExample/build/classes/java/main   com/mco152/rmiserver/RemoteServer 

