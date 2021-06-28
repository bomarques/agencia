package br.com.agencia.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/clientes"})
public class ClienteController {

    @Autowired
    private ClienteRepository repository;


    @CrossOrigin
    @GetMapping
    public List findAll(){
        return repository.findAll();
    }

    @CrossOrigin
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable Long id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @PostMapping
    public Cliente create(@RequestBody Cliente cliente){
        return repository.save(cliente);
    }

    @CrossOrigin
    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Cliente cliente) {
        return repository.findById(id)
                .map(record -> {
                    record.setNome(cliente.getNome());
                    record.setCpf(cliente.getCpf());
                    record.setCnpj(cliente.getCnpj());
                    record.setTelefone(cliente.getTelefone());
                    record.setEmail(cliente.getEmail());
                    record.setEndereco(cliente.getEndereco());
                    Cliente updated = repository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable Long id) {
        return repository.findById(id)
                .map(record -> {
                    repository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
