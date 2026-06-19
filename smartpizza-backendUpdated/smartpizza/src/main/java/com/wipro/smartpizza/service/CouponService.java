package com.wipro.smartpizza.service;
 
import java.util.List;

import com.wipro.smartpizza.dto.CouponRequestDTO;
import com.wipro.smartpizza.dto.CouponResponseDTO;
 
public interface CouponService {
 
    CouponResponseDTO createCoupon(CouponRequestDTO dto);
 
    List<CouponResponseDTO> getAllCoupons();
 
    CouponResponseDTO getCouponById(Long id);
 
    CouponResponseDTO updateCoupon(Long id, CouponRequestDTO dto);
 
    void deleteCoupon(Long id);
 
    CouponResponseDTO getCouponByCode(String code);
}