package com.tauan.transacao_simplificada.service;

import com.tauan.transacao_simplificada.exceptions.UserNotFound;
import com.tauan.transacao_simplificada.infrastructure.entity.Usuario;
import com.tauan.transacao_simplificada.infrastructure.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }
    public Usuario buscarUsuario(Long id){
        return repository.findById(id).orElseThrow(() -> new UserNotFound("Usuário não encontrado"));
    }
}
