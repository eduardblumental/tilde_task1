package lv.tilde.eduards.task1.mainObjects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lv.tilde.eduards.task1.Enums.TransactionType;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private LocalDateTime dateTime;

    @Column
    private Long amount;

    @Column
    private TransactionType type;

    @Column
    private Long senderId;

    @Column
    private Long receiverId;



}
