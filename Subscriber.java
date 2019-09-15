public class Subscriber
{
    private final int id;
    private String email;

    public Subscriber(int id, String email)
    {
        this.id = id;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString()
    {
        return "\nId: " + this.id + "\nEmail: " + this.email;
    }
}
