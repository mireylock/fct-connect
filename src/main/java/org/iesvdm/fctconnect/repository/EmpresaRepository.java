package org.iesvdm.fctconnect.repository;

import org.iesvdm.fctconnect.domain.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
