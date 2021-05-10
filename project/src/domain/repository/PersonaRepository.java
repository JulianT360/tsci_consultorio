package domain.repository;

import lombok.Getter;

@Getter
public enum PersonaRepository {

    getAllPersonas("SELECT * FROM Persona"),
    getPersonaById("SELECT * FROM Persona WHERE id = ?");

    private final String query;

    PersonaRepository(String query) {
        this.query = query;
    }
}
