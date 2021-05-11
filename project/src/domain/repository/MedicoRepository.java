package domain.repository;

import lombok.Getter;

@Getter
public enum MedicoRepository {
    getAllMedicos("SELECT * FROM Medico"),
    getMedicoById("SELECT * FROM Medico WHERE id = ?"),
    getMedicoByUsuario("SELECT * FROM Medico WHERE usuario = ?"),
    saveMedico("INSERT INTO Medico (cedula, especialidad, categoria,"
            + "usuario, password, personaId) VALUES (?,?,?,?,?,?)"),
    updateMedico("UPDATE Persona SET cedula=?, especialidad=?, categoria=?," +
            "usuario=?, password=?, personaId=? where id=?");;

    private final String query;

    MedicoRepository(String query) {
        this.query = query;
    }
}
