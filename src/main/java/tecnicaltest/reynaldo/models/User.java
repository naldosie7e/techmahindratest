package tecnicaltest.reynaldo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

@Data
@Entity
@Table(name = "usser", uniqueConstraints = @UniqueConstraint(columnNames = { "usuaName" }))
public class User extends Audit<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(sequenceName = "user_seq", initialValue = 1, allocationSize = 1, name = "USER_SEQ")
    private Long usuaId;

    @Column(nullable = false, unique = true)
    private String usuaName;

    @Column(nullable = true, unique = true)
    private String usuaPassword;

    @Column(nullable = false, unique = false)
    private String usuaFullName;

    @Column(nullable = false, unique = true)
    private String usuaMail;

    @Column(nullable = false, unique = false)
    private String usuaPhone;

    @Column(nullable = false, unique = true)
    private String usuaDocumentNumber;

    @Column(nullable = true, unique = false)
    private Long rolId;//

    @Column(nullable = false, unique = false)
    private boolean usuaStatus;

    @OneToOne()
    @JoinColumn(name = "rolId", insertable = false, updatable = false)
    private Rol role;

}