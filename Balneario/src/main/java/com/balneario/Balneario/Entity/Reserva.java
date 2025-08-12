package com.balneario.Balneario.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private Date fechaFin;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_unidad", referencedColumnName = "id", nullable = false)
    private UnidadDeSombra unidadDeSombra;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false)
    private Cliente cliente;

    public Reserva() {
    }

    public Reserva(Cliente cliente, UnidadDeSombra unidadDeSombra, Date fechaInicio, Date fechaFin, String estado) {
        this.cliente = cliente;
        this.unidadDeSombra = unidadDeSombra;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
    }
}
