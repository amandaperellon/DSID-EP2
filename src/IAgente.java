import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.util.UUID;

public interface IAgente extends Remote {
    UUID getId() throws RemoteException;
    void setId(UUID id) throws RemoteException;
    String getCodigo() throws RemoteException;
    void setCodigo(String codigo) throws RemoteException;

    void run () throws RemoteException;
}
