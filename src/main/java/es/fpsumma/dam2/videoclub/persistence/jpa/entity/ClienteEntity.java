package es.fpsumma.dam2.videoclub.persistence.jpa.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "email", nullable = false, length = 150, unique = true)
    private String email;

    @OneToMany(mappedBy = "cliente")
    private List<AlquilerEntity> alquileres = new ArrayList<>();

    public ClienteEntity(List<AlquilerEntity> alquileres, String email, Long id, String nombre) {
        this.alquileres = alquileres;
        this.email = email;
        this.id = id;
        this.nombre = nombre;
    }

    public ClienteEntity() {}


    public List<AlquilerEntity> getAlquileres() {
        return alquileres;
    }

    public void setAlquileres(List<AlquilerEntity> alquileres) {
        this.alquileres = alquileres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
