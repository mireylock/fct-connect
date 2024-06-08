package org.iesvdm.fctconnect.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="file")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_file")
    @EqualsAndHashCode.Include
    private long id;
    private String path;

}
