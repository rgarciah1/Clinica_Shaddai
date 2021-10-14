/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Cita;

/**
 *
 * @author jose_
 */
public interface CitaDao {

    public boolean create(Cita cita, int idDoctor, String usuario);

    public List<Cita> findByUsuario(String usuario);
}
