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

            String local = "Chegou do servidor: \n";

            if (this.metodo == "Publishers")
            {
                local = local + stub.imprimirPublishers();
                System.out.println(local);
            }
            if (this.metodo == "Cursos")
            {
                local = local + stub.imprimirCursos();
                System.out.println(local);
            }
        }
        catch (Exception e)
        {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}