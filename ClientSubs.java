import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientSubs {

    private ClientSubs() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];

        try 
        {
            Registry registry = LocateRegistry.getRegistry(host);
            Oliver stub = (Oliver) registry.lookup("Oliver");

            Scanner s = new Scanner(System.in);

            System.out.println("Digite seu email: ");
            String email = s.nextLine();

            System.out.println("Digite seu id: ");
            int id = s.nextInt();

            String response = stub.cadastrarPublisher(id, email);

            System.out.println("response: " + response);
        }
        catch (Exception e)
        {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}