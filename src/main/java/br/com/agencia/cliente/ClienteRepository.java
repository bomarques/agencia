package br.com.agencia.cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

