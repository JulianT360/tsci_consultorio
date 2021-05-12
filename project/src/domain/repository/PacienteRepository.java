package domain.repository;

import lombok.Getter;

@Getter
public enum PacienteRepository {
    getAll("SELECT * FROM Paciente"),
    getById("SELECT * FROM Paciente WHERE id=?"),
    save("INSERT INTO Paciente (personaId) VALUES (?)"),
    update("UPDATE Paciente SET personaId = ? WHERE id = ?"),
    delete("DELETE FROM Paciente WHERE id = ?"),;

    private final String query;

    PacienteRepository(String query) {
        this.query = query;
    }
}
