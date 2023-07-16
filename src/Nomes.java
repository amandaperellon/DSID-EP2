import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

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

    public boolean mudarAgenteAgencia(IAgente agente, IAgencia agencia){
        try {
            for (Map.Entry<IAgente, IAgencia> agentes : Agentes.entrySet()) {
                if (agentes.getKey().getId() == agente.getId()) {
                    agentes.setValue(agencia);
                    return true;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public IAgencia getAgenciaPeloId(UUID id) throws RemoteException {
        for (Map.Entry<Maquina, IAgencia> agencias : Agencias.entrySet()) {
            if(Objects.equals(agencias.getValue().getId(), id)){
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
