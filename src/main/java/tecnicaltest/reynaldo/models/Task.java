package tecnicaltest.reynaldo.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "task")
public class Task extends Audit<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TASK_SEQ")
    @SequenceGenerator(sequenceName = "TASK_SEQ", initialValue = 1, allocationSize = 1, name = "TASK_SEQ")
    private Long tasId;

    @Column(nullable = false, unique = false)
    private String tasTitle;

    @Column(nullable = false, unique = false)
    private String tasDecription;

    @Column(nullable = false, unique = false)
    private Date tasBeginDate;

    @Column(nullable = false, unique = false)
    private Date tasEndDate;

    @Column(nullable = true, unique = false)
    private Integer tasHoursExpect;

    @Column(nullable = true, unique = false)
    private Integer tasHoursTook;

    @Column(nullable = false, unique = false)
    private Long tasAssignTo;

    @Column(nullable = false, unique = false)
    private Boolean tasStatus;

    @OneToOne()
    @JoinColumn(name = "tasAssignTo", referencedColumnName = "usuaId", insertable = false, updatable = false)
    private User executor;

}