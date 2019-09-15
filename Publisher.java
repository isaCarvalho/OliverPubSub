public class Publisher
{
    private final int id;
    private String nome;

    public Publisher(int id, String nome)
    {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString()
    {
        return "Id: " + this.id + "\nNome: " + this.nome;
    }
}
