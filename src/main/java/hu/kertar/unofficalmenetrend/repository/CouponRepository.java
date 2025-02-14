package hu.kertar.unofficalmenetrend.repository;

import hu.kertar.unofficalmenetrend.model.kupon.Kupon;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CouponRepository extends JpaRepository<Kupon, Long> {
}
