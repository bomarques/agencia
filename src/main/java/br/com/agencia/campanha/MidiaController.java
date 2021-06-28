package br.com.agencia.campanha;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/midias"})
public class MidiaController {

    @Autowired
    private MidiaRepository repository;


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
    public Midia create(@RequestBody Midia midia){
        return repository.save(midia);
    }

    @CrossOrigin
    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Midia midia) {
        return repository.findById(id)
                .map(record -> {
                    record.setCampanha(midia.getCampanha());
                    record.setTipoDeMidia(midia.getTipoDeMidia());
                    record.setPeriodo(midia.getPeriodo());
                    record.setStatus(midia.getStatus());
                    record.setValorDedicado(midia.getValorDedicado());
                    record.setEngajamentoEstimado(midia.getEngajamentoEstimado());
                    record.setEngajamentoAlcancado(midia.getEngajamentoAlcancado());
                    record.setAlcance(midia.getAlcance());
                    record.setVeiculo(midia.getVeiculo());
                    Midia updated = repository.save(record);
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