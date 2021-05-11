package domain.service;

import conexion.ConexionSql;
import domain.entity.*;
import domain.repository.*;

import java.sql.*;

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

    public Long saveMedico(Medico medico) {
        Long idMedico = null;
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt =
                    conn.prepareStatement(MedicoRepository.saveMedico.getQuery(), Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, medico.getCedula());
            stmt.setString(2, medico.getEspecialidad());
            stmt.setString(3, medico.getCategoria());
            stmt.setString(4, medico.getUsuario());
            stmt.setString(5, medico.getPassword());
            stmt.setLong(6, medico.getIdPersona());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Error al guardar la informacion de medico");
            }

            ResultSet generatedKey = stmt.getGeneratedKeys();
            if(generatedKey.next()) {
                idMedico = generatedKey.getLong(1);
            } else {
                throw new SQLException("Error al guardar la informacion de medico");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return idMedico;
    }

    public void updateMedico(Medico medico) {
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt =
                    conn.prepareStatement(MedicoRepository.updateMedico.getQuery(), Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, medico.getCedula());
            stmt.setString(2, medico.getEspecialidad());
            stmt.setString(3, medico.getCategoria());
            stmt.setString(4, medico.getUsuario());
            stmt.setString(5, medico.getPassword());
            stmt.setLong(6, medico.getIdPersona());
            stmt.setLong(7, medico.getId());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Error al guardar la informacion de medico");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(Long idMedico) {
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt =
                    conn.prepareStatement(MedicoRepository.deleteMedico.getQuery(), Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, idMedico);
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Error al guardar la informacion de persona");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
