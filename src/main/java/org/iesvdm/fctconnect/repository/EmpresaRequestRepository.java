package org.iesvdm.fctconnect.repository;

import org.iesvdm.fctconnect.domain.EmpresaRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRequestRepository  extends JpaRepository<EmpresaRequest, Long> {
}
