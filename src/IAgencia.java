import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface IAgencia extends Remote {
    void setMaquina(Maquina maquina) throws RemoteException;
    IAgente criaAgente(String codigo) throws RemoteException;
}
