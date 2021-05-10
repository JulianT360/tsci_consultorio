package domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase entidad para tabla de medico.
 *
 * @author Julian Tovar
 * @since 2021-05-09
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class Medico {
    private Long id;
    private String cedula;
    private String especialidad;
    private String categoria;
    private String usuario;
    private String password;
    private Long idPersona;
}
