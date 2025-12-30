package es.fpsumma.dam2.videoclub.persistence.jpa.repository;

import es.fpsumma.dam2.videoclub.persistence.jpa.entity.PeliculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PeliculaRepository extends JpaRepository<PeliculaEntity, Long> {


    Optional<PeliculaEntity> findByTitulo(String titulo);


    List<PeliculaEntity> findByGenero(String genero);

    List<PeliculaEntity> findByAnioEstreno(Integer anioEstreno);


    List<PeliculaEntity> findByPuntuacionGreaterThanEqual(Double puntuacion);
}