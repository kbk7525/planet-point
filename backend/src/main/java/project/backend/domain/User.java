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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String uid;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String mobile;

    @Column
    private int seed;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<Payment> payments = new ArrayList<>();

    public void addPayment(Payment payment) {
        this.payments.add(payment);
        payment.setUser(this);
    }
}
