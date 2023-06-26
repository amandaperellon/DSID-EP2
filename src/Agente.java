import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class Agente extends UnicastRemoteObject implements IAgente, Serializable {
    private UUID Id;
    private String Codigo;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public Agente(String codigo) throws RemoteException {
        Id = UUID.randomUUID();
        Codigo = codigo;
    }
}
