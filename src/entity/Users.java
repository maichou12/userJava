package entity;
import org.mindrot.jbcrypt.BCrypt;
public class Users {
    private int id;
    private int idRole;
    private String email;
    private String passwordHashed;
    private Roles role;

    //Constructeurs

    public Users() {
    }

    public Users(int id, String email, String password, int idRole) {
        this.id = id;
        this.idRole = idRole;
        this.email = email;
        this.passwordHashed = hashPassword(password);
        this.role = new Roles(idRole, "admin");
    }

    //Getters
    public int getId() {
        return id;
    }

    public int getIdRole() {
        return idRole;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHashed() {
        return passwordHashed;
    }



    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHashed(String password) {
        this.passwordHashed = hashPassword(password);
    }

    //Methodes

    // Méthode pour hacher le mot de passe avec BCrypt
    private String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public Roles getRole() {
        return role;
    }

    public void setRoles(Roles roles) {
        this.role = roles;
    }
}
