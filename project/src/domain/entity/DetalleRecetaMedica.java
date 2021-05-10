package domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase entidad para tabla de detalle receta medica.
 *
 * @author Julian Tovar
 * @since 2021-05-09
 */

@Getter
@Setter
@NoArgsConstructor
public class DetalleRecetaMedica {
    private Long id;
    private Long idRecetaMedica;
    private Long idMedicamento;
    private Integer cantidad;
    private Integer frecuencia;
    private Boolean tomar;
    private String durante;
}
