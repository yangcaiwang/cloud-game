package com.ycw.operatelog.feignservice;

import com.ycw.common.page.Page;
import com.ycw.common.page.PageParam;
import com.ycw.operatelog.domain.Log;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 *
 * @Classname OperateLogFeignService
 * @Description openfeign 内部调用客户端
 * </p>
 */
@Service
@FeignClient(name = "cloud-operatelog", path = "/log", configuration = com.ycw.openfeign.config.FeignOkhttpConfig.class)
public interface OperateLogFeignService {
    // 声明要调用的rest
    @GetMapping("/{id}")
    Page<Log> selectLogList(@PathVariable PageParam pageParam, Long userId);
}
