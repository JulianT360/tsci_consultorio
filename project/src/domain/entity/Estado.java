package domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase entidad para el estado
 *
 * @author Julian Tovar
 * @since 2021-05-09
 */
@Getter
@Setter
@NoArgsConstructor
public class Estado {
    private Long id;
    private String estado;
    private Long idPaisl;
}
