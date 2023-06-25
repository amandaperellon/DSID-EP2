import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface IAgencia extends Remote {
    public void setMaquina(Maquina maquina) throws RemoteException;
    public void criaAgente(String nome) throws RemoteException;
    public INomes ConectaServidorNomes() throws RemoteException;
}
