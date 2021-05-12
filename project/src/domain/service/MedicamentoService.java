package domain.service;

import conexion.ConexionSql;
import domain.entity.Medicamento;
import domain.repository.MedicamentoRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MedicamentoService {

    private final ConexionSql conexion;

    public MedicamentoService() {
        this.conexion = new ConexionSql();
    }

    public List<Medicamento> getAll() {
        List<Medicamento> medicamentos = new ArrayList<>();
        try (Connection conn = conexion.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(MedicamentoRepository.getAll.getQuery());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                medicamentos.add(this.getResultsMedicamento(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return medicamentos;
    }

    public Medicamento getByNombre(String nombre) {
        Medicamento medicamento = new Medicamento();
        try (Connection conn = conexion.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(MedicamentoRepository.getByNombre.getQuery());
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                medicamento = this.getResultsMedicamento(rs);
            }
        } catch (SQLException sqle) {
            Logger.getLogger(MedicamentoService.class.getName()).log(Level.SEVERE, sqle.getLocalizedMessage(), sqle);
        }
        return medicamento;
    }

    public Long save(Medicamento medicamento) {
        Long idMedicamento = null;
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt =
                    conn.prepareStatement(MedicamentoRepository.save.getQuery(), Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, medicamento.getNombre());
            stmt.setInt(2, medicamento.getCantidad());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Error al guardar la informacion de medico");
            }

            ResultSet generatedKey = stmt.getGeneratedKeys();
            if(generatedKey.next()) {
                idMedicamento = generatedKey.getLong(1);
            } else {
                throw new SQLException("Error al guardar la informacion de medico");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return idMedicamento;
    }

    public void delete(Long idMedicamento) {
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt =
                    conn.prepareStatement(MedicamentoRepository.delete.getQuery(), Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, idMedicamento);
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Error al eliminar el registro");
            }
        } catch (SQLException sqle) {
            Logger.getLogger(MedicamentoService.class.getName()).log(Level.SEVERE, sqle.getLocalizedMessage(), sqle);
        }
    }

    private Medicamento getResultsMedicamento(ResultSet rs) {
        Medicamento medicamento = new Medicamento();
        try {
            medicamento.setId(rs.getLong("id"));
            medicamento.setNombre(rs.getString("nombre"));
            medicamento.setCantidad(rs.getInt("cantidad"));
        } catch (SQLException sqle) {
            System.out.println(sqle.getLocalizedMessage());
        }
        return medicamento;
    }
}
