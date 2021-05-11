package domain.service;

import conexion.ConexionSql;
import domain.entity.Persona;
import domain.repository.PersonaRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio para entidad de personas.
 *
 * @author Julian Tovar
 * @since 2021-05-09
 */
public class PersonaService {

    private final ConexionSql conexion;

    public PersonaService() {
        this.conexion = new ConexionSql();
    }
    /**
     * Consulta todas las personas.
     * @return
     */
    public List<Persona> getAllPersonas() {
        List<Persona> personas = new ArrayList<>();
        try(Connection conn = conexion.conectar()) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(PersonaRepository.getAllPersonas.getQuery());

            while (rs.next()) {
                personas.add(this.getResultsPersona(rs));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personas;
    }

    /**
     * Consulta registro de persona por id
     *
     * @param idPersona
     * @return
     */
    public Persona getPersonaById(Long idPersona) {
        Persona persona = new Persona();
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(PersonaRepository.getPersonaById.getQuery());
            stmt.setLong(1, idPersona);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                persona = this.getResultsPersona(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return persona;
    }

    public Long savePersona(Persona persona) {
        String[] returnId = { "BATCHID" };
        Long idPersona = null;
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt =
                    conn.prepareStatement(PersonaRepository.savePersona.getQuery(), Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApPaterno());
            stmt.setString(3, persona.getApMaterno());
            stmt.setString(4, persona.getSexo());
            //stmt.setInt(5, persona.getEdad());
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Error al guardar la informacion de persona");
            }

            ResultSet generatedKey = stmt.getGeneratedKeys();
            if(generatedKey.next()) {
                idPersona = generatedKey.getLong(1);
            } else {
                throw new SQLException("Error al guardar la informacion de persona");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return idPersona;
    }

    /**
     * Prepara los resultados de la consulta de personas
     * @param rs
     * @return
     */
    private Persona getResultsPersona(ResultSet rs) {
        Persona persona = new Persona();
        try {
            persona.setId(rs.getLong("id"));
            persona.setNombre(rs.getString("nombre"));
            persona.setApPaterno(rs.getString("ap_paterno"));
            persona.setApMaterno(rs.getString("ap_materno"));
            persona.setEdad(rs.getInt("edad"));
            persona.setSexo(rs.getString("sexo"));
            persona.setTelefono(rs.getString("telefono"));
            persona.setCelular(rs.getString("celular"));

        } catch (SQLException sqle) {
            System.out.println(sqle.getLocalizedMessage());
        }
        return persona;
    }
}
