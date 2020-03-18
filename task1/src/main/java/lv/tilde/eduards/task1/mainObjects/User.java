package lv.tilde.eduards.task1.mainObjects;

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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column (unique = true)
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
