package com.tauan.transacao_simplificada.service;

import com.tauan.transacao_simplificada.infrastructure.clients.AutorizacaoClient;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AutorizacaoService {
    private final AutorizacaoClient client;

    public AutorizacaoService(AutorizacaoClient client) {
        this.client = client;
    }
    public boolean validarTransferencia(){
        if (Objects.equals(client.validarAutorizacao().data().authorization(), "true")){
            return true;
        }
        return false;
    }
}
