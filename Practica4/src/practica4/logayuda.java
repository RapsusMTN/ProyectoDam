/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica4;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Admin
 */
public class logayuda {
    
    private int id;
    private Date fecha;
    private Time hora;
    private boolean contenido;
    private boolean buscar;

    public int getId() {
        return id;
    }

    public Date getFecha() {
        return fecha;
    }

    public Time getHora() {
        return hora;
    }

    public boolean isContenido() {
        return contenido;
    }

    public boolean isBuscar() {
        return buscar;
    }

    @Override
    public String toString() {
        return "logayuda{" + "id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", contenido=" + contenido + ", buscar=" + buscar + '}';
    }
    
}
