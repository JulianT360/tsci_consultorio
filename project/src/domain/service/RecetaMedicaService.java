package domain.service;

import conexion.ConexionSql;
import domain.entity.RecetaMedica;
import domain.repository.RecetaMedicaRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecetaMedicaService {
    private ConexionSql conexion;

    public RecetaMedicaService() {
        this.conexion = new ConexionSql();
    }

    public RecetaMedica getById(Long idReceta){
        RecetaMedica recetaMedica = new RecetaMedica();
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(RecetaMedicaRepository.getById.getQuery());
            stmt.setLong(1, idReceta);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                recetaMedica = this.getResultsRecetaMedica(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return recetaMedica;
    }

    public RecetaMedica getByExpediente(Long expediente){
        RecetaMedica recetaMedica = new RecetaMedica();
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(RecetaMedicaRepository.getByExpediente.getQuery());
            stmt.setLong(1, expediente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                recetaMedica = this.getResultsRecetaMedica(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return recetaMedica;
    }

    public RecetaMedica getByMedico(Long idMedico){
        RecetaMedica recetaMedica = new RecetaMedica();
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(RecetaMedicaRepository.getByMedicoId.getQuery());
            stmt.setLong(1, idMedico);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                recetaMedica = this.getResultsRecetaMedica(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return recetaMedica;
    }

    public RecetaMedica getByPaciente(Long idPaciente){
        RecetaMedica recetaMedica = new RecetaMedica();
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(RecetaMedicaRepository.getByPacienteId.getQuery());
            stmt.setLong(1, idPaciente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                recetaMedica = this.getResultsRecetaMedica(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return recetaMedica;
    }

    public RecetaMedica getByMedicoAndPaciente(Long idMedico, Long idPaciente){
        RecetaMedica recetaMedica = new RecetaMedica();
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(RecetaMedicaRepository.getByMedicoIdAndPacienteId.getQuery());
            stmt.setLong(1, idMedico);
            stmt.setLong(2, idPaciente);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                recetaMedica = this.getResultsRecetaMedica(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return recetaMedica;
    }

    public Long save(RecetaMedica recetaMedica) {
        Long idRecetaMedica = null;
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(RecetaMedicaRepository.save.getQuery(), Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, recetaMedica.getExpediente());
            stmt.setDate(2, recetaMedica.getFecha());
            stmt.setInt(3, recetaMedica.getAltura());
            stmt.setInt(4, recetaMedica.getPeso());
            stmt.setInt(5, recetaMedica.getTemperatura());
            stmt.setInt(6, recetaMedica.getPresionI());
            stmt.setInt(7, recetaMedica.getPresionF());
            stmt.setInt(8, recetaMedica.getRitmoC());
            stmt.setString(9, recetaMedica.getMotivoConsulta());
            stmt.setString(10, recetaMedica.getSintomas());
            stmt.setString(11, recetaMedica.getInstrucciones());
            stmt.setLong(12, recetaMedica.getIdMedico());
            stmt.setLong(13, recetaMedica.getIdPaciente());

            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Error al guardar la direccion");
            }

            ResultSet generatedKey = stmt.getGeneratedKeys();
            if(generatedKey.next()) {
                idRecetaMedica = generatedKey.getLong(1);
            } else {
                throw new SQLException("Error al guardar la informacion de medico");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return idRecetaMedica;
    }

    public Long update(RecetaMedica recetaMedica) {
        Long idRecetaMedica = null;
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(RecetaMedicaRepository.update.getQuery(), Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, recetaMedica.getExpediente());
            stmt.setDate(2, recetaMedica.getFecha());
            stmt.setInt(3, recetaMedica.getAltura());
            stmt.setInt(4, recetaMedica.getPeso());
            stmt.setInt(5, recetaMedica.getTemperatura());
            stmt.setInt(6, recetaMedica.getPresionI());
            stmt.setInt(7, recetaMedica.getPresionF());
            stmt.setInt(8, recetaMedica.getRitmoC());
            stmt.setString(9, recetaMedica.getMotivoConsulta());
            stmt.setString(10, recetaMedica.getSintomas());
            stmt.setString(11, recetaMedica.getInstrucciones());
            stmt.setLong(12, recetaMedica.getIdMedico());
            stmt.setLong(13, recetaMedica.getIdPaciente());
            stmt.setLong(14, recetaMedica.getId());

            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Error al guardar la direccion");
            }

            ResultSet generatedKey = stmt.getGeneratedKeys();
            if(generatedKey.next()) {
                idRecetaMedica = generatedKey.getLong(1);
            } else {
                throw new SQLException("Error al guardar la informacion de medico");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return idRecetaMedica;
    }

    public void delete(Long idRecetaMedica) {
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt =
                    conn.prepareStatement(RecetaMedicaRepository.delete.getQuery());
            stmt.setLong(1, idRecetaMedica);
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Error al eliminar el registro");
            }
        } catch (SQLException sqle) {
            Logger.getLogger(DireccionService.class.getName()).log(Level.SEVERE, sqle.getLocalizedMessage(), sqle);
        }
    }

    private RecetaMedica getResultsRecetaMedica(ResultSet rs) {
        RecetaMedica recetaMedica = new RecetaMedica();
        try {
            recetaMedica.setId(rs.getLong("id"));
            recetaMedica.setExpediente(rs.getLong("expediente"));
            recetaMedica.setFecha(rs.getDate("fecha"));
            recetaMedica.setAltura(rs.getInt("altura"));
            recetaMedica.setPeso(rs.getInt("peso"));
            recetaMedica.setTemperatura(rs.getInt("temperatura"));
            recetaMedica.setPresionI(rs.getInt("presion_i"));
            recetaMedica.setPresionF(rs.getInt("presion_f"));
            recetaMedica.setRitmoC(rs.getInt("ritmo_c"));
            recetaMedica.setMotivoConsulta(rs.getString("motivo_consulta"));
            recetaMedica.setSintomas(rs.getString("sintomas"));
            recetaMedica.setInstrucciones(rs.getString("instrucciones"));
            recetaMedica.setIdMedico(rs.getLong("medicoId"));
            recetaMedica.setIdPaciente(rs.getLong("pacienteId"));

        } catch (SQLException sqle) {
            System.out.println(sqle.getLocalizedMessage());
        }
        return recetaMedica;
    }
}
