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
