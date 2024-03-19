package config;


import com.service.IUserService;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.until.JWTFilter;
import com.web.MyRealm;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ASUS
 */
@Configuration
@ConditionalOnWebApplication
public class ShiroConfiguration {

    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数
        credentialsMatcher.setHashIterations(3);

        return credentialsMatcher;
    }

    @Bean
    public MyRealm userRealm( @Qualifier("hashedCredentialsMatcher") HashedCredentialsMatcher hashedCredentialsMatcher){
        MyRealm userRealm = new MyRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
    }

    @Bean("securityManager")
    public DefaultWebSecurityManager securityManager(MyRealm myRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myRealm);

        // 关闭自带session
//        DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
//        evaluator.setSessionStorageEnabled(false);
//        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
//        subjectDAO.setSessionStorageEvaluator(evaluator);
//        securityManager.setSubjectDAO(subjectDAO);

        return securityManager;
    }


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean( DefaultWebSecurityManager securityManager, IUserService userService) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter (userService));
        factoryBean.setFilters(filterMap);
        factoryBean.setSecurityManager(securityManager);


        Map<String, String> filterRuleMap = new HashMap<>();
        filterRuleMap.put("/user/login", "anon");
        //依然保持匿名访问.

        //filterRuleMap.put("/**","authc"); authc的认证规则: 是从shiro维护的session中解析身份检查是否登录.
        filterRuleMap.put("/**", "jwt");
        //jwt自定义过滤器: 检查请求头中有token么, 有则验证token的正确性.

        //测试规则:
        //filterRuleMap.put("/**" , "anon");

        factoryBean.setFilterChainDefinitionMap(filterRuleMap);



        return factoryBean;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }


}
