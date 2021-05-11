package domain.repository;

import lombok.Getter;

@Getter
public enum PersonaRepository {

    getAllPersonas("SELECT * FROM Persona"),
    getPersonaById("SELECT * FROM Persona WHERE id = ?"),
    savePersona("INSERT INTO Persona (nombre, ap_paterno, ap_materno,"
            + "sexo) VALUES (?,?,?,?)"),
    updatePersona("UPDATE Persona SET nombre=?, ap_paterno=?, ap_materno=?," +
            "sexo=?, edad=?, edad=?, telefono=?, celular=? where id=?");

    private final String query;

    PersonaRepository(String query) {
        this.query = query;
    }
}
