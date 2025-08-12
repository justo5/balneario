package com.balneario.Balneario.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.balneario.Balneario.Entity.Reserva;
import com.balneario.Balneario.Repository.ReservaRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> findAll(){

        return reservaRepository.findAll();
    }

    public Optional<Reserva> findById(Integer id) {


        return reservaRepository.findById(id);
    }

    public Reserva save(Reserva solicitud) {

        return reservaRepository.save(solicitud);
    }

    public void deleteById(Integer id) {

        reservaRepository.deleteById(id);
    }
}
