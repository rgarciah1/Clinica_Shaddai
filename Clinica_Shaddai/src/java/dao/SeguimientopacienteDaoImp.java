/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Cita;
import model.Expedientepaciente;
import model.Seguimientopaciente;
import model.Usuario;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author jose_
 */
public class SeguimientopacienteDaoImp implements SeguimientopacienteDao{


    @Override
    public boolean create(Seguimientopaciente seguimiento, String usuario) {
       boolean flag = false;
        UsuarioDao usuarioDao = new UsuarioDaoImp();
        ExpedientepacienteDao expedientepacienteDao = new ExpedientepacienteDaoImp();
        System.out.println("Create seguimiento - usuario " +usuario);
        
        Usuario usuario2 = new Usuario();
        usuario2.setUsuario(usuario);
        usuario2 = usuarioDao.findByUsuario(usuario2);
        
         Expedientepaciente expediente = new Expedientepaciente();
        if(usuario2 != null)
        {
         expediente = expedientepacienteDao.FindExpediete(usuario2.getIdUsuario());
         seguimiento.setExpedientepaciente(expediente);   
        }else
        {
            
        }
        
        if(usuario2 == null)
            System.out.println("Create seguimiento - usuario  nulo " +usuario2);
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            if(seguimiento.getExpedientepaciente().getIdExpediente() != 0)
            {
                   Transaction tx = session.beginTransaction();
            try {
                session.save(seguimiento);
                tx.commit();
                System.out.println("Se ha creado el seguimiento");
                flag = true;
            } catch (Exception e) {
                flag = false;
                tx.rollback();
                System.out.println(e.toString());
            } 
            }
        
        } finally {
            if (session.isOpen()) {
                session.close();
            }
        }
        return flag;
    }

   
    
}
