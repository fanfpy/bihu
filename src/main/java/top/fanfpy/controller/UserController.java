package top.fanfpy.controller;

import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Param;
import com.blade.mvc.annotation.Path;
import com.blade.mvc.annotation.PostRoute;
import com.blade.mvc.http.Request;
import com.blade.mvc.http.Response;
import top.fanfpy.core.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@Path("/login")
public class UserController {
    @PostRoute("/")
    public void login(@Param String username, @Param String passwd, Response response){
       String jwt = JwtUtil.createToken(username);

        Map<String,String> map = new HashMap<>();
        map.put("jwt",jwt);
        response.json(map);
    }

    @GetRoute("/jwt")
    public void getJwt(Request request,Response response){
        String jwt = request.header("jwt");
        String id = JwtUtil.verifyTokenAndGetUserId(jwt);

        System.out.println(id);
    }
}
