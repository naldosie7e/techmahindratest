package tecnicaltest.reynaldo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Rol")
public class Rol extends Audit<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ROL_SEQ")
    @SequenceGenerator(sequenceName = "ROL_SEQ", initialValue = 1, allocationSize = 1, name = "ROL_SEQ")
    private Long rolId;

    @Column(nullable = false, unique = true)
    private String rolName;

    private String rolDescripcion;

    @Column(nullable = false, unique = false)
    private Boolean rolStatus;

}