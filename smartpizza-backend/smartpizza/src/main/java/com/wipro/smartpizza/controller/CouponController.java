package com.wipro.smartpizza.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.smartpizza.dto.CouponRequestDTO;
import com.wipro.smartpizza.dto.CouponResponseDTO;
import com.wipro.smartpizza.service.CouponService;
 
@RestController
@RequestMapping("/api/coupons")
public class CouponController {
 
    @Autowired
    private CouponService couponService;
 
    @PostMapping
    public CouponResponseDTO createCoupon(
            @RequestBody CouponRequestDTO dto) {
 
        return couponService.createCoupon(dto);
    }
 
    @GetMapping
    public List<CouponResponseDTO> getAllCoupons() {
 
        return couponService.getAllCoupons();
    }
 
    @GetMapping("/{id}")
    public CouponResponseDTO getCouponById(
            @PathVariable Long id) {
 
        return couponService.getCouponById(id);
    }
 
    @GetMapping("/code/{code}")
    public CouponResponseDTO getCouponByCode(
            @PathVariable String code) {
 
        return couponService.getCouponByCode(code);
    }
 
    @PutMapping("/{id}")
    public CouponResponseDTO updateCoupon(
            @PathVariable Long id,
            @RequestBody CouponRequestDTO dto) {
 
        return couponService.updateCoupon(id, dto);
    }
 
    @DeleteMapping("/{id}")
    public void deleteCoupon(
            @PathVariable Long id) {
 
        couponService.deleteCoupon(id);
        
    }
    
}