package domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase entidad para tabla de direccion.
 *
 * @author Julian Tovar
 * @since 2021-05-09
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class Direccion {
    private Long id;
    private Long idPersona;
    private String calle;
    private String numInt;
    private String numExt;
    private Long idMunicipio;
}
