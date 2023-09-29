package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;
    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
        // @Valid exige os métodos de validação do Bean Validation
    }

    /*@GetMapping
    public Page<DadosListagemMedico> lista(@PageableDefault(size = 10, sort = {"nome"})Pageable paginacao)  {
        return repository.findAll(paginacao).map(DadosListagemMedico::new);

        // Convertendo o tipo de lista para o tipo DadosListagemmedico
        // Paginação
        // http://localhost:8080/medicos?size=1&page=1
        // Ordenação
        // http://localhost:8080/medicos?sort=nome,desc
    }*/

    @GetMapping
    public Page<DadosListagemMedico> listaAtivo(@PageableDefault(size = 10, sort = {"nome"})Pageable paginacao)  {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        //repository.deleteById(id);

        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}