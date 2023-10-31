package com.gestion.parqueadero.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placa", length = 6, nullable = false)
    private String placa;

    @Column(name = "hora_entrada", nullable = false)
    private int horaEntrada;

    @Column(name = "hora_salida")
    private int horaSalida;

    @Column(name = "ubicacion", length = 255)
    private String ubicacion;

    @Column(name = "tipo_vehiculo", length = 20)
    private String tipoVehiculo;

    public Vehiculo() {
    }

    public Vehiculo(String placa, int horaEntrada, String ubicacion) {
        this.placa = placa;
        this.horaEntrada = horaEntrada;
        this.ubicacion = ubicacion;
        // Establecer el tipo de vehículo por defecto (carro)
        this.tipoVehiculo = "carro";
    }

    // Getters y setters

    // ...
}
