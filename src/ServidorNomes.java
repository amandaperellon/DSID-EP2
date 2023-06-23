import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Scanner;

public class ServidorNomes {
    public static final HashMap<IAgencia, Maquina> Agencias = new HashMap<>();
    public static final HashMap<IAgencia, IAgente> Agentes = new HashMap<>();

    public static void main(String[] args) {
        CriaAgenciaNomes();
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
                Agencias.put(repository, maquina);
            } catch (Exception ex) {
                System.out.println("Erro ao iniciar a Agencia: " + ex.getMessage());
                System.exit(0);
            }
        }
    }
}