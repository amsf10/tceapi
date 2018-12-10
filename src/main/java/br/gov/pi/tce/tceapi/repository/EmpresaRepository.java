package br.gov.pi.tce.tceapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.pi.tce.tceapi.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
