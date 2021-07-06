package com.lee.tools.proxy.b.web.config;

import com.lee.tools.proxy.b.web.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: MvcConfiguration
 * @Description:
 * @author: libo
 * @date: 2021/7/7 0007 1:39
 * @Version: 1.0
 */
@Slf4j
@Configuration
public class MvcConfiguration {
    @Bean
    public HandlerExceptionResolver restHandleExceptionResolver() {
        return new HandlerExceptionResolver() {
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
                ModelAndView view = new ModelAndView(new MappingJackson2JsonView());
                view.addObject("code", 1);
                view.addObject("data", null);
                view.addObject("msg", ex.getMessage());
                log.error(ex.getMessage(), ex);
                return view;
            }
        };
    }
}