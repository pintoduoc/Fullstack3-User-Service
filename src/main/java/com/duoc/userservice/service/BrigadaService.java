package com.duoc.userservice.service;

import com.duoc.userservice.model.Brigada;
import com.duoc.userservice.repository.BrigadaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrigadaService {
    @Autowired
    private BrigadaRepository brigadaRepository;

    public List<Brigada> findAll() {
        return brigadaRepository.findAll();
    }

    public Brigada findById(Long id) {
        return brigadaRepository.findById(id).orElse(null);
    }

    public Brigada save(Brigada brigada) {
        return brigadaRepository.save(brigada);
    }

    public void deleteById(Long id) {
        brigadaRepository.deleteById(id);
    }
}
