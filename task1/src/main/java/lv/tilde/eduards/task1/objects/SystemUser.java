package lv.tilde.eduards.task1.objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class SystemUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column
    private String username;

    @Column
    private Long grossDebtors;

    @Column
    private Long grossCreditors;

    @Column
    private Long netDebtors;

    @Column
    private Long netCreditors;

    @Column
    private Long balance;

}
