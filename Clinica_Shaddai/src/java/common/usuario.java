package common;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

@ManagedBean(name = "usuario_beam")
@SessionScoped
@Entity

public class usuario implements Serializable {

    @Id
    private int idUsuario;
    private String usuario;
    private String clave;
    
    private SessionFactory sessionFactory ;// = new Configuration().configure().buildSessionFactory();
    private Session session;// = sessionFactory.openSession();

    public int getId() {
        return idUsuario;
    }

    public void setId(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean inicioSesion() {
     SessionFactory sessionF = new Configuration().configure().buildSessionFactory();
        Session se = sessionF.openSession();
        se.beginTransaction();   
        try {

            Query query = se.createQuery("from usuario");
            
            System.out.println("   " + query.list());
            List lista = query.list();
            System.out.println("list size " + lista.size());
            System.out.println("usuario: " + this.usuario);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario);
            if (lista.size() == 1) {
                System.out.println("Logueado");
                return true;
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Contraseña incorrecta"));
                System.out.println("No Logueado");
                return false;
            }
        } catch (HibernateException e) {

            System.out.println(e);
        } finally {
            se.close();
        }
        return false;
    }
//      public boolean inicioSesion(String usuario, String clave) {
//        sessionFactory = new Configuration().configure().buildSessionFactory();
//        session = sessionFactory.openSession();
//        session.beginTransaction();
//        try {
//
//            Query query = session.createQuery("from usuario where usuario=:usuario and clave=:clave");
//            query.setString("usuario", usuario);
//            query.setString("clave", clave);
//
//            List lista = query.list();
//
//            System.out.println("list size " + lista.size());
//            if (lista.size() == 1) {
//                System.out.println("Logueado");
//
//                return true;
//
//            } else {
//                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Contraseña incorrecta"));
//                System.out.println("No Logueado");
//
//                return false;
//            }
//
//        } catch (HibernateException e) {
//
//            System.out.println(e);
//        } finally {
//            session.close();
//        }
//
//        return false;
//    }
   
    public usuario() {

    }

}
