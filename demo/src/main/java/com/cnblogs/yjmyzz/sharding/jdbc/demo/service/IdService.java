package com.cnblogs.yjmyzz.sharding.jdbc.demo.service;

import com.cnblogs.yjmyzz.sharding.jdbc.demo.utils.NetworkUtils;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;

@Service("idService")
public class IdService implements InitializingBean {
    private AtomicInteger serverIndex = new AtomicInteger(0);

    private static final SnowflakeShardingKeyGenerator keyGenerator = new SnowflakeShardingKeyGenerator();

    public long nextId() {
        return (Long) keyGenerator.generateKey();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        BigInteger ipAddrBigInt = NetworkUtils.ipAddressToBigInt(NetworkUtils.getLocalHostAddress());
        //假设服务器最多512台
        BigInteger base = new BigInteger("512");
        serverIndex.set(ipAddrBigInt.mod(base).intValue());
        synchronized (this) {
            Properties prop = keyGenerator.getProperties();
            prop.setProperty("worker.id", serverIndex.toString());
            keyGenerator.setProperties(prop);
        }
    }
}
