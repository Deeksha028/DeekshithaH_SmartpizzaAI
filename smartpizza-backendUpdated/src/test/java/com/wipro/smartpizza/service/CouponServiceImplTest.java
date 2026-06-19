package com.wipro.smartpizza.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.wipro.smartpizza.dto.CouponRequestDTO;
import com.wipro.smartpizza.dto.CouponResponseDTO;
import com.wipro.smartpizza.entity.Coupon;
import com.wipro.smartpizza.repository.CouponRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CouponServiceImplTest {

    @Mock
    private CouponRepository couponRepository;

    @InjectMocks
    private CouponServiceImpl couponService;

    private Coupon coupon;
    private CouponRequestDTO dto;

    @BeforeEach
    void setup() {
        coupon = Coupon.builder()
                .id(1L)
                .code("SAVE10")
                .discount(10.0)
                .active(true)
                .build();

        dto = new CouponRequestDTO();
        dto.setCode("SAVE10");
        dto.setDiscount(10.0);
        dto.setActive(true);
    }

    // ✅ 1. CREATE COUPON
    @Test
    void testCreateCoupon() {

        when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);

        CouponResponseDTO result = couponService.createCoupon(dto);

        assertNotNull(result);
        assertEquals("SAVE10", result.getCode());
        assertEquals(10.0, result.getDiscount());
        assertTrue(result.isActive());
    }

    // ✅ 2. GET ALL COUPONS
    @Test
    void testGetAllCoupons() {

        when(couponRepository.findAll()).thenReturn(List.of(coupon));

        List<CouponResponseDTO> result = couponService.getAllCoupons();

        assertEquals(1, result.size());
        assertEquals("SAVE10", result.get(0).getCode());
    }

    // ✅ 3. GET COUPON BY ID
    @Test
    void testGetCouponById() {

        when(couponRepository.findById(1L)).thenReturn(Optional.of(coupon));

        CouponResponseDTO result = couponService.getCouponById(1L);

        assertEquals("SAVE10", result.getCode());
    }

    // ✅ 4. GET COUPON BY ID (NOT FOUND)
    @Test
    void testGetCouponById_NotFound() {

        when(couponRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            couponService.getCouponById(1L);
        });

        assertEquals("Coupon not found", ex.getMessage());
    }

    // ✅ 5. UPDATE COUPON
    @Test
    void testUpdateCoupon() {

        when(couponRepository.findById(1L)).thenReturn(Optional.of(coupon));
        when(couponRepository.save(any(Coupon.class))).thenReturn(coupon);

        CouponResponseDTO result = couponService.updateCoupon(1L, dto);

        assertEquals("SAVE10", result.getCode());
        assertEquals(10.0, result.getDiscount());
    }

    // ✅ 6. UPDATE COUPON (NOT FOUND)
    @Test
    void testUpdateCoupon_NotFound() {

        when(couponRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            couponService.updateCoupon(1L, dto);
        });
    }

    // ✅ 7. DELETE COUPON
    @Test
    void testDeleteCoupon() {

        doNothing().when(couponRepository).deleteById(1L);

        couponService.deleteCoupon(1L);

        verify(couponRepository, times(1)).deleteById(1L);
    }

    // ✅ 8. GET COUPON BY CODE
    @Test
    void testGetCouponByCode() {

        when(couponRepository.findByCode("SAVE10"))
                .thenReturn(Optional.of(coupon));

        CouponResponseDTO result = couponService.getCouponByCode("SAVE10");

        assertEquals("SAVE10", result.getCode());
    }

    // ✅ 9. GET COUPON BY CODE (NOT FOUND)
    @Test
    void testGetCouponByCode_NotFound() {

        when(couponRepository.findByCode("INVALID"))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            couponService.getCouponByCode("INVALID");
        });
    }
}