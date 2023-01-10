package com.attornatus.pessoa.service;

import com.attornatus.pessoa.model.Pessoa;
import com.attornatus.pessoa.repository.PessoaRepository;
import org.hibernate.exception.GenericJDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Collection;
import java.util.Optional;

@Service
    public class PessoaService {

        @Autowired
        private PessoaRepository repository;

        public ResponseEntity<?> buscarPorIdDaPessoa(Long id) {

            Optional<Pessoa> record = this.repository.findById(id);

            if (record.orElseGet(() -> null) != null) return new ResponseEntity<Pessoa>(record.get(), HttpStatus.OK);

            return new ResponseEntity<String>("Usuário não localizado. Tente novamente!", HttpStatus.BAD_REQUEST);
        }

        public ResponseEntity<?> buscarTodosUsuarios() {

            try {

                Collection<Pessoa> lista = this.repository.findAll();
                return new ResponseEntity<Collection<Pessoa>>(lista, HttpStatus.OK);

            } catch (MethodArgumentTypeMismatchException | NumberFormatException e) {

                e.printStackTrace();
                return new ResponseEntity<String>(
                        "Não foi possível encontrar os dados. Verifique se o link digitado está correto.",
                        HttpStatus.NOT_FOUND);
            }
        }

        public ResponseEntity<?> salvarPessoa(Pessoa pessoa) {

            try {

                return new ResponseEntity<Pessoa>(this.repository.save(pessoa), HttpStatus.CREATED);

            } catch (JpaSystemException | GenericJDBCException | HttpMessageNotReadableException | DataIntegrityViolationException e) {

                e.printStackTrace();
                return new ResponseEntity<String>(
                        "Dados informados inválido! Verificar se os dados informados já foram cadastrados.",
                        HttpStatus.BAD_REQUEST);
            }
        }

        public ResponseEntity<?> atualizarPessoa(long id, Pessoa pessoa) {

            try {

                return repository.findById(id).map(record -> {
                    record.setNome(pessoa.getNome());
                    record.setDataNascimento(pessoa.getDataNascimento());

                    Pessoa update = repository.save(record);

                    return new ResponseEntity(update, HttpStatus.OK);

                }).orElse(ResponseEntity.badRequest().body("Não foi possível atualizar o usuário. Por favor, tente novamente."));

            } catch (Exception e) {

                e.printStackTrace();
                return new ResponseEntity("Erro não identificado", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }

        public ResponseEntity<?> excluirPessoa(long id) {

            return repository.findById(id).map(record -> {
                repository.deleteById(id);

                return ResponseEntity.ok().body("Pessoa com ID " + id + " foi deletada com sucesso!");
            }).orElse(ResponseEntity.notFound().build());
        }


}
