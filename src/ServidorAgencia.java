import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ServidorAgencia {
    private static final Scanner sc = new Scanner(System.in);
    public static IAgencia Agencia;
    public static IAgente Agente;
    public static INomes ServidorNomes;

    public static void main(String[] args) {
        IniciaAgencia();
        Menu();
        Comandos();
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

    public static void IniciaAgencia(){
        try {
            Maquina maquina = new Maquina();
            System.out.print("Entre com a ip do servidor: ");
            maquina.setIp(sc.nextLine());
            System.out.print("Entre com o nome do servidor: ");
            maquina.setNome(sc.nextLine());
            System.out.print("Entre com a porta do servidor: ");
            maquina.setPorta(Integer.parseInt(sc.nextLine()));
            ServidorNomes = ConectaServidorNomes();
            if(ServidorNomes != null && ServidorNomes.getAgenciaMaquina(maquina) != null) {
                Registry registry = LocateRegistry.getRegistry(maquina.getIp(), maquina.getPorta());
                Agencia = (IAgencia) registry.lookup(maquina.getNome());
                System.out.println("Conectado com o servidor: " + maquina.getNome());
            }
        }catch(Exception e){
            System.out.println("Não foi possível se conectar com o servidor: "+e.getMessage());
        }
    }

    public static void AdicionaAgenteServidorNomes(IAgente agente) throws RemoteException {
        ServidorNomes.addAgente(agente, Agencia);
    }

    public static void printResults(Process process) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
    }

    public static void Comandos() {
        while(true) {
            System.out.print("Entre com o comando: ");
            String comando = sc.nextLine();
            try {
                switch (comando) {
                    case "bind" ->
                        IniciaAgencia();
                    case "addagente" -> {
                        System.out.print("Entre com o codigo do agente ");
                        String codigo = sc.nextLine();
                        Agente = Agencia.criaAgente(codigo);
                        System.out.print("Agente com id " + Agente.getId() + " criado com sucesso");
                        AdicionaAgenteServidorNomes(Agente);
                    }
                    case "runteste"->{
                        String[] cmdArray = new String[2];
                        cmdArray[0] = "java";
                        cmdArray[1] = "HelloWorld";
                        Process pro = Runtime.getRuntime().exec(cmdArray, null, new File(System.getProperty("user.dir")+"\\out\\production\\EP2\\"));
                        printResults(pro);
                    }
                    case "irnovaagencia"->{
                        Agente.goToAgencia(new Maquina("Teste2", 7000, "localhost"));
                    }
                    case "help" ->
                            Menu();

                    case "quit" -> {
                        sc.close();
                        return;
                    }
                    default ->
                            System.out.print("Não há um comando com esse nome, entre com o comando help para mostrar os comandos possíveis");

                }
                System.out.println("\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void Menu(){
        System.out.println("""
                -------------------------------------Menu--------------------------------------
                help: Mostra os comandos possíveis
                bind: Se conectar a outra agencia
                addagente: Adiciona o agente a agencia
                removeagente:
                migrateagente:
                sendmessageagente: Enviar determinada mensagem a um agente 
                quit: Encerra a execução
                -------------------------------------------------------------------------------
                """);
    }
}
