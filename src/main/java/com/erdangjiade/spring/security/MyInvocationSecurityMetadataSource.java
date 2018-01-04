package com.erdangjiade.spring.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.erdangjiade.spring.security.tool.AntUrlPathMatcher;
import com.erdangjiade.spring.security.tool.UrlMatcher;

public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private final UrlMatcher urlMatcher = new AntUrlPathMatcher();
    // key:url value:角色
    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    // tomcat启动时实例化一次
    public MyInvocationSecurityMetadataSource() {
        loadResourceDefine();
    }

    // tomcat开启时加载一次，加载所有url和权限（或角色）的对应关系
    private void loadResourceDefine() {
        resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
        final Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
        final ConfigAttribute ca = new SecurityConfig("ROLE_USER");
        atts.add(ca);
        resourceMap.put("/accessDenied.jsp", atts);
        final Collection<ConfigAttribute> attsno = new ArrayList<ConfigAttribute>();
        final ConfigAttribute cano = new SecurityConfig("ROLE_NO");
        attsno.add(cano);
        resourceMap.put("/hello.jsp", attsno);
        resourceMap.put("/role_no.jsp", attsno);
        final Collection<ConfigAttribute> attsno1 = new ArrayList<ConfigAttribute>();
        final ConfigAttribute cano1 = new SecurityConfig("ROLE_ADMIN");
        attsno1.add(cano1);
        resourceMap.put("/admin", attsno1);

    }

    // 参数是要访问的url，返回这个url对于的所有权限（或角色）
    @Override
    public Collection<ConfigAttribute> getAttributes(final Object object) throws IllegalArgumentException {
        // 将参数转为url
        final String url = ((FilterInvocation) object).getRequestUrl();
        final Iterator<String> ite = resourceMap.keySet().iterator();
        while (ite.hasNext()) {
            final String resURL = ite.next();
            if (urlMatcher.pathMatchesUrl(resURL, url)) {
                return resourceMap.get(resURL);
            }
        }
        return null;
    }

    @Override
    public boolean supports(final Class<?> clazz) {
        return true;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }
}
