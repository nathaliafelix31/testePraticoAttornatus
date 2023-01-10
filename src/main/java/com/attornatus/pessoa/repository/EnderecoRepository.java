package com.attornatus.pessoa.repository;

import com.attornatus.pessoa.model.Endereco;
import com.attornatus.pessoa.model.Pessoa;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

}