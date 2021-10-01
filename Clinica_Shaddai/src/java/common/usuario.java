package common;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

@ManagedBean(name = "usuario_beam")
@SessionScoped
@Entity

public class usuario implements Serializable {

    @Id
    private int id;
    private String usuario;
    private String clave;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        
        try{
            
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            Session session=sessionFactory.openSession();
            session.beginTransaction();
            
            Query query = session.createQuery("from usuario where usuario=:usuario and clave=:clave");
            query.setString("usuario", usuario);
            query.setString("clave", clave);
            
            List lista=query.list();
            
            System.out.println("list size "+lista.size());
              if(lista.size()==1){
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Contraseña incorrecta"));
                  return true;
                   
              }else{
                  return false;
              }
            
                    
            
            
        }  catch (Exception e){
            System.out.println(e);
        }
        
               return false;
    }

    public usuario() {

    }

}
