package com.springboot.common.base.controller;

import com.springboot.common.utils.DateUtils;
import com.springboot.common.response.AjaxR;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;


/**
 * web层通用数据处理
 *
 * @author ai-cloud
 */
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }


    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxR toAjax(int rows) {
        return rows > 0 ? AjaxR.success() : AjaxR.error();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxR toAjax(boolean result) {
        return result ? success() : error();
    }

    /**
     * 返回成功
     */
    public AjaxR success() {
        return AjaxR.success();
    }

    /**
     * 返回失败消息
     */
    public AjaxR error() {
        return AjaxR.error();
    }

    /**
     * 返回成功消息
     */
    public AjaxR success(String message) {
        return AjaxR.success(message);
    }

    /**
     * 返回失败消息
     */
    public AjaxR error(String message) {
        return AjaxR.error(message);
    }
}
