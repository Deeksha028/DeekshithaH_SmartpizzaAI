package com.wipro.smartpizza.service;
 
import java.util.List;

import com.wipro.smartpizza.dto.AdminAnalyticsDTO;
import com.wipro.smartpizza.dto.CustomerTrendDTO;
import com.wipro.smartpizza.dto.DeliveryPerformanceDTO;
import com.wipro.smartpizza.dto.OrderHeatmapDTO;
 
public interface AdminService {
 
    AdminAnalyticsDTO getAnalytics();
    
    CustomerTrendDTO getCustomerTrends();
    
    DeliveryPerformanceDTO getDeliveryPerformance();
    
    List<OrderHeatmapDTO> getOrderHeatmap();
}
