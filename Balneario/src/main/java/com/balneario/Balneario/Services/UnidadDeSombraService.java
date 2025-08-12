package com.balneario.Balneario.Services;

import com.balneario.Balneario.Entity.UnidadDeSombra;
import com.balneario.Balneario.Repository.UnidadDeSombraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadDeSombraService {

    @Autowired
    private UnidadDeSombraRepository unidadDeSombraRepository;

    public List<UnidadDeSombra> findAll(){
        return unidadDeSombraRepository.findAll();
    }

    public Optional<UnidadDeSombra> findById(Integer id) {
        return unidadDeSombraRepository.findById(id);
    }

    public UnidadDeSombra save(UnidadDeSombra unidadDeSombra) {

        return unidadDeSombraRepository.save(unidadDeSombra);
    }

    public void deleteById(Integer id) {
        unidadDeSombraRepository.deleteById(id);
    }
}
