package com.wipro.smartpizza.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.wipro.smartpizza.dto.AdminAnalyticsDTO;
import com.wipro.smartpizza.dto.CustomerTrendDTO;
import com.wipro.smartpizza.dto.DeliveryPerformanceDTO;
import com.wipro.smartpizza.dto.OrderHeatmapDTO;
import com.wipro.smartpizza.entity.Cart;
import com.wipro.smartpizza.entity.Delivery;
import com.wipro.smartpizza.entity.PizzaOrder;
import com.wipro.smartpizza.entity.Role;
import com.wipro.smartpizza.entity.User;
import com.wipro.smartpizza.repository.CartRepository;
import com.wipro.smartpizza.repository.DeliveryRepository;
import com.wipro.smartpizza.repository.OrderRepository;
import com.wipro.smartpizza.repository.PaymentRepository;
import com.wipro.smartpizza.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdminServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private DeliveryRepository deliveryRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    // ✅ 1. TEST ANALYTICS
    @Test
    void testGetAnalytics() {

        when(userRepository.count()).thenReturn(10L);

        // ✅ FIXED Cart creation
        Cart c1 = new Cart();
        c1.setUserEmail("user1@gmail.com");

        Cart c2 = new Cart();
        c2.setUserEmail("user2@gmail.com");

        when(cartRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        when(deliveryRepository.countDelivered()).thenReturn(5L);
        when(deliveryRepository.countPending()).thenReturn(2L);

        when(paymentRepository.getTotalRevenue()).thenReturn(1000.0);

        when(orderRepository.findTopCustomer()).thenReturn("Rahul");

        when(orderRepository.countPlacedOrders()).thenReturn(8L);
        when(orderRepository.countCancelledOrders()).thenReturn(2L);
        when(orderRepository.countCouponUsage()).thenReturn(3L);

        when(orderRepository.getOrderCount()).thenReturn(10L);

        when(userRepository.countByRole(Role.CUSTOMER)).thenReturn(7L);
        when(userRepository.countByRole(Role.ADMIN)).thenReturn(2L);
        when(userRepository.countByRole(Role.DELIVERY)).thenReturn(1L);

        when(orderRepository.countTodayOrders()).thenReturn(2L);
        when(orderRepository.countWeeklyOrders()).thenReturn(5L);
        when(orderRepository.countMonthlyOrders()).thenReturn(10L);

        when(paymentRepository.getTodayRevenue()).thenReturn(200.0);
        when(paymentRepository.getRevenueAfter(any(LocalDateTime.class))).thenReturn(500.0);

        // ✅ Top pizza logic
        PizzaOrder o1 = new PizzaOrder();
        o1.setPizzaName("Pepperoni");

        PizzaOrder o2 = new PizzaOrder();
        o2.setPizzaName("Pepperoni");

        PizzaOrder o3 = new PizzaOrder();
        o3.setPizzaName("Margherita");

        when(orderRepository.findAll()).thenReturn(Arrays.asList(o1, o2, o3));

        when(orderRepository.count()).thenReturn(10L);
        when(paymentRepository.count()).thenReturn(8L);
        when(deliveryRepository.count()).thenReturn(7L);

        AdminAnalyticsDTO result = adminService.getAnalytics();

        assertNotNull(result);
        assertEquals(10L, result.getTotalUsers());
        assertEquals("Pepperoni", result.getTopPizza());
        assertEquals("Rahul", result.getTopCustomer());
        assertEquals(5L, result.getDeliveredOrders());
        assertEquals(1000.0, result.getTotalRevenue());
    }

    // ✅ 2. CUSTOMER TRENDS
    @Test
    void testGetCustomerTrends() {

        User user = new User();
        user.setName("Deekshitha");

        when(userRepository.count()).thenReturn(1L);
        when(orderRepository.count()).thenReturn(5L);
        when(userRepository.findAll()).thenReturn(List.of(user));

        CustomerTrendDTO result = adminService.getCustomerTrends();

        assertNotNull(result);
        assertEquals(1L, result.getTotalCustomers());
        assertEquals(5L, result.getTotalOrders());
        assertEquals("Deekshitha", result.getTopCustomer());
    }

    // ✅ 3. DELIVERY PERFORMANCE
    @Test
    void testGetDeliveryPerformance() {

        Delivery d1 = new Delivery();
        d1.setStatus("DELIVERED");
        d1.setEtaMinutes(30);

        Delivery d2 = new Delivery();
        d2.setStatus("PENDING");
        d2.setEtaMinutes(20);

        when(deliveryRepository.findAll()).thenReturn(List.of(d1, d2));

        DeliveryPerformanceDTO result = adminService.getDeliveryPerformance();

        assertNotNull(result);
        assertEquals(2L, result.getTotalDeliveries());
        assertEquals(1L, result.getCompletedDeliveries());
        assertEquals(25.0, result.getAverageEta());
    }

    // ✅ 4. ORDER HEATMAP
    @Test
    void testGetOrderHeatmap() {

        List<OrderHeatmapDTO> result = adminService.getOrderHeatmap();

        assertNotNull(result);
        assertEquals(4, result.size());

        // ✅ FIXED: use correct getter
        assertEquals("Bangalore", result.get(0).getLocation());
    }
}
