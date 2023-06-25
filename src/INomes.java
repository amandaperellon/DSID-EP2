import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface INomes extends Remote {
    public IAgencia getAgenciaMaquina(Maquina maquina) throws RemoteException;
    public void addAgente(IAgente agente, IAgencia agencia) throws RemoteException;
    public void addAgencia(Maquina maquina, IAgencia agencia) throws RemoteException;
    public HashMap<Maquina, IAgencia> getAgencias() throws RemoteException;
    public void setAgencias(HashMap<Maquina, IAgencia> agencias) throws RemoteException;
    public HashMap<IAgente, IAgencia> getAgentes() throws RemoteException;
    public void setAgentes(HashMap<IAgente, IAgencia> agentes) throws RemoteException;
}
