package domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase entidad para la tabla de medicamento.
 *
 * @author Julian Tovar
 * @since 2021-05-09
 */

@Getter
@Setter
@NoArgsConstructor
public class Medicamento {
    private Long id;
    private String nombre;
    private Integer cantidad;
}
