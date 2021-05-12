package domain.repository;

import lombok.Getter;

@Getter
public enum MedicamentoRepository {
    getAll("SELECT * FROM Medicamento"),
    getByNombre("SELECT * FROM Medicamento WHERE nombre=?"),
    save("INSERT INTO Medicamento (nombre, cantidad) VALUES (?, ?)"),
    delete("DELETE FROM Medicamento WHERE id = ?"),;

    private final String query;

    MedicamentoRepository(String query) {
        this.query = query;
    }
}
