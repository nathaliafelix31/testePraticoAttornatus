package com.attornatus.pessoa.controller;

import com.attornatus.pessoa.model.Endereco;
import com.attornatus.pessoa.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attornatus/endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService service;

    @GetMapping
    public List buscarTodosEnderecos() {
        return service.buscarTodosEnderecos();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<?> buscarPorIdDoEndereco(@PathVariable Long id){
        return service.buscarPorIdDoEndereco(id);
    }

    @PostMapping
    public ResponseEntity<?> salvarEndereco(@RequestBody Endereco endereco) {
        return service.salvarEndereco(endereco);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PutMapping(value = "{id}")
    public ResponseEntity<?> atualizarEndereco(@PathVariable long id, @RequestBody Endereco endereco){
        return service.atualizarEndereco(id, endereco);
    }

    @DeleteMapping(path = { "{id}" })
    public ResponseEntity<?> excluirEndereco(@PathVariable long id) {
        return service.excluirEndereco(id);
    }

}