import java.io.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.UUID;

public abstract class Agent extends UnicastRemoteObject implements Runnable, Serializable, IAgente {

    private byte[] byteCodes;
    private LinkedList<Maquina> hosts;
    private final Maquina home;
    private UUID id;
    private String arquivo;
    public static final String  EXT = ".class";

    public Agent(Maquina home, String arquivo) throws RemoteException {
        FileInputStream in;
        byteCodes = null;
        this.id = UUID.randomUUID();
        this.arquivo = arquivo;

        try {
            in = new FileInputStream(System.getProperty("user.dir") + "\\out\\production\\EP2\\"+getName()+EXT);
            byteCodes = new byte[in.available()];
            in.read(byteCodes);
        } catch (FileNotFoundException e) {
            System.err.println("Excecao File Not Found");
            throw new RuntimeException(e);
        } catch (IOException e) {
            byteCodes = null;
            System.err.println("Excecao IO");
            throw new RuntimeException(e);
        }

        this.home = home;
    }

    public void addHost(Maquina host) {
        hosts.addFirst(host);
    }

    public INomes ConectaServidorNomes(){
        try {
            Registry registry = LocateRegistry.getRegistry("localhost",5000);
            INomes ServidorNomes = (INomes) registry.lookup("ServidorNomes");
            System.out.println("Conectado com o servidor de nomes");
            return ServidorNomes;
        }catch(Exception e){
            System.out.println("Não foi possível se conectar com o servidor: "+e.getMessage());
            return null;
        }
    }

    public void goTo(Maquina maquina){
        if(maquina.equals(home)){
            return;
        }

        try {
            System.out.println("Teste");
            INomes servidorNomes = ConectaServidorNomes();
            IAgencia agencia = servidorNomes.getAgenciaMaquina(maquina);
            servidorNomes.mudarAgenteAgencia(this, agencia);

            Registry registry = LocateRegistry.getRegistry(maquina.getIp(), maquina.getPorta());
            IAgencia agenciaTeste = (IAgencia) registry.lookup(maquina.getNome());
            System.out.println("Conectado com o servidor: " + maquina.getNome());
            agenciaTeste.rodaAgente(this);
            agenciaTeste.addAgenteSandbox(this);
            System.out.println("tudo certo");
        } catch(RemoteException ex){
            System.out.println("entrou no 1 catch");
            ex.printStackTrace();
        } catch (Exception e) {
            System.out.println("Entrou no 2 catch");
            maquina = hosts.getFirst();
            hosts.removeFirst();
            if(maquina != null) goTo(maquina);
        }
    }

    public abstract void beforeDeparture();
    public abstract void onArrival();
    public abstract void onReturn();

    public byte[] getByteCodes() {
        return byteCodes;
    }

    private String getName() {
        return getClass().getName();
    }

    @Override
    public void run() {
        System.out.println("Run iniciado");
        Maquina host;
        if(hosts == null){
            System.out.println("hosts == null");
            hosts = new LinkedList<>();
            hosts.addLast(home);
            beforeDeparture();
        }
        else if (hosts.size() == 0)
        {
            System.out.println("hosts size = 0");
            onReturn();
        }
        else
        {
            System.out.println("senao final");
            onArrival();
            host = hosts.getFirst();
            hosts.removeFirst();
            goTo(host);
        }
    }

    @Override
    public UUID getId() throws RemoteException {
        return id;
    }

    @Override
    public void setId(UUID id) throws RemoteException {
        this.id = id;
    }
    @Override
    public String getArquivo() throws RemoteException{
        return this.arquivo;
    }

    @Override
    public void setArquivo(String arquivo) throws RemoteException{
        this.arquivo = arquivo;
    }

    @Override
    public void goToAgencia(Maquina maquina) throws RemoteException {
        System.out.println("Go to maquina "+maquina.getNome());
        goTo(maquina);
    }
}
