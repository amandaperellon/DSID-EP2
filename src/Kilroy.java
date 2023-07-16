import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;

public class Kilroy extends Agent {

    public Kilroy(Maquina home, String arquivo) throws RemoteException {
        super(home, arquivo);
    }

    @Override
    public void beforeDeparture() {
        //goTo(new Maquina("localhost", 5000, "ServidorNomes"));
    }

    public String printResults(Process process) throws IOException {
        String text = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            text = text.concat(line);
            System.out.println(line);
        }
        return text;
    }

    @Override
    public void onArrival() {
        try {
            System.out.println("On Arrival");
            System.out.println(getArquivo());
            String[] cmdArray = new String[2];
            cmdArray[0] = "java";
            cmdArray[1] = getArquivo();
            Process pro = Runtime.getRuntime().exec(cmdArray, null, new File(System.getProperty("user.dir") + "\\out\\production\\EP2\\"));
            String teste = printResults(pro);
            System.out.println(teste);
            Frame f;
            f = new Frame("Kilroy");
            f.setLayout(new BorderLayout());
            f.add(new Label(teste, Label.CENTER));
            f.setSize(400, 50);
            f.show();
            f.toFront();

            try { Thread.sleep(5000); } catch (InterruptedException ie) {}
            f.dispose();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void onReturn() {

    }
}
