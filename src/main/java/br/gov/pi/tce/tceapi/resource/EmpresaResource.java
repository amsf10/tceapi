package br.gov.pi.tce.tceapi.resource;

import java.net.URI;
import java.text.MessageFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.pi.tce.tceapi.exception.NotFoundException;
import br.gov.pi.tce.tceapi.model.Empresa;
import br.gov.pi.tce.tceapi.service.EmpresaService;

@RestController
@RequestMapping("/empresas")
public class EmpresaResource {

	@Autowired
	private EmpresaService empresaService;
	
	@GetMapping
	public List<Empresa> pesquisar() {
		return empresaService.todos();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Empresa> buscarPeloId(@PathVariable Long id) {
		Empresa empresa = empresaService.buscarPeloId(id);
		if (empresa == null) 
			throw new NotFoundException(MessageFormat.format("Empresa {0} não encontrada!", id));
		
		return ResponseEntity.ok(empresa);
	}
	
	@PostMapping
	public ResponseEntity<Empresa> criar(@Valid @RequestBody Empresa empresa, HttpServletResponse response) {
		Empresa empresaPersistida = empresaService.adicionar(empresa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(empresaPersistida.getId()).toUri();
			response.setHeader("Location", uri.toASCIIString());

		return ResponseEntity.created(uri).body(empresaPersistida);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Empresa> atualizar(@PathVariable Long id, @Valid @RequestBody Empresa empresa) {
		Empresa empresaPersistida = empresaService.atualizar(id, empresa);
		if (empresaPersistida == null) 
			throw new NotFoundException(MessageFormat.format("Empresa {0} não encontrada!", id));
		
		return ResponseEntity.ok(empresaPersistida);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(code=HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		empresaService.deletarPeloId(id);
	}
	

}
