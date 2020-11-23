package com.example.demo.week06;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author lw
 */
@Repository
public interface OrderMapper {

    void insert(Order order);
    void delete(Long id);
    void update(Order order);
    List<Order> query(Map<String, Object> condition);
}
