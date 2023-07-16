import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.UUID;

public interface INomes extends Remote {
    IAgencia getAgenciaMaquina(Maquina maquina) throws RemoteException;
    IAgencia getAgenciaPeloId(UUID id) throws RemoteException;
    void addAgente(IAgente agente, IAgencia agencia) throws RemoteException;
    void addAgencia(Maquina maquina, IAgencia agencia) throws RemoteException;
    HashMap<Maquina, IAgencia> getAgencias() throws RemoteException;
    void setAgencias(HashMap<Maquina, IAgencia> agencias) throws RemoteException;
    HashMap<IAgente, IAgencia> getAgentes() throws RemoteException;
    void setAgentes(HashMap<IAgente, IAgencia> agentes) throws RemoteException;
    boolean mudarAgenteAgencia(IAgente agente, IAgencia agencia) throws RemoteException;
}
