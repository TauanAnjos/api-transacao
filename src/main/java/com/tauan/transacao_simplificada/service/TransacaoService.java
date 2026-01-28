package com.tauan.transacao_simplificada.service;

import com.tauan.transacao_simplificada.controller.TransacaoDTO;
import com.tauan.transacao_simplificada.exceptions.BadRequestException;
import com.tauan.transacao_simplificada.infrastructure.entity.Carteira;
import com.tauan.transacao_simplificada.infrastructure.entity.TipoUsuario;
import com.tauan.transacao_simplificada.infrastructure.entity.Transacoes;
import com.tauan.transacao_simplificada.infrastructure.entity.Usuario;
import com.tauan.transacao_simplificada.infrastructure.repository.TransacoesRepository;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

@Service
@EnableFeignClients
public class TransacaoService {

    private final UsuarioService usuarioServiceervice;
    private final AutorizacaoService autorizacaoService;
    private final  CarteiraService carteiraService;
    private final TransacoesRepository transacoesRepository;
    private final NotificacaoService notificacaoService;

    public TransacaoService(UsuarioService usuarioServiceervice, AutorizacaoService autorizacaoService, CarteiraService carteiraService, TransacoesRepository transacoesRepository, NotificacaoService notificacaoService) {
        this.usuarioServiceervice = usuarioServiceervice;
        this.autorizacaoService = autorizacaoService;
        this.carteiraService = carteiraService;
        this.transacoesRepository = transacoesRepository;
        this.notificacaoService = notificacaoService;

    }
    @Transactional
    public void transferirValores(TransacaoDTO transacaoDTO){
       Usuario pagador = usuarioServiceervice.buscarUsuario(transacaoDTO.payer());
       Usuario recebedor = usuarioServiceervice.buscarUsuario(transacaoDTO.payee());

       validaPagadorLogista(pagador);
       validarSaldoUsuario(pagador, transacaoDTO.value());
       validarTransferencia();

       pagador.getCarteira().setSaldo(pagador.getCarteira().getSaldo().subtract(transacaoDTO.value()));
       atualizarSaldoCarteira(pagador.getCarteira());

        recebedor.getCarteira().setSaldo(pagador.getCarteira().getSaldo().add(transacaoDTO.value()));
        atualizarSaldoCarteira(recebedor.getCarteira());

        Transacoes transacoes = new Transacoes(null, transacaoDTO.value(), pagador, recebedor, null);

        transacoesRepository.save(transacoes);
        enviarNotificacao();
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
    private void validarTransferencia(){
        try{
            if (!autorizacaoService.validarTransferencia()){
                throw new IllegalArgumentException("Transação não autorizadam pela api.");
            }
        }catch (Exception e){
            throw new IllegalArgumentException(e.getMessage());
        }
    }
    private void atualizarSaldoCarteira(Carteira carteira){
        carteiraService.salvar(carteira);
    }
    private void enviarNotificacao(){
        try {
            notificacaoService.enviarNotificacao();
        }catch (HttpClientErrorException e){
            throw new BadRequestException("Erro ao enviar notificação.");
        }
    }
}
