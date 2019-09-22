package top.fanfpy.controller;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.*;
import com.blade.mvc.http.Response;
import top.fanfpy.core.ResultGenerator;
import top.fanfpy.dao.UserDao;

@Path("/user")
public class UserController {

    @Inject
    UserDao userDao;

    @PostRoute("/login")
    public void login(@Param String username, @Param String passwd, Response response){
        response.json(ResultGenerator.genSuccessResult(userDao.login(username,passwd)));
    }

    @GetRoute("/:uid")
    public void getUserInfo(@PathParam Long uid, Response response){
       response.json(ResultGenerator.genSuccessResult(userDao.findById(uid)));
    }
}
