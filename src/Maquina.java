import java.io.Serializable;

public class Maquina implements Serializable {
    private String Nome;
    private int Porta;
    private String Ip;

    public Maquina(){

    }
    public Maquina(String nome, int porta){
        Nome = nome;
        Porta = porta;
        Ip = "localhost";
    }
    public Maquina(String nome, int porta, String ip){
        Nome = nome;
        Porta = porta;
        Ip = ip;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getPorta() {
        return Porta;
    }

    public void setPorta(int porta) {
        Porta = porta;
    }

    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }
}
