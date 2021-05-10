package domain.service;

import conexion.ConexionSql;
import domain.entity.Medico;
import domain.repository.MedicoRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Servicio para entidad de medico.
 *
 * @author Julian Tovar
 * @since 2021-05-09
 */
public class MedicoService {
    private final ConexionSql conexion;

    public MedicoService() {
        this.conexion = new ConexionSql();
    }

    public Medico getMedicoByUsuario(String usuario) {
        Medico medico = new Medico();
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(MedicoRepository.getMedicoByUsuario.getQuery());
            stmt.setString(1, usuario);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                medico = this.getResultsMedico(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return medico;
    }

    /**
     * Prepara los resultados de la consulta de personas
     * @param rs
     * @return
     */
    private Medico getResultsMedico(ResultSet rs) {
        Medico medico = new Medico();
        try {
            medico.setId(rs.getLong("id"));
            medico.setCedula(rs.getString("cedula"));
            medico.setEspecialidad(rs.getString("especialidad"));
            medico.setCategoria(rs.getString("categoria"));
            medico.setUsuario(rs.getString("usuario"));
            medico.setPassword(rs.getString("password"));
            medico.setIdPersona(rs.getLong("personaId"));
        } catch (SQLException sqle) {
            System.out.println(sqle.getLocalizedMessage());
        }
        return medico;
    }
}
