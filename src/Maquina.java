public class Maquina {
    private String Nome;
    private int Porta;
    private String Ip;

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
}
