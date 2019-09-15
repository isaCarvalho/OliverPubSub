public class Curso
{
    private final int id;
    private String titulo;
    private String descricao;
    private int id_publisher;

    public Curso(int id, String titulo, int id_publisher)
    {
        this.id = id;
        this.titulo = titulo;
        this.id_publisher = id_publisher;
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

    @Override
    public String toString() {
        return "O curso " + this.titulo + " - " + this.id + " foi criado por " + this.id_publisher;
    }
}
