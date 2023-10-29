package project.backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String uid; //user의 고유번호

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String mobile; //핸드폰 번호

    @Column
    private int seed;

    //payment 엔티티와 1대다 관계
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<Payment> payments = new ArrayList<>();

    public void addPayment(Payment payment) {
        this.payments.add(payment);
        payment.setUser(this);
    }
}
