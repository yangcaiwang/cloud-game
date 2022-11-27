package com.ycw.operatelog.feignservice.impl;

import com.ycw.common.page.Page;
import com.ycw.common.page.PageParam;
import com.ycw.operatelog.domain.Log;
import com.ycw.operatelog.feignservice.OperateLogFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * <p>
 *
 * @Classname
 * @Description </p>
 */
public class OperateLogFeignServiceImpl implements OperateLogFeignService {
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 分页查询日志
     */
    @Override
    public Page<Log> selectLogList(PageParam pageParam, Long userId) {
        Integer pageNum = pageParam.getPageNum();
        Integer pageSize = pageParam.getPageSize();
        // 页码和每页查询条数
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Query query = new Query(Criteria.where("userId").is(userId));
        // 查询记录总数
        long totalCount = mongoTemplate.count(query, Log.class);
        //查询分页后的记录
        List<Log> result = mongoTemplate.find(query.with(pageable), Log.class);
        // 计算总页数
        long totalPage = (totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1);
        Page<Log> page = new Page<>();
        page.setTotalCount(totalCount)
                .setTotalPage(totalPage)
                .setList(result);
        return page;
    }
}
