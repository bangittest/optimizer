package com.bn.optimizer.controller;

import com.bn.optimizer.service.OrderAssignmentOptimizer;
import com.bn.optimizer.service.CriteriaManager;
import com.bn.optimizer.dto.DataDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderOptimizeController {

    private final OrderAssignmentOptimizer orderAssignmentOptimizer;
    private final CriteriaManager criteriaManager;

    public OrderOptimizeController(OrderAssignmentOptimizer orderAssignmentOptimizer, CriteriaManager criteriaManager) {
        this.orderAssignmentOptimizer = orderAssignmentOptimizer;
        this.criteriaManager = criteriaManager;
    }

    @PostMapping("api/receive-data")
    public  ResponseEntity<?> receiveData (@RequestBody DataDTO data) {
        criteriaManager.setCriteriaList(data.getCriteria());
        orderAssignmentOptimizer.optimize(data.getOrders(),data.getVehicles(),data.getDrivers(),data.getDriverVehicleTeam());

        return ResponseEntity.ok("Received");
    }
}
