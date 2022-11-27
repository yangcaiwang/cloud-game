package com.ycw.openfeign.config;

import com.ycw.openfeign.entity.OkhttpProperties;
import com.ycw.openfeign.interceptor.OkHttpLogInterceptor;
import feign.okhttp.OkHttpClient;
import okhttp3.ConnectionPool;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 * @Classname FeignOkhttpConfig
 * @Description OkHttpFeign 配置类
 * </p>
 */
@Configuration
@ConditionalOnClass({OkHttpClient.class})
@ConditionalOnProperty({"feign.okhttp.enabled"})
public class FeignOkhttpConfig {
    @Bean
    public okhttp3.OkHttpClient okHttpClient(OkhttpProperties okhttpProperties) {
        return new okhttp3.OkHttpClient.Builder()
                //设置连接超时
                .connectTimeout(okhttpProperties.getConnectTimeout(), TimeUnit.MILLISECONDS)
                //设置读超时
                .readTimeout(okhttpProperties.getReadTimeout(), TimeUnit.MILLISECONDS)
                //是否自动重连
                .retryOnConnectionFailure(true)
                .connectionPool(new ConnectionPool())
                .addInterceptor(new OkHttpLogInterceptor())
                //构建OkHttpClient对象
                .build();
    }
}
