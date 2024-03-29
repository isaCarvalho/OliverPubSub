import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Collection;

public class Server implements Oliver, Remote
{
    private HashMap<Integer, Publisher> publishers = new HashMap<>();
    private HashMap<Integer, Subscriber> subscribers = new HashMap<>();
    private HashMap<Integer, Curso> cursos = new HashMap<>();
    private HashMap<Integer, String> palavras_chave = new HashMap<>();
    private Dodger subRemoto;

    public Server() {
    };

    private void alive() {
        try {
            Registry registry = LocateRegistry.getRegistry();
            this.subRemoto = (Dodger) registry.lookup("Dodger");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
        }
    }

    private String parseCollection(Collection values) {

        String resposta = "";
        for (Object value: values)
            resposta += '\n' + value.toString();

        return resposta;
    }

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
        this.alive();

        Subscriber novo = new Subscriber(id, email);
        this.subscribers.put(id, novo);
                
        return (this.subscribers.get(id)).toString();
    }

    @Override
    public String adicionarCurso(int id_subscriber, int id_curso) throws RemoteException
    {
        Subscriber sub = this.subscribers.get(id_subscriber);

        if (sub != null)
        {
            Curso curso = this.cursos.get(id_curso);
            if (curso != null)
            {
                sub.addCurso(curso);
                this.subscribers.replace(id_subscriber, sub);

                return "Curso adicionado com sucesso!";
            }
            return "Nao foi possivel adicionar o curso! Curso inexistente!";
        }
        return "Não foi possivel adicionar o curso! Subscriber inexistente!";
    }

    @Override
    public String removerCurso(int id_subscriber, int id_curso) throws RemoteException
    {
        Subscriber sub = this.subscribers.get(id_subscriber);
        
        if (sub != null)
        {
            sub.removerCurso(id_curso);
            this.subscribers.replace(id_subscriber, sub);

            return "Curso removido com sucesso!";
        }

        return "Nao foi possivel remover o curso! Subscriber inexistente!";
    }

    @Override
    public String listarCursosPorId(int id_subscriber) throws RemoteException
    {
        Subscriber sub = subscribers.get(id_subscriber);

        return parseCollection((sub.getCursos()).values());
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

    public String listarMensagens(int id_subscriber) throws RemoteException
    {
        Subscriber sub = this.subscribers.get(id_subscriber);

        if (sub != null)
            return parseCollection((sub.getCaixaDeEntrada()).values());
        
        return "Sub nao existe!";
    }

    public synchronized String imprimirPublishers() throws RemoteException
    {
        return parseCollection(publishers.values()); 
    }

    public synchronized String imprimirCursos() throws RemoteException
    {
        return parseCollection(cursos.values());
    }

    public String post(int id_curso, String mensagem)
    {
        Curso curso = this.cursos.get(id_curso);
        if (curso != null)
        {
            curso.addMensagem(mensagem);
            this.cursos.replace(id_curso, curso);

            for (Subscriber sub : this.subscribers.values())
            {
                for (Curso curso2: (sub.getCursos()).values())
                {
                    if (curso2.getId() == id_curso)
                    {
                        sub.addMensagem(id_curso, mensagem);
                        try 
                        {
                            subRemoto.listarMensagens(sub);
                        }
                        catch (Exception e)
                        {
                            System.err.println("Server exception: " + e.toString());
                        }
                    }
                }
            }

            return "Mensagem enviada com sucesso!";
        }
        return "Curso inexistente!";
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
