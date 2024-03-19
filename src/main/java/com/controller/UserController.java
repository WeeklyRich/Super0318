package com.controller;


import com.pojo.User;
import com.service.IPermissionService;
import com.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.until.JWTUtil;
import com.until.JsonResponseResult;
import com.until.RedisUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhou
 * @since 2024-03-18
 */
@RestController
@RequestMapping("/user")
@CrossOrigin (originPatterns = {"*"})
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/login")
    public JsonResponseResult login( User user){

        //1.封装账号密码到UsernamepasswordToken中.

        //2.使用subject.log(  token  );

        //3.成功(生成jwt进行分发)或失败(返回异常):

        //如何实现登录: token封装账号和密码.
        UsernamePasswordToken token = new UsernamePasswordToken( user.getUserName() , user.getUserPassword() );

        //开始登录:
        JsonResponseResult result = null;
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.login( token );
            //如果登录成功则不产生异常. 登录失败:产生异常.

            //以往: 登录成功时, session不在继续存储身份.
            //签发token时, 采用加密后的密码:
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("username" , user.getUserName());
            User u1 = this.userService.getOne(wrapper);


            String jwtToken = JWTUtil.sign(user.getUserName(), u1.getUserPassword());
            result = new JsonResponseResult( 200 , "登录成功" , jwtToken );


        }catch( UnknownAccountException e){
            result = new JsonResponseResult( 500 , "账号不存在" , null );
        }catch( IncorrectCredentialsException e){
            result = new JsonResponseResult( 500 , "密码错误" , null );
        }catch( AuthenticationException e){
            result = new JsonResponseResult( 500 , "登录失败" , null );
        }

        return result;
    }


    /*退出登录*/
    @RequestMapping("/logout")
    public JsonResponseResult logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new JsonResponseResult(200, "退出成功", null);
    }


    //用户分页查询:
    @RequestMapping("/query_by_page")
    public JsonResponseResult query( @RequestParam ("page_no") int curPage , @RequestParam("page_size")int pageSize , @RequestParam(defaultValue = "") String uname){
        //List<User> userList = this.userService.queryByPage( curPage,pageSize,uname );
        QueryWrapper qw = new QueryWrapper();
        qw.like("username" , uname);

        Page<User> page = new Page<>();
        page.setSize( pageSize );
        page.setCurrent( curPage );

        Page<User> page1 = this.userService.page(page , qw);

        JsonResponseResult jsonResponse= new JsonResponseResult(200 , "查询成功" , page1.getRecords());
        return jsonResponse;
    }


    //用户页码总数:
    @RequestMapping("/queryCounts")
    public JsonResponseResult queryCounts(@RequestParam(defaultValue = "") String uname){

        //int counts = this.userService.queryCounts( uname );

        QueryWrapper qw = new QueryWrapper();
        qw.like("username" , uname);
        long count = this.userService.count(qw);

        JsonResponseResult jsonResponse= new JsonResponseResult(200 , "查询成功" , count);
        return jsonResponse;
    }




    @Autowired
    private RedisUtils redisUtils;
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {

        String key = "user_" + id;
        User user = (User) redisUtils.getValue(key);
        if (user == null) {
            user = userService.getUserById(id);
            redisUtils.cacheValue(key, user);
        }
        return user;
    }

}

