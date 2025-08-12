package com.balneario.Balneario.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "unidad_de_sombra")
public class UnidadDeSombra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tipo", length = 50, nullable = false)
    private String tipo;

    @Column(name = "ubicacion", length = 100, nullable = false)
    private String ubicacion;

    @Column(name = "precio_por_dia", nullable = false)
    private Double precioPorDia;

    @Column(name = "ocupada", nullable = false)
    private Boolean ocupada = false;

    public boolean isOcupada() {return Boolean.TRUE.equals(this.ocupada);}
}
