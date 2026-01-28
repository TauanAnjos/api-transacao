package com.tauan.transacao_simplificada.service;

import com.tauan.transacao_simplificada.infrastructure.entity.Carteira;
import com.tauan.transacao_simplificada.infrastructure.repository.CarteiraRepository;
import com.tauan.transacao_simplificada.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CarteiraService {
    private final CarteiraRepository repository;

    public CarteiraService(CarteiraRepository repository) {
        this.repository = repository;
    }
    public void salvar(Carteira carteira){
        repository.save(carteira);
    }
}
