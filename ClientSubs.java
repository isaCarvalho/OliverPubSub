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

            System.out.println("\nDigite seu email: ");
            String email = s.nextLine();

            System.out.println("Digite seu id: ");
            int id = s.nextInt();

            String response = stub.cadastrarSubscriber(id, email);
            System.out.println("response: " + response);   
        }
        catch(Exception e)
        {
            System.out.println("Cadastrar subscriber exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public static void adicionarCurso(Oliver stub, String host)
    {
        try 
        {
            Scanner s = new Scanner(System.in);

            System.out.println("\nDigite o seu id:");
            int id_subscriber = s.nextInt();

            Thread t1 = new Thread(new ClientThread(host, "Cursos"));
            t1.start(); 

            System.out.println("\nDigite o id do curso a ser adicionado:");
            int id_curso = s.nextInt();

            String response = stub.adicionarCurso(id_subscriber, id_curso);
            System.out.println("response: " + response);
        }
        catch(Exception e)
        {
            System.out.println("Adicionar curso exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public static void removerCurso(Oliver stub, String host)
    {
        try 
        {
            Scanner s = new Scanner(System.in);

            System.out.println("\nDigite o seu id:");
            int id_subscriber = s.nextInt();

            Thread t1 = new Thread(new ClientThread(host, "Cursos"));
            t1.start(); 

            System.out.println("\nDigite o id do curso a ser removido:");
            int id_curso = s.nextInt();

            String response = stub.removerCurso(id_subscriber, id_curso);
            System.out.println("\nresponse: " + response);           
        }
        catch(Exception e)
        {
            System.out.println("Remover curso exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public static void imprimirMeusCursos(Oliver stub)
    {
        try 
        {
            Scanner s = new Scanner(System.in);

            System.out.println("\nDigite seu id: ");
            int id = s.nextInt();

            String response = stub.listarCursosPorId(id);
            System.out.println("\nresponse: " + response);
        }
        catch(Exception e)
        {
            System.out.println("Listar cursos por id exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public static void listarMensagens(Oliver stub)
    {
        try
        {
            Scanner s = new Scanner(System.in);

            System.out.println("\nDigite seu id: ");
            int id = s.nextInt();
            
            System.out.println(stub.listarMensagens(id));
        }
        catch (Exception e)
        {
            System.out.println("Listar mensagens exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];

        try 
        {
            Registry registry = LocateRegistry.getRegistry(host);
            Oliver stub = (Oliver) registry.lookup("Oliver");

            Thread mensagem = new Thread(new ClientThread(host, "Mensagem"));
            mensagem.start();

            int op = 0;

            do 
            {
                System.out.println("\nDigite a opcao desejada:\n");
                System.out.println("1 - Cadastrar um novo subscriber");
                System.out.println("2 - Se inscrever em um curso");
                System.out.println("3 - Remover um curso");
                System.out.println("4 - Listar todos os meus cursos");
                System.out.println("5 - Visualizar caixa de entrada");
                System.out.println("6 - Sair");

                Scanner s = new Scanner(System.in);
                op = s.nextInt();

                switch(op)
                {
                    case 1: 
                        cadastrarSubscriber(stub);
                        break;

                    case 2:
                        adicionarCurso(stub, host);
                        break;

                    case 3:
                        removerCurso(stub, host);
                        break;

                    case 4:
                        imprimirMeusCursos(stub);
                        break;

                    case 5:
                        listarMensagens(stub);
                        break;

                    default:
                        break;
                }

            } while (op != 6);
            
        }
        catch (Exception e)
        {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}