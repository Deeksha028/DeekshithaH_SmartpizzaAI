package com.wipro.smartpizza.service;
 
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.smartpizza.dto.CouponRequestDTO;
import com.wipro.smartpizza.dto.CouponResponseDTO;
import com.wipro.smartpizza.entity.Coupon;
import com.wipro.smartpizza.repository.CouponRepository;
 
@Service
public class CouponServiceImpl implements CouponService {
 
    @Autowired
    private CouponRepository couponRepository;
 
    @Override
    public CouponResponseDTO createCoupon(CouponRequestDTO dto) {
 
        Coupon coupon = Coupon.builder()
                .code(dto.getCode())
                .discount(dto.getDiscount())
                .active(dto.isActive())
                .build();
 
        Coupon saved = couponRepository.save(coupon);
 
        return map(saved);
    }
 
    @Override
    public List<CouponResponseDTO> getAllCoupons() {
 
        return couponRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
 
    @Override
    public CouponResponseDTO getCouponById(Long id) {
 
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
 
        return map(coupon);
    }
 
    @Override
    public CouponResponseDTO updateCoupon(Long id, CouponRequestDTO dto) {
 
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
 
        coupon.setCode(dto.getCode());
        coupon.setDiscount(dto.getDiscount());
        coupon.setActive(dto.isActive());
 
        Coupon updated = couponRepository.save(coupon);
 
        return map(updated);
    }
 
    @Override
    public void deleteCoupon(Long id) {
 
        couponRepository.deleteById(id);
    }
 
    @Override
    public CouponResponseDTO getCouponByCode(String code) {
 
        Coupon coupon = couponRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
 
        return map(coupon);
    }
 
    private CouponResponseDTO map(Coupon coupon) {
 
        return CouponResponseDTO.builder()
                .id(coupon.getId())
                .code(coupon.getCode())
                .discount(coupon.getDiscount())
                .active(coupon.isActive())
                .build();
    }
}