/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author jose_
 */
public class UsuarioDaoImp implements UsuarioDao{
    
    @Override
    public Usuario findByUsuario(Usuario usuario) {
        System.out.println("ingresando a findByUsuario");
        Usuario model = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Usuario where usuario ='" + usuario.getUsuario()+ "'";
        try {
            Transaction tx = session.beginTransaction();
            try {
                model = (Usuario) session.createQuery(sql).uniqueResult();
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                System.out.println(e.toString());
            }
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return model; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Usuario login(Usuario usuario) {
        System.out.println("Ingresando a login");
        Usuario model = this.findByUsuario(usuario);
        if (model != null) {
            if (!usuario.getClave().equals(model.getClave())) {
                model = null;
            }
        }
        return model;
    }

    @Override
    public List<Usuario> findAllDoctors() {
        List<Usuario> listDoctors = new ArrayList<Usuario>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Usuario where idTipoUsuario in (select idTipoUsuario from Tipousuario where tipo ='Doctor')";
        try {
            Transaction tx = session.beginTransaction();
            try {
                listDoctors = session.createQuery(sql).list();
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                System.out.println(e.toString());
            }
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return listDoctors;
    }

    @Override
    public Usuario findById(int idUsuario) {
        System.out.println("ingresando a findById");
        Usuario model = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Usuario where idUsuario ='" + idUsuario+ "'";
        try {
            Transaction tx = session.beginTransaction();
            try {
                model = (Usuario) session.createQuery(sql).uniqueResult();
                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                System.out.println(e.toString());
            }
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return model;
    }
    
}
