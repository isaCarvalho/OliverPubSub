import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Inbox implements Dodger, Remote
{
    public Inbox(){};

    public void listarMensagens(Subscriber remetente) throws RemoteException
    {
        System.out.println("\n" + remetente.getEmail());    
        for (String value : (remetente.getCaixaDeEntrada()).values())
            System.out.println(value);
    }

    public static void main(String args[])
    {
        String host = (args.length < 1) ? null : args[0];
        try
        {
            Inbox dodger = new Inbox();
            Registry registry = LocateRegistry.getRegistry(host);

            Dodger dstub = (Dodger) UnicastRemoteObject.exportObject(dodger, 0);
            registry.bind("Dodger", dstub);

            System.out.println("Inbox is ready!\n");            
        }
        catch(Exception e)
        {
            System.err.println("Errinho no inbox, depois isa identa");
        }
    }
}