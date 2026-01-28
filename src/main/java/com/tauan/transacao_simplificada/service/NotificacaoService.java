package com.tauan.transacao_simplificada.service;

import com.tauan.transacao_simplificada.infrastructure.clients.NotificacaoClient;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {
    private final NotificacaoClient client;

    public NotificacaoService(NotificacaoClient client) {
        this.client = client;
    }

    public  void enviarNotificacao(){
        client.enviarNotificacao();
    }
}
