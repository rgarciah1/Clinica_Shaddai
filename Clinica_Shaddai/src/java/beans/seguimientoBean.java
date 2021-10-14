/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CitaDao;
import dao.CitaDaoImp;
import dao.SeguimientopacienteDao;
import dao.SeguimientopacienteDaoImp;
import dao.UsuarioDao;
import dao.UsuarioDaoImp;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Cita;
import model.Expedientepaciente;
import model.Seguimientopaciente;
import model.Usuario;


/**
 *
 * @author Eduardo
 */
public class seguimientoBean {

    public String usuario;
    private Expedientepaciente expediente;
    private Seguimientopaciente selectseguimiento;
    
        public seguimientoBean(){
             inicializar();
    }
     public final void inicializar(){
        this.selectseguimiento = new Seguimientopaciente(0, new Expedientepaciente(), "",  "", new Date(), "");
        this.usuario="";
    }

    public String getUsuario() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario").toString();
    }

     public void setUsuario(String usuraio) {
        this.usuario = usuario;
    }

    public Expedientepaciente getExpediente() {
        return expediente;
    }

    public void setExpediente(Expedientepaciente expediente) {
        this.expediente = expediente;
    }

    public Seguimientopaciente getSelectseguimiento() {
        return selectseguimiento;
    }

    public void setSelectseguimiento(Seguimientopaciente selectseguimiento) {
        this.selectseguimiento = selectseguimiento;
    }
     
    public void btnCreateSeguimiento(ActionEvent actionEvent) {
        SeguimientopacienteDao seguimientoDao = new SeguimientopacienteDaoImp();
        String msg;
        if (seguimientoDao.create(this.selectseguimiento,getUsuario())) {
            msg = "Se creó correctamente el registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro.";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }    
    
}