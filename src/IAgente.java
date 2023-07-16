import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteObject;
import java.util.UUID;

public interface IAgente extends Remote {
    UUID getId() throws RemoteException;
    void setId(UUID id) throws RemoteException;
    String getArquivo() throws RemoteException;
    void setArquivo(String arquivo) throws RemoteException;
    void goToAgencia(Maquina maquina) throws RemoteException;
    void run () throws RemoteException;
}
