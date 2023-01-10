package com.attornatus.pessoa.service;

import com.attornatus.pessoa.model.Endereco;
import com.attornatus.pessoa.model.Pessoa;
import com.attornatus.pessoa.repository.EnderecoRepository;
import com.attornatus.pessoa.repository.PessoaRepository;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    EnderecoRepository repository;

    public List buscarTodosEnderecos() {
        return repository.findAll();
    }


    public ResponseEntity<?> buscarPorIdDoEndereco(Long id) {
        return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }



    public ResponseEntity<?> salvarEndereco(Endereco endereco) {

        try {

            return new ResponseEntity<Endereco>(this.repository.save(endereco), HttpStatus.CREATED);
        } catch (JpaSystemException | GenericJDBCException | HttpMessageNotReadableException | DataIntegrityViolationException e) {

            e.printStackTrace();
            return new ResponseEntity<String>(
                    "Dados informados inválido! Verificar se os dados informados já foram cadastrados.",
                    HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> atualizarEndereco(long id, Endereco endereco){

        try {

            return repository.findById(id).map(record -> {
                record.setCep(endereco.getCep());
                record.setLogradouro(endereco.getLogradouro());
                record.setNumero(endereco.getNumero());
                record.setCidade(endereco.getCidade());


                Endereco update = repository.save(record);

                return new ResponseEntity(update, HttpStatus.OK);
            }).orElse(ResponseEntity.badRequest().body("Não foi possível atualizar o usuário. Por favor, tente novamente."));

        } catch (Exception e) {

            e.printStackTrace();
            return new ResponseEntity("Erro não identificado", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    public ResponseEntity<?> excluirEndereco(long id) {

        return repository.findById(id).map(record -> {
            repository.deleteById(id);

            return ResponseEntity.ok().body("Usuario do ID " + id + " foi deletado com sucesso!");
        }).orElse(ResponseEntity.notFound().build());
    }

}
