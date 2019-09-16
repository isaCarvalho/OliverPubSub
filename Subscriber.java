import java.io.Serializable;
import java.util.HashMap;

public class Subscriber implements Serializable
{
    private final int id;
    private String email;
    private HashMap<Integer, Curso> cursos = new HashMap<>();
    private HashMap<Integer, String> caixaDeEntrada = new HashMap<>();
    private static final long serialVersionUID = 1L;

    public Subscriber(int id, String email)
    {
        this.id = id;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public HashMap<Integer, String> getCaixaDeEntrada()
    {
        return this.caixaDeEntrada;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void addCurso(Curso curso)
    {
        this.cursos.put(curso.getId(), curso);
    }

    public void removerCurso(int id)
    {
        this.cursos.remove(id);
    }

    public HashMap<Integer, Curso> getCursos()
    {
        return this.cursos;
    }

    public void addMensagem(int id_curso, String mensagem)
    {
        this.caixaDeEntrada.put(id_curso, mensagem);
    }

    public void imprimirCursos()
    {
        this.cursos.values().toString();
    }

    @Override
    public String toString()
    {
        return "\nId: " + this.id + "\nEmail: " + this.email;
    }
}
