package domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase entidad para tabla de paciente
 *
 * @author Julian Tovar
 * @since 2021-05-09
 */
@Getter
@Setter
@NoArgsConstructor
public class Paciente {
    private Long id;
    private Long idPersona;
}
