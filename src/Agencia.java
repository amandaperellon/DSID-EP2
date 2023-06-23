import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.LinkedList;

public class Agencia extends UnicastRemoteObject implements IAgencia{
    private Maquina Maquina;
    private HashMap<IAgente, String> Agentes;

    public Agencia() throws RemoteException {
        Agentes = new HashMap<>();
    }
    @Override
    public void setMaquina(Maquina maquina) throws RemoteException {
        Maquina = maquina;
    }
}
