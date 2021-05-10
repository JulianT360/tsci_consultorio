package domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase entidad para tabla de municipio.
 *
 * @author Julian Tovar
 * @since 2021-05-09
 *
 */
@Getter
@Setter
@NoArgsConstructor
public class Municipio {
    private Long id;
    private String municipio;
    private Long estadoId;
}
