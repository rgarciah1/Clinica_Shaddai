/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Cita;
import model.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author jose_
 */
public class CitaDaoImp implements CitaDao {

    @Override
    public boolean create(Cita cita, int idDoctor, String usuario) {
        boolean flag = false;
        UsuarioDao usuarioDao = new UsuarioDaoImp();
        System.out.println("Create Cita - usuario " + usuario);
        Usuario paciente = new Usuario();
        Usuario doctor = new Usuario();
        Usuario usuario2 = new Usuario();
        paciente.setUsuario(usuario);
        usuario2.setUsuario(usuario);
        paciente = usuarioDao.findByUsuario(paciente);
        doctor = usuarioDao.findById(idDoctor);
        usuario2 = usuarioDao.findByUsuario(usuario2);
        if (usuario2 == null) {
            System.out.println("Create Cita - usuario  nulo " + usuario2);
        }
        cita.setUsuarioByIdDoctor(doctor);
        cita.setUsuarioByIdPaciente(paciente);
        cita.setUsuarioByIdUsuario(usuario2);
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            Transaction tx = session.beginTransaction();
            try {
                session.save(cita);
                tx.commit();
                System.out.println("Se ha creado la cita");
                flag = true;
            } catch (Exception e) {
                flag = false;
                tx.rollback();
                System.out.println(e.toString());
            }
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return flag;
    }

    @Override
    public List<Cita> findByUsuario(String usuario) {
        System.out.println("ingresando a findByUsuario CITA");
        List<Cita> model;
        model = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Cita where idUsuario in "
                + "(select idUsuario from Usuario where usuario='" + usuario + "')";
        try {
            Transaction tx = session.beginTransaction();
            try {
                model = session.createQuery(sql).list();
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

}
