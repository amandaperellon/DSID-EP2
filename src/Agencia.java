import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

public class Agencia extends UnicastRemoteObject implements IAgencia, Serializable {
    private UUID Id;
    private Maquina Maquina;
    private HashMap<IAgente, String> Agentes;

    private INomes ServidorNomes;

    public Agencia() throws RemoteException {
        Agentes = new HashMap<>();
    }

    @Override
    public void setMaquina(Maquina maquina) throws RemoteException {
        Id = UUID.randomUUID();
        Maquina = maquina;
    }

    public INomes ConectaServidorNomes(){
        try {
            Registry registry = LocateRegistry.getRegistry("localhost",5000);
            ServidorNomes = (INomes) registry.lookup("ServidorNomes");
            System.out.println("Conectado com o servidor de nomes");
            return ServidorNomes;
        }catch(Exception e){
            System.out.println("Não foi possível se conectar com o servidor: "+e.getMessage());
            return null;
        }
    }

    @Override
    public void criaAgente(String nome) throws RemoteException {
        IAgente agente = new Agente(nome);
        ConectaServidorNomes();
        ServidorNomes.addAgente(agente, this);
    }
}
