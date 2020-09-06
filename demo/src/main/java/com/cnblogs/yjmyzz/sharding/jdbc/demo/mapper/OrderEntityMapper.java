package com.cnblogs.yjmyzz.sharding.jdbc.demo.mapper;

import com.cnblogs.yjmyzz.sharding.jdbc.demo.entity.OrderEntity;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderEntityMapper extends Mapper<OrderEntity> {

    Long deleteAll();

    List<OrderEntity> selectList(@Param("tableIndex") String tableIndex, @Param("orderDate") String orderDate);

    List<OrderEntity> selectList2(@Param("tableIndex") String tableIndex, @Param("orderDate") String orderDate);

    /**
     * 手动指定pagehelper的COUNT语句
     * @param tableIndex
     * @param orderDate
     * @return
     */
    Long selectList2_COUNT(@Param("tableIndex") String tableIndex, @Param("orderDate") String orderDate);

}