package es.fpsumma.dam2.videoclub.persistence.jpa.repository;

import es.fpsumma.dam2.videoclub.persistence.jpa.entity.ActorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ActorRepository extends JpaRepository<ActorEntity, Long> {

    Optional<ActorEntity> findByNombre(String nombre);

    List<ActorEntity> findByNombreContainingIgnoreCase(String nombre);

    List<ActorEntity> findAllByOrderByNombreAsc();
}
