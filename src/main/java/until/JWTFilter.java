package until;

import Test.JsonResponseResult;
import Test.pojo.User;
import Test.service.IUserService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JWTFilter extends BasicHttpAuthenticationFilter {

    private IUserService userService;

    public JWTFilter(IUserService userService) {
        this.userService = userService;
    }

    // 登录标识
    private static String LOGIN_SIGN = "Authorization";

    /**
     * 检测用户是否登录
     * 检测header里面是否包含Authorization字段即可
     *
     * @param request
     * @param response
     * @return
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;

        String authorization = req.getHeader(LOGIN_SIGN);

        return authorization != null;

    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp =  (HttpServletResponse)response;

        String header = req.getHeader(LOGIN_SIGN);

        //检查token是否正确:
        if (isValidToken(header)) {
            return true;
        } else {
            //token错误.
            return false;
        }
    }

    private boolean isValidToken(String token) {
        // 在此处编写判断Token是否合法的逻辑
        String username = JWTUtil.getUsername(token);
        //根据username查询数据库:  建议通过service组件实现.
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username" , username );
        User u1 = this.userService.getOne(wrapper);

        //从数据库中找出的加密密码:
        if(u1 != null ){
            String password = u1.getUserPassword ();
            boolean result = JWTUtil.verify(token, username, password);

            return result;
            // 若为真表示Token有效，反之则无效
        }else{
            return false;
        }

    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletResponse resp = (HttpServletResponse)response;
        response.setContentType("application/json; charset=utf-8");
        //1.看请求头中是否具备token.
        if (isLoginAttempt(request, response)) {
                //2.检查token的正确性:
            try {
                return executeLogin(request, response);
            } catch (Exception e) {
                //检查身份失败:
                return false;
            }
        }else{
            return false;
        }


    }

    /**
     * 对跨域提供支持
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        HttpServletResponse resp = (HttpServletResponse)response;
        resp.setContentType("application/json; charset=utf-8");
        JsonResponseResult result = new JsonResponseResult(500 , "请先登录" , null);


        resp.setStatus( 500 );
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            String jsonStr = JSONObject.toJSONString(result);
            writer.write( jsonStr );

        } catch (IOException e) {
            e.printStackTrace();
        }
        return  false;
    }
}
