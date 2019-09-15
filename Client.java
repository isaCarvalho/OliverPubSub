import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    private Client() {}
    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
       
        try 
        {
            Registry registry = LocateRegistry.getRegistry(host);
            Oliver stub = (Oliver) registry.lookup("Oliver");

            int op = 0;
            Scanner s = new Scanner(System.in);

            do 
            {
                System.out.println("Digite a opcao desejada: \n");
                System.out.println("1 - Cadastrar um publisher;");
                System.out.println("2 - Cadastrar um curso;");
                System.out.println("3 - Ver todos os publishers;");
                System.out.println("4 - Ver todos os cursos;");
                System.out.println("5 - Sair");

                op = s.nextInt();
                String local;
                switch(op)
                {
                    case 1:
                        System.out.println(cadastrarPublisher(stub));
                        break;

                    case 2:
                        System.out.println(cadastrarCurso(stub));
                        break;

                    case 3:
                        /*local = stub.imprimirPublishers();
                        System.out.println(local);
                        local = "";*/
                        Thread t1 = new Thread(new ClientThread(host));
                        t1.start();                        
                        break;

                    case 4:
                        local = stub.imprimirCursos();
                        System.out.println(local);
                        local = "";
                        break;
                }
            
            } while (op != 5);
        
        }
        catch (Exception e)
        {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public static String cadastrarPublisher(Oliver stub)
    {
        try {
            Scanner s = new Scanner(System.in);

            System.out.println("Digite seu nome: ");
            String nome = s.nextLine();

            System.out.println("Digite seu id: ");
            int id = s.nextInt();

            return stub.cadastrarPublisher(id, nome);
        } 
        catch (Exception e)
        {
            System.out.println("Publisher Exception: " + e.toString());
            e.printStackTrace();
        }
        
        return "Nao";
    }

    public static String cadastrarCurso(Oliver stub)
    {
        try {
            Scanner c = new Scanner(System.in);

            System.out.println("Digite o titulo do curso: ");
            String titulo = c.nextLine();

            System.out.println("Digite o id do curso: ");
            int id = c.nextInt();

            System.out.println("Digite o id do publicador do curso: ");
            int id_publisher = c.nextInt();

            return stub.cadastrarCurso(id, titulo, id_publisher);
        }
        catch (Exception e)
        {
            System.out.println("Curso Exception: " + e.toString());
            e.printStackTrace();
        }
        
        return "Nao";
    }
}
