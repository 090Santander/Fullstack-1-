package dev.bibliotecaduoc.bibliotecaduoc.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class Prestamo {

    private Integer idPrestamo;
    private Integer idLibro;
    private String runSolicitante;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaSolicitud;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaEntrega;

    private Integer cantidadDias;
    private Integer multas;

    public Prestamo() {
        this.multas = 0;
    }

    public Prestamo(Integer idPrestamo, Integer idLibro, String runSolicitante,
                    LocalDate fechaSolicitud, LocalDate fechaEntrega,
                    Integer cantidadDias, Integer multas) {
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.runSolicitante = runSolicitante;
        this.fechaSolicitud = fechaSolicitud;
        this.fechaEntrega = fechaEntrega;
        this.cantidadDias = cantidadDias;
        this.multas = multas;
    }

    public Integer getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(Integer idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

    public String getRunSolicitante() {
        return runSolicitante;
    }

    public void setRunSolicitante(String runSolicitante) {
        this.runSolicitante = runSolicitante;
    }

    public LocalDate getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDate fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getCantidadDias() {
        return cantidadDias;
    }

    public void setCantidadDias(Integer cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public Integer getMultas() {
        return multas;
    }

    public void setMultas(Integer multas) {
        this.multas = multas;
    }
}