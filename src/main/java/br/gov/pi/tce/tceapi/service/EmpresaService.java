package br.gov.pi.tce.tceapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.pi.tce.tceapi.model.Empresa;
import br.gov.pi.tce.tceapi.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	public List<Empresa> todos() {
		return empresaRepository.findAll();
	}
	
	public Empresa adicionar(Empresa empresa) {
		Empresa empresaPersistida = empresaRepository.save(empresa);
		return empresaPersistida;
	}

	public Empresa atualizar(Long id, Empresa empresa) {
		Empresa empresaPersistida = buscarPeloId(id);
		if (empresaPersistida != null) {
			BeanUtils.copyProperties(empresa, empresaPersistida, "id");
			empresaRepository.save(empresaPersistida);
		}
		return empresaPersistida;
	}
	
	public Empresa buscarPeloId(Long id) {
		Optional<Empresa> empresaOptional = empresaRepository.findById(id);
		return empresaOptional.orElse(null);
	}
	
	public void deletarPeloId(Long id) {
		empresaRepository.deleteById(id);
	}
}
