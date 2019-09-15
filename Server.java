import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class Server implements Oliver, Remote
{
    private HashMap<Integer, Publisher> publishers = new HashMap<>();
    private HashMap<Integer, Subscriber> subscribers = new HashMap<>();
    private HashMap<Integer, Curso> cursos = new HashMap<>();

    public Server() { };

    @Override
    public String helloOliver(String teste) throws RemoteException 
    {
        return "Relou, " + teste + "!";
    }

    @Override
    public String cadastrarPublisher(int id, String nome) throws RemoteException 
    {
        Publisher novo = new Publisher(id, nome);
        this.publishers.put(id, novo);        

        return (this.publishers.get(id)).toString();
    }

    @Override
    public String cadastrarSubscriber(int id, String email) throws RemoteException 
    {
        Subscriber novo = new Subscriber(id, email);
        this.subscribers.put(id, novo);

        return (this.subscribers.get(id)).toString();
    }

    @Override
    public String cadastrarCurso(int id, String titulo, int id_publisher) throws RemoteException
    {
        if (this.publishers.get(id_publisher) != null) 
        {
            Curso novo = new Curso(id, titulo, id_publisher);

            this.cursos.put(id, novo);

            return (this.cursos.get(id)).toString();
        }

        return "Nao foi possivel cadastrar o curso!";
    }

    public String imprimirPublishers() throws RemoteException
    {
        String resposta = publishers.values().toString();
        //publishers.forEach((key, value) -> resposta = resposta + value.toString() + "\n");
        return resposta;
    }

    public String imprimirCursos() throws RemoteException
    {
        String resposta = cursos.values().toString();
        //cursos.forEach((key, value) -> resposta = resposta + value.toString() + "\n");
        return resposta;
    }

    public static void main(String args[])
    {
        try {
            Server obj = new Server();
            Oliver stub = (Oliver) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Oliver", stub);

            System.err.println("Server ready");
        }
        catch (Exception e)
        {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
