/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebadynamicjasper;

import java.util.Date;

/**
 *
 * @author Natalia
 */
public class ClasePrueba {

    private String nombre;
    private String direccion;
    private String pais;
    private Integer codigo;
    private Date fecha;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
    
}
