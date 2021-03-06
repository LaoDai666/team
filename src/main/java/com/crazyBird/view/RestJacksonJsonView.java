package com.crazyBird.view;

import java.util.Map;

import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

/**
 * @Type RestJacksonJsonView
 * @Desc json映身java对象�? 
 * @author luogm
 * @date 2016-08-03
 * @Version V1.0
 */
public class RestJacksonJsonView extends MappingJacksonJsonView {

    /**
     * 重写方法,去掉json的键值对,只保留�??
     */
    protected Object filterModel(Map<String, Object> model) {
        Object resultObject = null;
        Map<?, ?> result = (Map<?, ?>) super.filterModel(model);
        if (result.size() == 1) {
            resultObject = result.values().iterator().next();
        } else {
            resultObject = result;
        }
        return resultObject;
    }
}
