import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientSubs {

    private ClientSubs() {}

    public static void cadastrarSubscriber(Oliver stub)
    {
        try 
        {
            Scanner s = new Scanner(System.in);

            System.out.println("Digite seu email: ");
            String email = s.nextLine();

            System.out.println("Digite seu id: ");
            int id = s.nextInt();

            String response = stub.cadastrarSubscriber(id, email);
            System.out.println("response: " + response);   
        }
        catch(Exception e)
        {
            System.out.println("Cadastrar subscriber exception: " e.toString());
            e.printStackTrace();
        }
    }

    public static adicionarCurso(Oliver stub)
    {
        try 
        {
            Scanner s = new Scanner(System.in);

            System.out.println("Digite o seu id:");
            int id = s.nextInt();

            String response = stub.adicionarCurso(id);
            System.out.println("response: " + response);
        }
        catch(Exception e)
        {
            System.out.println("Adicionar curso exception: ", e.toString());
            e.printStackTrace();
        }
    }

    public static removerCurso(Oliver stub)
    {
        try 
        {
            Scanner s = new Scanner(System.in);

            System.out.println("Digite o seu id:");
            int id = s.nextInt();

            String response = stub.removerCurso(id);
            System.out.println("response: " + response);            
        }
        catch(Exception e)
        {
            System.out.println("Remover curso exception: ", e.toString());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];

        try 
        {
            Registry registry = LocateRegistry.getRegistry(host);
            Oliver stub = (Oliver) registry.lookup("Oliver");

            int op = 0;

            do 
            {
                System.out.println("\nDigite a opcao desejada:\n");
                System.out.println("1 - Cadastrar um novo subscriber");
                System.out.println("2 - Se inscrever em um curso");
                System.out.println("3 - Remover um curso");
                System.out.println("4 - Sair");

                Scanner s = new Scanner(System.in);
                op = s.nextInt();

                switch(op)
                {
                    case 1: 
                        cadastrarSubscriber(stub);
                        break;

                    case 2:
                        adicionarCurso(stub);
                        break;

                    case 3:
                        removerCurso(stub);
                        break;

                    default:
                        break;
                }

            } while (op != 4);
            
        }
        catch (Exception e)
        {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}