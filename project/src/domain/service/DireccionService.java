package domain.service;

import conexion.ConexionSql;
import domain.entity.Direccion;
import domain.entity.Estado;
import domain.entity.Municipio;
import domain.entity.Pais;
import domain.repository.DireccionRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DireccionService {
    private ConexionSql conexion;

    public DireccionService() {
        this.conexion = new ConexionSql();
    }

    public List<Pais> getPaisAll() {
        List<Pais> paises = new ArrayList<>();
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(DireccionRepository.getPaisAll.getQuery());
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                paises.add(this.getResultsPais(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return paises;
    }

    public Pais getPaisById(Long idPais){
        Pais pais = new Pais();
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(DireccionRepository.getPaisById.getQuery());
            stmt.setLong(1, idPais);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                pais = this.getResultsPais(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pais;
    }

    public List<Estado> getEstadosByPais(Long idPais) {
        List<Estado> estados = new ArrayList<>();
        try {
            Connection conn = conexion.conectar();
            PreparedStatement stmt = conn.prepareStatement(DireccionRepository.getEstadoByPais.getQuery());
            stmt.setLong(1, idPais);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                estados.add(this.getResultsEstado(rs));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return estados;
    }

    public Estado getEstadoById(Long idEstado) {
        Estado estado = new Estado();
        try {
            Connection conn = conexion.conectar();
            PreparedStatement stmt = conn.prepareStatement(DireccionRepository.getEstadoById.getQuery());
            stmt.setLong(1, idEstado);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                estado = this.getResultsEstado(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return estado;
    }

    public List<Municipio> getMunicipiosByEstado(Long idEstado) {
        List<Municipio> municipios = new ArrayList<>();
        try {
            Connection conn = conexion.conectar();
            PreparedStatement stmt = conn.prepareStatement(DireccionRepository.getMunicipioByEstado.getQuery());
            stmt.setLong(1, idEstado);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                municipios.add(this.getResultsMunicipio(rs));
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return municipios;
    }

    public Municipio getMunicipiosById(Long idMunicipio) {
        Municipio municipio = new Municipio();
        try {
            Connection conn = conexion.conectar();
            PreparedStatement stmt = conn.prepareStatement(DireccionRepository.getMunicipioById.getQuery());
            stmt.setLong(1, idMunicipio);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                municipio = this.getResultsMunicipio(rs);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return municipio;
    }

    public Direccion getByIdPersona(Long idPersona) {
        Direccion direccion = new Direccion();
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(DireccionRepository.getByPersonaId.getQuery());
            stmt.setLong(1, idPersona);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                direccion = this.getResultsDireccion(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return direccion;
    }

    public Long save(Direccion direccion) {
        Long idDireccion = null;
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(DireccionRepository.save.getQuery(), Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, direccion.getIdPersona());
            stmt.setString(2, direccion.getCalle());
            stmt.setString(3, direccion.getNumInt());
            stmt.setString(4, direccion.getNumExt());
            stmt.setLong(5, direccion.getIdMunicipio());

            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Error al guardar la direccion");
            }

            ResultSet generatedKey = stmt.getGeneratedKeys();
            if(generatedKey.next()) {
                idDireccion = generatedKey.getLong(1);
            } else {
                throw new SQLException("Error al guardar la informacion de medico");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return idDireccion;
    }

    public Long update(Direccion direccion) {
        Long idDireccion = null;
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt = conn.prepareStatement(DireccionRepository.update.getQuery(), Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, direccion.getIdPersona());
            stmt.setString(2, direccion.getCalle());
            stmt.setString(3, direccion.getNumInt());
            stmt.setString(4, direccion.getNumExt());
            stmt.setLong(5, direccion.getIdMunicipio());
            stmt.setLong(6, direccion.getId());

            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Error al guardar la direccion");
            }

            ResultSet generatedKey = stmt.getGeneratedKeys();
            if(generatedKey.next()) {
                idDireccion = generatedKey.getLong(1);
            } else {
                throw new SQLException("Error al guardar la informacion de medico");
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return idDireccion;
    }

    public void delete(Long idDireccion) {
        try(Connection conn = conexion.conectar()) {
            PreparedStatement stmt =
                    conn.prepareStatement(DireccionRepository.delete.getQuery(), Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, idDireccion);
            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0) {
                throw new SQLException("Error al eliminar el registro");
            }
        } catch (SQLException sqle) {
            Logger.getLogger(DireccionService.class.getName()).log(Level.SEVERE, sqle.getLocalizedMessage(), sqle);
        }
    }

    private Direccion getResultsDireccion(ResultSet rs) {
        Direccion direccion = new Direccion();
        try {
            direccion.setId(rs.getLong("id"));
            direccion.setIdPersona(rs.getLong("personaId"));
            direccion.setCalle(rs.getString("calle"));
            direccion.setNumInt(rs.getString("numInt"));
            direccion.setNumExt(rs.getString("numExt"));
            direccion.setIdMunicipio(rs.getLong("municipioId"));
        } catch (SQLException sqle) {
            System.out.println(sqle.getLocalizedMessage());
        }
        return direccion;
    }

    private Pais getResultsPais(ResultSet rs) {
        Pais pais = new Pais();
        try {
            pais.setId(rs.getLong("id"));
            pais.setPais(rs.getString("pais"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return pais;
    }

    private Estado getResultsEstado(ResultSet rs){
        Estado estado = new Estado();
        try {
            estado.setId(rs.getLong("id"));
            estado.setEstado(rs.getString("estado"));
            estado.setIdPais(rs.getLong("paisId"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return estado;
    }

    private Municipio getResultsMunicipio(ResultSet rs) {
        Municipio municipio = new Municipio();
        try {
            municipio.setId(rs.getLong("id"));
            municipio.setMunicipio(rs.getString("municipio"));
            municipio.setEstadoId(rs.getLong("estadoId"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return municipio;
    }
}
