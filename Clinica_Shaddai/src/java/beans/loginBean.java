/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.UsuarioDao;
import dao.UsuarioDaoImp;
import java.awt.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Usuario;
import org.primefaces.context.RequestContext;
import util.MyUtil;

/**
 *
 * @author Usuario
 */
public class loginBean {

    private Usuario usuario;
    private UsuarioDao usuarioDao;

    /**
     * Creates a new instance of loginBean
     */
    public loginBean() {
        this.usuarioDao = new UsuarioDaoImp();
        this.usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void login(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg;
        int loggedIn = 2;
        String ruta = "";

        this.usuario = this.usuarioDao.login(this.usuario);
        if (this.usuario != null) {
            System.out.println("Admin Login 1");
            loggedIn = 1;
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario.getUsuario());
            msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.usuario.getUsuario());
            ruta = MyUtil.basepathlogin() + "principal.xhtml";
        } else {
            System.out.println("Admin Login 2");
            this.usuario = new Usuario();
            loggedIn = 1;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Credenciales invalidas");
            ruta = MyUtil.basepathlogin() + "main.xhtml";
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("ruta", ruta);
    }
}
