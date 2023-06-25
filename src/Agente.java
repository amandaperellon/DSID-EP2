import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class Agente extends UnicastRemoteObject implements IAgente, Serializable {
    private UUID Id;
    private String Nome;

    public Agente(String nome) throws RemoteException {
        Nome = nome;
    }
}
