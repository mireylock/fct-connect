package org.iesvdm.fctconnect.repository;

import org.iesvdm.fctconnect.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File,Long> {
}
