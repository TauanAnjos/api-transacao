package com.tauan.transacao_simplificada.infrastructure.repository;

import com.tauan.transacao_simplificada.infrastructure.entity.Transacoes;
import com.tauan.transacao_simplificada.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacoesRepository extends JpaRepository<Transacoes, Long> {
}
