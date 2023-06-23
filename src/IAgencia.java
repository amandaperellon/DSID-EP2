import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAgencia extends Remote {
    public void setMaquina(Maquina maquina) throws RemoteException;
}
