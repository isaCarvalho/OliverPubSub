import java.util.ArrayList;

public class Curso
{
    private final int id;
    private String titulo;
    private String descricao;
    private int id_publisher;
    private String palavra_chave;
    private ArrayList<String> mensagens;

    public Curso(int id, String titulo, int id_publisher, String palavra_chave)
    {
        this.id = id;
        this.titulo = titulo;
        this.id_publisher = id_publisher;
        this.palavra_chave = palavra_chave;
    }

    public void addMensagem(String mensagem)
    {
        this.mensagens.add(mensagem);
    }

    public int getId() {
        return id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setId_publisher(int id_publisher) {
        this.id_publisher = id_publisher;
    }

    public int getId_publisher() {
        return id_publisher;
    }

    public void setPalavraChave(String palavra_chave)
    {
        this.palavra_chave = palavra_chave;
    }

    public String getPalavraChave()
    {
        return this.palavra_chave;
    }

    @Override
    public String toString() {
        return  "\nId: " + this.id + "\nTitulo: " + this.titulo + "\nPalavra-chave: " + this.palavra_chave;
    }
}
