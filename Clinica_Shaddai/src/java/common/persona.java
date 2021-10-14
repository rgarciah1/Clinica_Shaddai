/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.persistence.Id;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.Entity;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Ottoniel
 */
@ManagedBean(name = "persona")
@RequestScoped
@Entity

public class persona implements Serializable {

    @Id
    int idPersona;
    String nombre;
    String apellido;
    int edad;
    String direccion;
    String departamento;
    String correo;
    Date fechaNacimiento;

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    int habilitado;
    private SessionFactory sessionFactory;// = new Configuration().configure().buildSessionFactory();
    private Session session;// = sessionFactory.openSession();
    private Transaction transaction;
    private varios varios;

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }

    //QUERYS 
    //https://www.javaguides.net/2018/11/hibernate-query-language-insert-update.html
    public boolean registroUsuario() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        varios = new varios();
        boolean resultado = false;
        String vusuario = "";
        int result = 0;
        String consulta = "INSERT INTO persona(nombre, apellido, edad, departamento, habilitado, correo,fecha_nacimiento) values (:nombre, :apellido, :edad, :departamento, :habilitado, :correo, :nacimiento)";
        String usuario = "";//"INSERT INTO usuario (usuario, clave, habilitado, fecha_usaurio) VALUES(:usuario, :clave, 1, now())";
        try {
            Query query = session.createSQLQuery(consulta);
            query.setParameter("nombre", nombre);
            query.setParameter("apellido", apellido);
            query.setInteger("edad", edad);
            query.setParameter("departamento", departamento);
            query.setParameter("habilitado", habilitado);
            query.setParameter("correo", correo);
            query.setParameter("nacimiento", fechaNacimiento);
            result = query.executeUpdate();
            String[] valorUsuario = this.getNombre().split(" ");
            String[] valorApellido = this.getApellido().split(" ");
            System.out.println("" + valorUsuario.length);
            if (valorUsuario.length > 1) {
                usuario = valorUsuario[0] + valorUsuario[1] + valorApellido[0].charAt(0) + valorApellido[1].charAt(0);
            } else {
                usuario = valorUsuario[0] + valorApellido[0].charAt(0) + valorApellido[1].charAt(0);
            }
            if (result > 0) {
                Query queryUsuario = session.createSQLQuery("INSERT INTO usuario (usuario, clave, habilitado, fecha_usaurio) VALUES(:usuario, :usuario, 1, now())");
                queryUsuario.setParameter("usuario", usuario.toUpperCase());
                queryUsuario.setParameter("usuario", usuario);
                result = queryUsuario.executeUpdate();
                if (varios.enviarCorreos(this.getCorreo(), usuario.toUpperCase())== true) {
                    transaction.commit();
                }
            } else {
                transaction.rollback();// esto esta de mas jajajaj             
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            transaction.rollback();
        } finally {
            session.close();
            return resultado;
        }
    }

 

    public persona() {
    }

}
