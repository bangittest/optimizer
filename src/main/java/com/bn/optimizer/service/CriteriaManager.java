package com.bn.optimizer.service;


import com.bn.optimizer.dto.CriteriaDTO;
import com.bn.optimizer.dto.DriverDTO;
import com.bn.optimizer.dto.OrderDTO;
import com.bn.optimizer.dto.VehicleDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CriteriaManager {
    private List<CriteriaDTO> criteriaList;

    public void setCriteriaList(List<CriteriaDTO> criteriaList) {
        this.criteriaList = criteriaList;
    }

    public double calculateTotalScore(OrderDTO order, VehicleDTO vehicle, DriverDTO driver) {
        double totalScore = 0.0;
        for (CriteriaDTO criteria : criteriaList) {
            double score = criteria.calculate(order, vehicle, driver);
            totalScore += score * criteria.getWeight();
        }
        return totalScore;
    }

    public List<CriteriaDTO> getCriteriaList() {
        return new ArrayList<>(criteriaList);
    }
}

