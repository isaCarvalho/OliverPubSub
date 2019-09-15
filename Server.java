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
    private HashMap<Integer, String> palavras_chave = new HashMap<>();

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
    public String adicionarCurso(int id_subscriber) throws RemoteException
    {
        Subscriber sub = this.subscribers.get(id_subscriber);

        if (sub != null)
        {
            // VER COMO VAI IMPRIMIR OS CURSOS AQUI
        }

        return "Não foi possivel adicionar o curso! Subscriber inexistente!";
    }

    @Override
    public String alterarTituloCurso(int id, String titulo) throws RemoteException
    {
        // TERMINAR AQUI TBM E COLOCAR NO PUBLISHERS
    }

    @Override
    public String removerCurso(int id_subscriber) throws RemoteException
    {
        Subscriber sub = this.subscribers.get(id_subscriber);
        
        if (sub != null)
        {
            // VER COMO VAI IMPRIMIR AQUI TBM
        }

        return "Não foi possivel remover o curso! Subscriber inexistente!";
    }

    @Override
    public String cadastrarCurso(int id, String titulo, int id_publisher, String palavra_chave) throws RemoteException
    {
        if (this.publishers.get(id_publisher) != null) 
        {
            Curso novo = new Curso(id, titulo, id_publisher, palavra_chave);

            this.cursos.put(id, novo);
            this.palavras_chave.put(id, novo.getPalavraChave());

            return (this.cursos.get(id)).toString();
        }

        return "Nao foi possivel cadastrar o curso!";
    }

    public synchronized String imprimirPublishers() throws RemoteException
    {
        String resposta = publishers.values().toString(); 
        return resposta; 
    }

    public synchronized String imprimirCursos() throws RemoteException
    {
        String resposta = cursos.values().toString();
        return resposta;
    }

    public static void main(String args[])
    {
        try {
            Server obj = new Server();
            Oliver stub = (Oliver) UnicastRemoteObject.exportObject(obj, 0);

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
