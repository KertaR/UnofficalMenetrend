package hu.kertar.unofficalmenetrend.model.kupon;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "coupon")
public class Kupon {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "coupon_sequence")
    @SequenceGenerator(name = "coupon_sequence", sequenceName = "coupon_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int percent;

    @Column(nullable = false)
    private String code;
}
