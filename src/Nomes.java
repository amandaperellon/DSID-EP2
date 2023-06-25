import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Nomes extends UnicastRemoteObject implements INomes, Serializable {
    public HashMap<Maquina, IAgencia> Agencias;
    public HashMap<IAgente, IAgencia> Agentes;

    public Nomes() throws RemoteException {
        Agencias = new HashMap<>();
        Agentes = new HashMap<>();
    }

    public HashMap<Maquina, IAgencia> getAgencias() {
        return Agencias;
    }

    public void setAgencias(HashMap<Maquina, IAgencia> agencias) {
        Agencias = agencias;
    }

    public HashMap<IAgente, IAgencia> getAgentes() {
        return Agentes;
    }

    public void setAgentes(HashMap<IAgente, IAgencia> agentes) {
        Agentes = agentes;
    }

    public IAgencia getAgenciaMaquina(Maquina maquina){
        for (Map.Entry<Maquina, IAgencia> agencias : Agencias.entrySet()) {
            if(Objects.equals(agencias.getKey().getNome(), maquina.getNome()) && Objects.equals(agencias.getKey().getIp(), maquina.getIp()) && Objects.equals(agencias.getKey().getPorta(), maquina.getPorta())){
                return agencias.getValue();
            }
        }
        return null;
    }

    public void addAgente(IAgente agente, IAgencia agencia) {
        Agentes.put(agente, agencia);
    }

    public void addAgencia(Maquina maquina, IAgencia agencia) {
        Agencias.put(maquina, agencia);
    }
}
