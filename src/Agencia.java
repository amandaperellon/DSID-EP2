import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.UUID;

public class Agencia extends UnicastRemoteObject implements IAgencia, Serializable {
    private UUID Id;
    private Maquina Maquina;
    private HashMap<IAgente, String> Agentes;
    public Sandbox sandbox;

    public Agencia() throws RemoteException {
        Agentes = new HashMap<>();
        sandbox = new Sandbox();
    }

    @Override
    public void setMaquina(Maquina maquina) throws RemoteException {
        Id = UUID.randomUUID();
        Maquina = maquina;
    }

    @Override
    public IAgente criaAgente(String codigo) throws RemoteException {
        // Criar thread com agente
        Agent agente = new Kilroy(Maquina, codigo);
        Agentes.put(agente, codigo);
        sandbox.AddAgente(agente);
        sandbox.start();
        return agente;
    }
    @Override
    public void rodaAgente(IAgente agente) throws RemoteException{
        try {
            Agentes.put(agente, agente.getArquivo());
            System.out.println("roda Agente");
//            Sandbox sandbox = new Sandbox(agente);
//            System.out.println("inicio sandbox");
//            sandbox.start();
//            System.out.println("start sandbox");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void addAgenteSandbox(Agent agente) throws RemoteException {
        sandbox.AddAgente(agente);
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public HashMap<IAgente, String> getAgentes() {
        return Agentes;
    }

    public void setAgentes(HashMap<IAgente, String> agentes) {
        Agentes = agentes;
    }

    //TODO: esses metodos devem ser bloqueante
    public void enviarMensagem (UUID agenteDestino, String msg){
        // Consultar servico de nomes para saber em qual agencia e maquina esta o agente de destino
        // Chamar metodo receber mensagem na agencia de destino
    }

    public void receberMensagem (UUID agenteDestino, String msg) {
        // Repassar a mensagem para agente de destino
        // Agente de destino deve estar nessa agencia que recebeu a mensagem
    }


    public void transportarAgente (IAgente agente, Maquina maquina) {
        try {
            agente.goToAgencia(maquina);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
        // Serializar o objeto, persistindo variaveis
        // Consultar servico de nomes para saber em qual servidor esta a agencia de destino
        // Chamar receberAgente na agencia de destino

    }

    public void receberAgente (IAgente agente) {
        // Criar thread e colocar objeto para rodar nessa
        // Atualizar servico de nomes indicando que agente esta em nova agencia
    }

    public void removerAgente (IAgente agente) {
        // Remover agente do servico de nomes
        // Descontinuar a thread do agente
    }
}
