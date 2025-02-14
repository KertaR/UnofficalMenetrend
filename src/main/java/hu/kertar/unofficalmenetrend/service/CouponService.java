package hu.kertar.unofficalmenetrend.service;

import hu.kertar.unofficalmenetrend.model.User;
import hu.kertar.unofficalmenetrend.model.kupon.Kupon;
import hu.kertar.unofficalmenetrend.repository.CouponRepository;
import hu.kertar.unofficalmenetrend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService {

    private final CouponRepository couponRepository;

    @Autowired
    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public List<Kupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    public void addCoupon(Kupon coupon) {
        couponRepository.save(coupon);
    }

    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }
}
