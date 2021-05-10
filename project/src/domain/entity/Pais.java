package domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase entidad para tabla de pais
 *
 * @author Julian Tovar
 * @since 2021-05-09
 */
@Getter
@Setter
@NoArgsConstructor
public class Pais {
    private Long id;
    private String pais;
}
