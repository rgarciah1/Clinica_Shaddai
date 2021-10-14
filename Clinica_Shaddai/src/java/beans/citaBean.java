/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.CitaDao;
import dao.CitaDaoImp;
import dao.UsuarioDao;
import dao.UsuarioDaoImp;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Cita;
import model.Usuario;

/**
 *
 * @author jose_
 */
public final class citaBean {

    public String usuario;
    private List<Usuario> doctores;
    private Cita selectedCita;
    private int idDoctor;
    private int idPaciene;
    public List<Cita> citas;

    public citaBean() {
        inicializar();
        setUsuario(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario").toString());
  
    }

    public Cita getSelectedCita() {
        return selectedCita;
    }

    public void setSelectedCita(Cita selectedCita) {
        this.selectedCita = selectedCita;
    }

    public List<Cita> getCitas() {
        CitaDao citaDao = new CitaDaoImp();
        citas = citaDao.findByUsuario(getUsuario());
        return citas;
    }

    public void setCitas(List<Cita> citas) {
        this.citas = citas;
    }

    public final void inicializar() {
        this.selectedCita = new Cita(0, new Usuario(), new Usuario(), new Usuario(), "", new Date(), 1);
        this.usuario = "";
        this.idDoctor = 0;
        this.idPaciene = 0;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public int getIdPaciene() {
        return idPaciene;
    }

    public void setIdPaciene(int idPaciene) {
        this.idPaciene = idPaciene;
    }

    public String getUsuario() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario").toString();
    }

    public void setUsuario(String usuraio) {
        this.usuario = usuario;
    }

    public List<Usuario> getDoctores() {
        this.doctores = new ArrayList<Usuario>();
        UsuarioDao usuarioDao = new UsuarioDaoImp();
        this.doctores = usuarioDao.findAllDoctors();
        return doctores;
    }

    public void setDoctores(List<Usuario> doctores) {
        this.doctores = doctores;
    }

    public void btnCreateCita(ActionEvent actionEvent) {
        CitaDao citaDao = new CitaDaoImp();
        String msg;
        if (citaDao.create(this.selectedCita, this.idDoctor, getUsuario())) {
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
