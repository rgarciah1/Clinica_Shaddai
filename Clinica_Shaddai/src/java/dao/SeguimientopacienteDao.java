/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Cita;
import model.Expedientepaciente;
import model.Seguimientopaciente;

/**
 *
 * @author jose_
 */
public interface SeguimientopacienteDao {
    public boolean create(Seguimientopaciente seguimiento, String usuario);
}
