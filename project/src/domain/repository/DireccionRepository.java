package domain.repository;

import lombok.Getter;

@Getter
public enum DireccionRepository {
    // Se obtienen los municipios
    getMunicipioById("SELECT * FROM municipio where id=?"),
    getMunicipioByEstado("SELECT * FROM municipio where estadoId=?"),
    // Se obtienen los estados
    getEstadoById("SELECT * FROM estado WHERE id=?"),
    getEstadoByPais("SELECT * FROM estado where paisId=?"),
    // Se obtienen los paises
    getPaisAll("SELECT * FROM pais"),
    getPaisById("SELECT * FROM pais WHERE id=?"),
    // Crud para direccion
    getAll("SELECT * FROM Direccion"),
    getById("SELECT * FROM Direccion WHERE id=?"),
    getByPersonaId("SELECT * FROM Direccion where personaId=?"),
    save("INSERT INTO Direccion (personaId, calle, numInt, numExt, municipioId) VALUES (?,?,?,?,?)"),
    update("UPDATE Direccion SET personaId=?, calle=?, numInt=?, numExt=?, municipioId=? WHERE id=?"),
    delete("DELETE FROM Direccion WHERE id=?"),;

    private final String query;

    DireccionRepository(String query) {
        this.query = query;
    }
}
