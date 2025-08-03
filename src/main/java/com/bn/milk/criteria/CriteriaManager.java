package com.bn.milk.criteria;

import com.bn.milk.model.Driver;
import com.bn.milk.model.Order;
import com.bn.milk.model.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CriteriaManager {
    private List<BaseCriteria> criteriaList;

    public CriteriaManager() {
        this.criteriaList = new ArrayList<>();
        initializeDefaultCriteria();
    }

    private void initializeDefaultCriteria() {
        // Khởi tạo các tiêu chí mặc định với trọng số như yêu cầu
        criteriaList.add(new DistanceCriteria(0.2));
        criteriaList.add(new ETDTimeCriteria(0.2));
        criteriaList.add(new DriverBalanceCriteria(0.2, new HashMap<>()));
        criteriaList.add(new OrderPrecedenceCriteria(0.1));
        criteriaList.add(new VehicleLoadCriteria(0.05));

        // Thêm tiêu chí cho việc tách/gộp đơn
        criteriaList.add(new SubOrderGroupingCriteria(0.15));
        criteriaList.add(new VehicleUtilizationCriteria(0.1, new HashMap<>()));
    }

    public void addCriteria(BaseCriteria criteria) {
        criteriaList.add(criteria);
    }

    public void removeCriteria(String criteriaName) {
        criteriaList.removeIf(c -> c.getName().equals(criteriaName));
    }

    public void updateCriteriaWeight(String criteriaName, double newWeight) {
        for (BaseCriteria criteria : criteriaList) {
            if (criteria.getName().equals(criteriaName)) {
                criteria.setWeight(newWeight);
                break;
            }
        }
    }

    public double calculateTotalScore(Order order, Vehicle vehicle, Driver driver) {
        double totalScore = 0.0;
        for (BaseCriteria criteria : criteriaList) {
            double score = criteria.calculate(order, vehicle, driver);
            totalScore += score * criteria.getWeight();
        }
        return totalScore;
    }

    public List<BaseCriteria> getCriteriaList() {
        return new ArrayList<>(criteriaList);
    }
}
