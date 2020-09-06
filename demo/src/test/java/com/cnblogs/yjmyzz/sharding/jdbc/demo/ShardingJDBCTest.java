package com.cnblogs.yjmyzz.sharding.jdbc.demo;

import com.cnblogs.yjmyzz.sharding.jdbc.demo.entity.OrderEntity;
import com.cnblogs.yjmyzz.sharding.jdbc.demo.mapper.OrderEntityMapper;
//import com.cnblogs.yjmyzz.sharding.jdbc.demo.service.IdService;
import com.cnblogs.yjmyzz.sharding.jdbc.demo.service.IdService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingJDBCTest {

    static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private OrderEntityMapper orderEntityMapper;

    @Autowired
    private IdService idService;

    @Before
    public void initData() {
        orderEntityMapper.deleteAll();

        Date now = new Date();
        String dateStr = sdf.format(now);
        for (int i = 0; i < 20; i++) {
            OrderEntity entity = new OrderEntity();
            entity.setOrderId(idService.nextId());
            entity.setOrderName("order-" + i);
            entity.setOrderDate(dateStr);
            int rows = orderEntityMapper.insertSelective(entity);
            System.out.println(rows + "," + entity.getOrderId());
        }
    }

    @After
    public void delData() {
        orderEntityMapper.deleteAll();
    }

    @Test
    public void testPageList() {
        PageHelper.startPage(1, 5);
        List<OrderEntity> list = orderEntityMapper.selectAll();
        PageInfo pageInfo = new PageInfo(list);
        System.out.println("total:" + pageInfo.getTotal());
    }

    @Test
    public void testPageList2() {
        Date now = new Date();
        String dateStr = sdf.format(now);
        PageHelper.startPage(1, 5);
        List<OrderEntity> list = orderEntityMapper.selectList("_0", dateStr);
        PageInfo pageInfo = new PageInfo(list);
        System.out.println("total:" + pageInfo.getTotal());
    }

    @Test
    public void testPageList3() {
        Date now = new Date();
        String dateStr = sdf.format(now);
        PageHelper.startPage(1, 5);
        List<OrderEntity> list = orderEntityMapper.selectList2("_0", dateStr);
        PageInfo pageInfo = new PageInfo(list);
        System.out.println("total:" + pageInfo.getTotal());
        System.out.println(list.size());
    }

    @Test
    public void testPageList4() {
        Date now = new Date();
        String dateStr = sdf.format(now);
        PageHelper.startPage(1, 5);
        List<OrderEntity> list = orderEntityMapper.selectList2(null, dateStr);
        PageInfo pageInfo = new PageInfo(list);
        System.out.println("total:" + pageInfo.getTotal());
        System.out.println(list.size());
    }

}
