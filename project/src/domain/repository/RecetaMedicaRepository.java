package domain.repository;

import lombok.Getter;

@Getter
public enum RecetaMedicaRepository {
    getById("SELECT * FROM receta_medica WHERE id = ?"),
    getByExpediente("SELECT * FROM receta_medica WHERE expediente = ?"),
    getByMedicoId("SELECT * FROM receta_medica WHERE medicoId=?"),
    getByPacienteId("SELECT * FROM receta_medica WHERE pacienteId=?"),
    getByMedicoIdAndPacienteId("SELECT * FROM receta_medica WHERE medicoId=? AND pacienteId=?"),

    save("INSERT INTO receta_medica(expediente, fecha, altura, peso, temperatura, presion_i, presion_f, ritmo_c, motivo_consulta, sintomas, instrucciones, medicoId, pacienteId) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)"),
    update("UPDATE receta_medica SET expediente=?, fecha=?, altura=?, peso=?, temperatura=?, presion_i=?, presion_f=?, ritmo_c=?, motivo_consulta=?, sintomas=?, instrucciones=?, medicoId=?, pacienteId=? WHERE id=?"),
    delete("DELETE FROM receta_medica WHERE id=?");

    private final String query;

    RecetaMedicaRepository(String query) {
        this.query = query;
    }
}
