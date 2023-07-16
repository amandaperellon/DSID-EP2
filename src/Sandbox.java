import java.util.LinkedList;

public class Sandbox implements Runnable{
    private LinkedList<Agent> agents;
    private Thread controlThread;

    public Sandbox() {
        agents = new LinkedList<>();
        this.controlThread = new Thread(this);
    }

    public void AddAgente(Agent agente){
        System.out.println("addagente sandbox");
        this.agents.addFirst(agente);
    }

    @Override
    public void run() {
        System.out.println("run");
    }

    public void start() {
        System.out.println("start sandbox");
        controlThread.start();
    }
}
