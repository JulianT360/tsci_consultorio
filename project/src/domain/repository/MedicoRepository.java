package domain.repository;

import lombok.Getter;

@Getter
public enum MedicoRepository {
    getAllMedicos("SELECT * FROM medico"),
    getMedicoById("SELECT * FROM medico WHERE id = ?"),
    getMedicoByUsuario("SELECT * FROM medico WHERE usuario = ?");

    private final String query;

    MedicoRepository(String query) {
        this.query = query;
    }
}
