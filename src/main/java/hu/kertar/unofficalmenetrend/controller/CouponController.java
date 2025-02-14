package hu.kertar.unofficalmenetrend.controller;

import hu.kertar.unofficalmenetrend.model.User;
import hu.kertar.unofficalmenetrend.model.kupon.Kupon;
import hu.kertar.unofficalmenetrend.service.CouponService;
import hu.kertar.unofficalmenetrend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CouponController {

    private final CouponService couponService;

    @Autowired
    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/coupon")
    public String showRegistrationForm(Model model) {
        List<Kupon> coupons = couponService.getAllCoupons();
        model.addAttribute("coupons", coupons);
        return "coupon/coupon";
    }

    @PostMapping("/coupon")
    public String registerUser(@ModelAttribute("coupon") Kupon coupon, Model model) {
        try {
            couponService.addCoupon(coupon);
            return "redirect:/coupon";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "redirect:/coupon";
        }
    }

    @PostMapping("/coupon/delete")
    public String deleteCoupon(@RequestParam Long id) {
        couponService.deleteCoupon(id);
        return "redirect:/coupon";
    }
}
