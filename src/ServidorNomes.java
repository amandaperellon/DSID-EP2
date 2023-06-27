import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ServidorNomes {
    public static INomes Nomes;
    public static void main(String[] args) {
        new ServidorNomes();
        CriaAgenciaNomes();
    }

    public ServidorNomes(){
        try{
            Nomes = new Nomes();
            Registry registry = LocateRegistry.createRegistry(5000);
            registry.bind("ServidorNomes", Nomes);
            System.out.println("Servidor de Nomes iniciado com sucesso");
        }catch (Exception ex){
            System.out.println("Erro ao iniciar o servidor: "+ex.getMessage());
            System.exit(0);
        }
    }

    public static void CriaAgenciaNomes(){
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.print("Entre com o nome da Agencia: ");
                String name = sc.nextLine();
                System.out.print("Entre com a porta da Agencia: ");
                int port = Integer.parseInt(sc.nextLine());
                Maquina maquina = new Maquina(name, port);

                IAgencia repository = new Agencia();
                repository.setMaquina(maquina);

                Registry registry = LocateRegistry.createRegistry(port);
                registry.bind(name, repository);
                System.out.println("Agencia " + name + " iniciado com sucesso");
                Nomes.addAgencia(maquina, repository);
            } catch (Exception ex) {
                System.out.println("Erro ao iniciar a Agencia: " + ex.getMessage());
                System.exit(0);
            }
        }
    }
}