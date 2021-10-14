package model;
// Generated Oct 7, 2021 7:16:23 AM by Hibernate Tools 4.3.1


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Seguimientopaciente generated by hbm2java
 */
@Entity
@Table(name="seguimientopaciente"
    ,catalog="dbclinica"
)
public class Seguimientopaciente  implements java.io.Serializable {


     private int idSeguimiento;
     private Expedientepaciente expedientepaciente;
     private String seguimiento;
     private String detalle;
     private Date fechaSeguimiento;
     private String archivo;

    public Seguimientopaciente() {
    }

	
    public Seguimientopaciente(int idSeguimiento, Expedientepaciente expedientepaciente, String seguimiento, String detalle, Date fechaSeguimiento) {
        this.idSeguimiento = idSeguimiento;
        this.expedientepaciente = expedientepaciente;
        this.seguimiento = seguimiento;
        this.detalle = detalle;
        this.fechaSeguimiento = fechaSeguimiento;
    }
    public Seguimientopaciente(int idSeguimiento, Expedientepaciente expedientepaciente, String seguimiento, String detalle, Date fechaSeguimiento, String archivo) {
       this.idSeguimiento = idSeguimiento;
       this.expedientepaciente = expedientepaciente;
       this.seguimiento = seguimiento;
       this.detalle = detalle;
       this.fechaSeguimiento = fechaSeguimiento;
       this.archivo = archivo;
    }
   
     @Id 

    
    @Column(name="idSeguimiento", unique=true, nullable=false)
    public int getIdSeguimiento() {
        return this.idSeguimiento;
    }
    
    public void setIdSeguimiento(int idSeguimiento) {
        this.idSeguimiento = idSeguimiento;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idExpediente", nullable=false)
    public Expedientepaciente getExpedientepaciente() {
        return this.expedientepaciente;
    }
    
    public void setExpedientepaciente(Expedientepaciente expedientepaciente) {
        this.expedientepaciente = expedientepaciente;
    }

    
    @Column(name="seguimiento", nullable=false, length=100)
    public String getSeguimiento() {
        return this.seguimiento;
    }
    
    public void setSeguimiento(String seguimiento) {
        this.seguimiento = seguimiento;
    }

    
    @Column(name="detalle", nullable=false, length=1000)
    public String getDetalle() {
        return this.detalle;
    }
    
    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="fechaSeguimiento", nullable=false, length=19)
    public Date getFechaSeguimiento() {
        return this.fechaSeguimiento;
    }
    
    public void setFechaSeguimiento(Date fechaSeguimiento) {
        this.fechaSeguimiento = fechaSeguimiento;
    }

    
    @Column(name="archivo", length=100)
    public String getArchivo() {
        return this.archivo;
    }
    
    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }




}

