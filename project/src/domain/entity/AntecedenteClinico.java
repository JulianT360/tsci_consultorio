package domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase entidad para la tabla antecedente clinico
 *
 * @author Julian Tovar
 * @since 2021-05-09
 */

@Getter
@Setter
@NoArgsConstructor
public class AntecedenteClinico {
    private Long id;
    private String tipo;
    private String descripcion;
    private Long idPaciente;
}
