import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class ClientThread implements Runnable
{
    private String host;
    
    public ClientThread(String host)
    {
        this.host = host;
    }
    @Override
    public void run(){
        try
        {
        Registry registry = LocateRegistry.getRegistry(this.host);
        Oliver stub = (Oliver) registry.lookup("Oliver");
        String local = "Chegou do servidor: \n";
        local = local + stub.imprimirPublishers();
        System.out.println(local);
        }
        catch (Exception e)
        {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}