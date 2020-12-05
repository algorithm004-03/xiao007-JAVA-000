package week0802.week0802.mappers;

import org.springframework.stereotype.Repository;
import week0802.week0802.models.Order;

import java.util.List;
import java.util.Map;

/**
 * @author lw
 */
@Repository
public interface OrderMapper {

    void insertOne(Order order);
    void insertMany(List<Order> orders);
    void delete(Long id);
    void update(Order order);
    List<Map<String, Object>> query(Map<String, Object> condition);
}
