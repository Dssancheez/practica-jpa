package es.fpsumma.dam2.videoclub.persistence.jpa.repository;

import es.fpsumma.dam2.videoclub.persistence.jpa.entity.AlquilerEntity;
import es.fpsumma.dam2.videoclub.persistence.jpa.entity.ClienteEntity;
import es.fpsumma.dam2.videoclub.persistence.jpa.entity.PeliculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AlquilerRepository extends JpaRepository<AlquilerEntity, Long> {

    List<AlquilerEntity> findByCliente(ClienteEntity cliente);

    List<AlquilerEntity> findByPelicula(PeliculaEntity pelicula);

    List<AlquilerEntity> findByFechaAlquilerGreaterThanEqual(LocalDate fechaAlquiler);

    List<AlquilerEntity> findByFechaDevolucionIsNull();
}
