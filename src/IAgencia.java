import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface IAgencia extends Remote {
    void setMaquina(Maquina maquina) throws RemoteException;
    IAgente criaAgente(String codigo) throws RemoteException;
    UUID getId() throws RemoteException;
    void rodaAgente(IAgente agente) throws RemoteException;
    void addAgenteSandbox(Agent agente) throws RemoteException;
}
