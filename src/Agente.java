import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class Agente extends UnicastRemoteObject implements IAgente, Serializable, Runnable {
    private UUID Id;
    private String Codigo;

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public Agente(String codigo) throws RemoteException {
        Id = UUID.randomUUID();
        Codigo = codigo;
    }

    @Override
    public void run (){
        // Codigo de execucao do agente
    }

    public void comunicarComAgente () {
        // Encontrar qual minha agencia
        // Chamar agencia.enviarMensagem
    }

    public void receberMensagem (String msg) {
       // Receber mensagem e processa-la, migrando se necessario
    }
    public void transportarParaAgencia (UUID agenciaDestinoId) {
        // Ficar em estado consistente
        // Encontrar qual minha agencia
        // Chamar agencia.transportarAgente
    }
}
