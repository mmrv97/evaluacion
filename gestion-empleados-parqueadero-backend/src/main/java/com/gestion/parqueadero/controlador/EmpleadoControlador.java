package com.gestion.parqueadero.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.parqueadero.excepciones.ResourceNotFoundException;
import com.gestion.parqueadero.modelo.Vehiculo;
import com.gestion.parqueadero.repositorio.VehiculoRepositorio;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmpleadoControlador {

    @Autowired
    private VehiculoRepositorio repositorio;

    // Este método sirve para listar todos los vehículos
    @GetMapping("/vehiculos")
    public List<Vehiculo> listarTodosLosVehiculos() {
        return repositorio.findAll();
    }

    // Este método sirve para guardar un vehículo
    @PostMapping("/vehiculos")
    public Vehiculo guardarVehiculo(@RequestBody Vehiculo vehiculo) {
        return repositorio.save(vehiculo);
    }

    // Este método sirve para buscar un vehículo por su ID
    @GetMapping("/vehiculos/{id}")
    public ResponseEntity<Vehiculo> obtenerVehiculoPorId(@PathVariable Long id) {
        Vehiculo vehiculo = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el vehículo con el ID : " + id));
        return ResponseEntity.ok(vehiculo);
    }

    // Este método sirve para actualizar un vehículo
    @PutMapping("/vehiculos/{id}")
    public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable Long id, @RequestBody Vehiculo detallesVehiculo) {
        Vehiculo vehiculo = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el vehículo con el ID : " + id));

        vehiculo.setPlaca(detallesVehiculo.getPlaca());
        vehiculo.setHoraEntrada(detallesVehiculo.getHoraEntrada());
        vehiculo.setHoraSalida(detallesVehiculo.getHoraSalida());
        vehiculo.setUbicacion(detallesVehiculo.getUbicacion());
        vehiculo.setTipoVehiculo(detallesVehiculo.getTipoVehiculo());

        Vehiculo vehiculoActualizado = repositorio.save(vehiculo);
        return ResponseEntity.ok(vehiculoActualizado);
    }

    // Este método sirve para eliminar un vehículo
    @DeleteMapping("/vehiculos/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarVehiculo(@PathVariable Long id) {
        Vehiculo vehiculo = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el vehículo con el ID : " + id));

        repositorio.delete(vehiculo);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminar", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}

