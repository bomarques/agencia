package br.com.agencia.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/veiculos"})
public class VeiculoController {

    @Autowired
    private VeiculoRepository repository;


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
    public Veiculo create(@RequestBody Veiculo veiculo){
        return repository.save(veiculo);
    }

    @CrossOrigin
    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Veiculo veiculo) {
        return repository.findById(id)
                .map(record -> {
                    record.setCliente(veiculo.getCliente());
                    record.setNome(veiculo.getNome());
                    record.setDescricao(veiculo.getDescricao());
                    record.setInformacaoDeAcesso(veiculo.getInformacaoDeAcesso());

                    Veiculo updated = repository.save(record);
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


