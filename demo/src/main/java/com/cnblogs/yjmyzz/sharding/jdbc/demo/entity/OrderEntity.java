package com.cnblogs.yjmyzz.sharding.jdbc.demo.entity;

import javax.persistence.*;

@Table(name = "`t_order_logic`")
public class OrderEntity {
    @Id
    @Column(name = "`order_id`")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "`order_name`")
    private String orderName;

    @Column(name = "`order_date`")
    private String orderDate;

    /**
     * @return order_id
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return order_name
     */
    public String getOrderName() {
        return orderName;
    }

    /**
     * @param orderName
     */
    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    /**
     * @return order_date
     */
    public String getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate
     */
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
}