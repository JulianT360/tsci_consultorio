package domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

/**
 * Clase entidad para tabla de receta medica.
 *
 * @author Julian Tovar
 * @since 2021-05-09
 */
@Getter
@Setter
@NoArgsConstructor
public class RecetaMedica {
    private Long id;
    private Long expediente;
    private Date fecha;
    private Integer altura;
    private Integer peso;
    private Integer temperatura;
    private Integer presionI;
    private Integer presionF;
    private Integer ritmoC;
    private String motivoConsulta;
    private String sintomas;
    private String instrucciones;
    private Long idMedico;
    private Long idPaciente;
}
