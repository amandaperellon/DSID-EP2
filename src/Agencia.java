import com.sun.source.tree.ReturnTree;

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

    public Agencia() throws RemoteException {
        Agentes = new HashMap<>();
    }

    @Override
    public void setMaquina(Maquina maquina) throws RemoteException {
        Id = UUID.randomUUID();
        Maquina = maquina;
    }

    @Override
    public IAgente criaAgente(String codigo) throws RemoteException {
        IAgente agente = new Agente(codigo);
        Agentes.put(agente, codigo);
        return agente;
    }
}
