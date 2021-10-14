/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Cita;
import model.Expedientepaciente;
import model.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author jose_
 */
public class ExpedientepacienteDaoImp implements ExpedientepacienteDao{

    @Override
    public Expedientepaciente FindExpediete(int paciente) {
        System.out.println("ingresando a FindExpediete");
        Expedientepaciente model = null;
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        String sql = "from Expedientepaciente where idPaciente ='" + paciente+ "'";
        try {
            Transaction tx = session.beginTransaction();
            try {
                model = (Expedientepaciente) session.createQuery(sql).uniqueResult();
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
