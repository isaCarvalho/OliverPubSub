import java.rmi.RemoteException;
import java.rmi.Remote;
import java.util.HashMap;

public interface Oliver extends Remote
{
    public String helloOliver(String teste) throws RemoteException;

    public String cadastrarPublisher(int id, String nome) throws RemoteException;

    public String imprimirPublishers() throws RemoteException;

    public String cadastrarSubscriber(int id, String email) throws RemoteException;

    public String cadastrarCurso(int id, String titulo, int id_publisher) throws RemoteException;

    public String imprimirCursos() throws RemoteException;
}
