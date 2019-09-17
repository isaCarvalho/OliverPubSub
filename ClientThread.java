import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientThread implements Runnable
{
    private String host;
    private String metodo;
    
    public ClientThread(String host, String metodo)
    {
        this.host = host;
        this.metodo = metodo;
    }

    @Override
    public void run()
    {
        try
        {
            Registry registry = LocateRegistry.getRegistry(this.host);
            Oliver stub = (Oliver) registry.lookup("Oliver");

            if (this.metodo == "Publishers")
                System.out.println(stub.imprimirPublishers());
            
            if (this.metodo == "Cursos")
                System.out.println(stub.imprimirCursos());
        }
        catch (Exception e)
        {
            System.err.println("ClientThread exception: " + e.toString());
            e.printStackTrace();
        }
    }
}