package br.com.agencia.campanha;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/campanhas"})
public class CampanhaController {

    @Autowired
    private CampanhaRepository repository;


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
    public Campanha create(@RequestBody Campanha campanha){
        return repository.save(campanha);
    }

    @CrossOrigin
    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Campanha campanha) {
        return repository.findById(id)
                .map(record -> {
                    record.setCliente(campanha.getCliente());
                    record.setTitulo(campanha.getTitulo());
                    record.setObjetivo(campanha.getObjetivo());
                    record.setPublicoAlvo(campanha.getPublicoAlvo());
                    record.setDataInicio(campanha.getDataInicio());
                    record.setDataFim(campanha.getDataFim());
                    record.setOrcamento(campanha.getOrcamento());
                    record.setStatus(campanha.getStatus());
                    Campanha updated = repository.save(record);
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

