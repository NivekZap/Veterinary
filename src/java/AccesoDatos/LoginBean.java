package AccesoDatos;
import AccesoDatos.DAOusuario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private String mensaje;
    
    private DAOusuario daoUsuario;

    public LoginBean() {
        daoUsuario = new DAOusuario();
    }

    public String login() {
        if (daoUsuario.verificarCredenciales(username, password)) {
            // Credenciales válidas, redirige a la página de inicio
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("usuario", username);
            return "index?faces-redirect=true"; // Redirige a la página de inicio con la redirección de JSF
        } else {
            // Credenciales incorrectas
            mensaje = "Credenciales incorrectas. Inténtelo de nuevo.";
            return "login"; // Permanece en la página de login
        }
    }
    
    public String logout() {
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    return "login?faces-redirect=true"; // Redirige de vuelta a la página de login
}

    // Getters y Setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
}