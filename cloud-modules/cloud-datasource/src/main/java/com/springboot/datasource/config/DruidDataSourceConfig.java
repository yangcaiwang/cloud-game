package com.springboot.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.springboot.common.enums.DataSourcesType;
import com.springboot.common.context.DynamicDataSourceContextHolder;
import com.springboot.datasource.aspect.DynamicDataSourceAspect;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用druid连接池 DruidDataSource 实现多数据源和动态数据源
 */

@Configuration
@Import(DynamicDataSourceAspect.class)
public class DruidDataSourceConfig {
    /**
     * 获取数据源（依赖于 spring）
     * 定义一个类继承AbstractRoutingDataSource实现determineCurrentLookupKey方法，该方法可以实现数据库的动态切换
     */
    static class DynamicDataSource extends AbstractRoutingDataSource {
        private final Map<Object, Object> datasources;

        public DynamicDataSource() {
            datasources = new HashMap<>();
            //额外数据源配置 TargetDataSources
            super.setTargetDataSources(datasources);
        }

        public <T extends DataSource> void addDataSource(DataSourcesType key, T data) {
            datasources.put(key, data);
        }

        /**
         * 获取与数据源相关的key
         * 此key是Map<String,DataSource> resolvedDataSources 中与数据源绑定的key值
         * 在通过determineTargetDataSource获取目标数据源时使用
         */
        @Override
        protected Object determineCurrentLookupKey() {
            return DynamicDataSourceContextHolder.getDataSourceType();
        }
    }

    /**
     * 自定义数据源配置
     */
    @Component
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @Data
    static class DataSourceProperties {
        private int initialSize;
        private int minIdle;
        private int maxActive;
        private int maxWait;
        private int timeBetweenEvictionRunsMillis;
        private int minEvictableIdleTimeMillis;
        private int maxEvictableIdleTimeMillis;
        private String validationQuery;
        private boolean testWhileIdle;
        private boolean testOnBorrow;
        private boolean testOnReturn;
        private boolean poolPreparedStatements;
        private int maxPoolPreparedStatementPerConnectionSize;
        private String filters;
        private String connectionProperties;

        /**
         * DruidDataSource 基本配置
         */
        public DataSource setDataSource(DruidDataSource datasource) {
            /** 配置初始化大小、最小、最大 */
            datasource.setInitialSize(initialSize);
            datasource.setMinIdle(minIdle);
            datasource.setMaxActive(maxActive);
            /** 配置获取连接等待超时的时间 */
            datasource.setMaxWait(maxWait);
            /** 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 */
            datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            /** 配置一个连接在池中最小、最大生存的时间，单位是毫秒 */
            datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            datasource.setMaxEvictableIdleTimeMillis(maxEvictableIdleTimeMillis);
            /**
             * 用来检测连接是否有效的sql，要求是一个查询语句，常用select 'x'。
             * 如果validationQuery为null，testOnBorrow、testOnReturn、testWhileIdle都不会起作用。
             */
            datasource.setValidationQuery(validationQuery);
            /** 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，
             * 如果空闲时间大于 timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
             */
            datasource.setTestWhileIdle(testWhileIdle);
            /** 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 */
            datasource.setTestOnBorrow(testOnBorrow);
            /** 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 */
            datasource.setTestOnReturn(testOnReturn);
            datasource.setPoolPreparedStatements(poolPreparedStatements);
            datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
            try {
                datasource.setFilters(filters);
            } catch (SQLException e) {
                System.err.println("druid configuration initialization filter: " + e);
            }
            datasource.setConnectionProperties(connectionProperties);
            return datasource;
        }
    }

    /**
     * 主库
     */
    @Bean(name = "coreDataSource")
    @ConfigurationProperties("spring.datasource.druid.core")
    public DataSource coreDataSource(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.setDataSource(DruidDataSourceBuilder.create().build());
    }

    /**
     * 从库
     */
    @Bean(name = "logsDataSource")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.logs", name = "enable", havingValue = "true")
    //是否开启数据源开关---若不开启 默认适用默认数据源
    @ConfigurationProperties("spring.datasource.druid.logs")
    public DataSource logsDataSource(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.setDataSource(DruidDataSourceBuilder.create().build());
    }

    /**
     * 设置数据源
     *
     * @Bean 声明其为Bean实例;
     * @Primary 在同样的DataSource中，首先使用被标注的DataSource
     */
    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dynamicDataSource(@Qualifier("coreDataSource") DataSource coreDataSource, @Qualifier("logsDataSource") DataSource logsDataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.addDataSource(DataSourcesType.CORE, coreDataSource);
        dynamicDataSource.addDataSource(DataSourcesType.LOGS, logsDataSource);
        //默认数据源配置 DefaultTargetDataSource
        dynamicDataSource.setDefaultTargetDataSource(coreDataSource);
        dynamicDataSource.afterPropertiesSet();
        return dynamicDataSource;
    }

    @Bean // 将数据源纳入spring事物管理
    public DataSourceTransactionManager transactionManager(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}

