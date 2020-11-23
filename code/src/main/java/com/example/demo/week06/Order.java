package com.example.demo.week06;

import lombok.Data;


/**
 * 订单
 * @author lw
 */
@Data
public class Order {

    private Long id;
    private Long user_id;
    private String commodities;
    private int status;
    private String deliver_status;
    private Long total_price;
    private Long create_time;
    private Long update_time;

    Order(long userId, String commodities, long totalPrice) {
        this.user_id = userId;
        this.commodities = commodities;
        this.status = 0;
        this.deliver_status = null;
        this.total_price = totalPrice;
        this.create_time = System.currentTimeMillis();
    }
}
