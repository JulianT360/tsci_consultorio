package domain.service;

import conexion.ConexionSql;
import domain.entity.Paciente;
import domain.repository.PacienteRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PacienteService {

    private final ConexionSql conexion;

    public PacienteService() {
        this.conexion = new ConexionSql();
    }
    /**
     * Consulta todas las personas.
     * @return
     */
    public List<Paciente> getAll() {
        List<Paciente> pacientes = new ArrayList<>();
        try(Connection conn = conexion.conectar()) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(PacienteRepository.getAll.getQuery());

            while (rs.next()) {
                pacientes.add(this.getResultsPaciente(rs));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pacientes;
    }

    /**
     * Consulta registro de persona por id
     *
     * @param idPaciente
     * @return
     */
    public Paciente getById(Long idPaciente) {
        Paciente paciente = new Paciente();
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(PacienteRepository.getById.getQuery());
            stmt.setLong(1, idPaciente);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                paciente = this.getResultsPaciente(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return paciente;
    }

    public Long save(Paciente paciente) {
        Long idPaciente = null;
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt =
                    conn.prepareStatement(PacienteRepository.save.getQuery(), Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, paciente.getIdPersona());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Error al guardar la informacion de persona");
            }

            ResultSet generatedKey = stmt.getGeneratedKeys();
            if(generatedKey.next()) {
                idPaciente = generatedKey.getLong(1);
            } else {
                throw new SQLException("Error al guardar la informacion de persona");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return idPaciente;
    }

    public void update(Paciente paciente) {
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt =
                    conn.prepareStatement(PacienteRepository.update.getQuery(), Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, paciente.getIdPersona());
            stmt.setLong(2, paciente.getId());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Error al guardar la informacion de persona");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(Long idPaciente) {
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt =
                    conn.prepareStatement(PacienteRepository.delete.getQuery(), Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, idPaciente);
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
    private Paciente getResultsPaciente(ResultSet rs) {
        Paciente paciente = new Paciente();
        try {
            paciente.setId(rs.getLong("id"));
            paciente.setIdPersona(rs.getLong("personaId"));

        } catch (SQLException sqle) {
            System.out.println(sqle.getLocalizedMessage());
        }
        return paciente;
    }
}
