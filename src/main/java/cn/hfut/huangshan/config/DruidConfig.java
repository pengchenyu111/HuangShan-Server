package cn.hfut.huangshan.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于配置druid数据源
 * @author PCY
 */
@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSouce(){
        return new DruidDataSource();
    }

    /**
     * 配置druid后台监控
     * @return ServletRegistrationBean<StatViewServlet>
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        //配置druid后台登陆管理
        HashMap<String,String> initParameters = new HashMap<>();
        //登陆账号、密码
        initParameters.put("loginUsername","admin");
        initParameters.put("loginPassword","123456");
        //允许谁可以访问，为空则默认谁都可以访问
        initParameters.put("allow","192.168.43.232");
        initParameters.put("allow","101.37.173.73");
        //禁止谁可以访问
        //initParameters.put("xiaoli","192.168.43.121");

        bean.setInitParameters(initParameters);
        return bean;
    }

    /**
     * 配置 Druid监控的web监控的filter
     * WebStatFilter：用于配置Web和Druid数据源之间的管理关联监控统计
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        //exclusions：设置哪些请求进行过滤排除掉，从而不进行统计
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);

        //"/*" 表示过滤所有请求
        //bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
