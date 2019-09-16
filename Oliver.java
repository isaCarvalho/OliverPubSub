import java.rmi.RemoteException;
import java.rmi.Remote;

public interface Oliver extends Remote
{
    public String helloOliver(String teste) throws RemoteException;

    public String cadastrarPublisher(int id, String nome) throws RemoteException;

    public String imprimirPublishers() throws RemoteException;

    public String cadastrarSubscriber(int id, String email) throws RemoteException;

    public String adicionarCurso(int id_subscriber, int id_curso) throws RemoteException;

    public String removerCurso(int id_subscriber, int id_curso) throws RemoteException;

    public String listarCursosPorId(int id_subscriber) throws RemoteException;

    public String alterarTituloCurso(int id, String titulo) throws RemoteException;

    public String cadastrarCurso(int id, String titulo, int id_publisher, String palavra_chave) throws RemoteException;

    public String imprimirCursos() throws RemoteException;

    public String post(int id_curso, String mensagem) throws RemoteException;

    public String listarMensagens(int id_subscriber) throws RemoteException;

}
