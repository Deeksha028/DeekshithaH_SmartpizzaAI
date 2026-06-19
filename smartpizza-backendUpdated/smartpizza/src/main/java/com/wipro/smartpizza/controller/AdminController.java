package com.wipro.smartpizza.controller;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.smartpizza.dto.AdminAnalyticsDTO;
import com.wipro.smartpizza.dto.CustomerTrendDTO;
import com.wipro.smartpizza.dto.DeliveryPerformanceDTO;
import com.wipro.smartpizza.dto.OrderHeatmapDTO;
import com.wipro.smartpizza.service.AdminService;
 
@RestController
@RequestMapping("/api/admin")
public class AdminController {
 
    @Autowired
    private AdminService service;
 
    @GetMapping("/analytics")
    public AdminAnalyticsDTO getAnalytics() {
        return service.getAnalytics();
    }
    
    @GetMapping("/customer-trends")
    public CustomerTrendDTO customerTrends() {
        return service.getCustomerTrends();
    }
    
    @GetMapping("/delivery-performance")
    public DeliveryPerformanceDTO getDeliveryPerformance() {
        return service.getDeliveryPerformance();
    }
    
    @GetMapping("/order-heatmap")
    public List<OrderHeatmapDTO> orderHeatmap() {
        return service.getOrderHeatmap();
    }
}