package com.balneario.Balneario.Repository;

import com.balneario.Balneario.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNombre(String nombre);

    List<Cliente> findByApellido(String apellido);

    List<Cliente> findByDni(String dni);

}
