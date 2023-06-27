import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAgencia extends Remote {
    void setMaquina(Maquina maquina) throws RemoteException;
    IAgente criaAgente(String codigo) throws RemoteException;
}
