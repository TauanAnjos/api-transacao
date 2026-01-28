package com.tauan.transacao_simplificada.service;

import com.tauan.transacao_simplificada.controller.TransacaoDTO;
import com.tauan.transacao_simplificada.infrastructure.entity.TipoUsuario;
import com.tauan.transacao_simplificada.infrastructure.entity.Usuario;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferenciasService {

    private final UsuarioService usuarioServiceervice;

    public TransferenciasService(UsuarioService usuarioServiceervice) {
        this.usuarioServiceervice = usuarioServiceervice;
    }

    public void transferirValores(TransacaoDTO transacaoDTO){
       Usuario pagador = usuarioServiceervice.buscarUsuario(transacaoDTO.payer());
       Usuario recebedor = usuarioServiceervice.buscarUsuario(transacaoDTO.payee());

       validaPagadorLogista(pagador);
       validarSaldoUsuario(pagador, transacaoDTO.value());

    }
    private void validaPagadorLogista(Usuario usuario){
        try{
            if(usuario.getTipoUsuario().equals(TipoUsuario.LOGISTA)){
                throw new IllegalArgumentException("Transação não autorizada para esse tipo de usuário.");
            }
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    private void validarSaldoUsuario(Usuario usuario, BigDecimal valor){
        try{
            if (usuario.getCarteira().getSaldo().compareTo(valor) < 0){
                throw new IllegalArgumentException("Transação não autorizadam, saldo insuficiente.");
            }
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
