package com.attornatus.pessoa.controller;

import com.attornatus.pessoa.model.Pessoa;
import com.attornatus.pessoa.repository.PessoaRepository;
import com.attornatus.pessoa.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/attornatus/pessoas")
@AllArgsConstructor
public class PessoaController {
    PessoaRepository repository;


    @Autowired
    private PessoaService service;

    @GetMapping
    public ResponseEntity<?> buscarTodosUsuarios() {
        return service.buscarTodosUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorIdDaPessoa(@PathVariable Long id) {
        return service.buscarPorIdDaPessoa(id);
    }

    @PostMapping
    public ResponseEntity<?> salvarPessoa(@RequestBody Pessoa pessoa) {
        return service.salvarPessoa(pessoa);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPessoa(@PathVariable long id, @RequestBody Pessoa pessoa){
        return service.atualizarPessoa(id, pessoa);
    }

    @DeleteMapping(path = { "{id}" })
    public ResponseEntity<?> excluirPessoa(@PathVariable long id) {
        return service.excluirPessoa(id);

    }

}
