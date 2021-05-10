package domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Clase entidad para la tabla Persona
 *
 * @author Julian Tovar
 * @since 2021-05-09
 */
@Getter
@Setter
@NoArgsConstructor
public class Persona implements Serializable {

    private Long id;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String sexo;
    private Integer edad;
    private String telefono;
    private String celular;

    @Override
    public String toString() {
        return getNombre() + " " + getApPaterno() + " " + getApMaterno();
    }
}
