import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ServidorAgencia {
    private static final Scanner sc = new Scanner(System.in);
    public static IAgencia Agencia;

    public static void main(String[] args) {
        try {
            Maquina maquina = new Maquina();
            System.out.print("Entre com a ip do servidor: ");
            maquina.setIp(sc.nextLine());
            System.out.print("Entre com o nome do servidor: ");
            maquina.setNome(sc.nextLine());
            System.out.print("Entre com a porta do servidor: ");
            maquina.setPorta(Integer.parseInt(sc.nextLine()));
            INomes servidorNomes = ConectaServidorNomes();
            if(servidorNomes != null) {
                IAgencia agencia = servidorNomes.getAgenciaMaquina(maquina);
                Registry registry = LocateRegistry.getRegistry(maquina.getIp(), maquina.getPorta());
                Agencia = (IAgencia) registry.lookup(maquina.getNome());
                System.out.println("Conectado com o servidor: " + maquina.getNome());
            }
        }catch(Exception e){
            System.out.println("Não foi possível se conectar com o servidor: "+e.getMessage());
        }
    }

    public static INomes ConectaServidorNomes(){
        try {
            Registry registry = LocateRegistry.getRegistry("localhost",5000);
            INomes ServidorNomes = (INomes) registry.lookup("ServidorNomes");
            System.out.println("Conectado com o servidor de nomes");
            return ServidorNomes;
        }catch(Exception e){
            System.out.println("Não foi possível se conectar com o servidor: "+e.getMessage());
            return null;
        }
    }
}
