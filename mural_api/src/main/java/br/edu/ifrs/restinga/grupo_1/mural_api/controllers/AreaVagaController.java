package br.edu.ifrs.restinga.grupo_1.mural_api.controllers;

import br.edu.ifrs.restinga.grupo_1.mural_api.models.AreaDaVaga;
import br.edu.ifrs.restinga.grupo_1.mural_api.services.AreaVagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/areas")
public class AreaVagaController {

    @Autowired
    private AreaVagaService areaVagaService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<AreaDaVaga>> all() {
        return ResponseEntity.ok().body(this.areaVagaService.buscarTodas());
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<AreaDaVaga> show(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.areaVagaService.buscarPorId(id));

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> create(@RequestBody @Valid AreaDaVaga area) {
        AreaDaVaga obj = this.areaVagaService.cadastrarAreaDaVaga(area);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody @Valid AreaDaVaga area, @PathVariable Long id) {
        this.areaVagaService.editarAreaDaVaga(area, id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> destroy(@PathVariable Long id) {
        this.areaVagaService.excluirAreaDaVaga(id);
        return ResponseEntity.noContent().build();
    }
}
