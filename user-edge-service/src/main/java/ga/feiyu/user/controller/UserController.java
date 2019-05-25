package ga.feiyu.user.controller;


import ga.feiyu.thrift.user.UserInfo;
import ga.feiyu.user.Redis.RedisClient;
import ga.feiyu.user.dto.UserDTO;
import ga.feiyu.user.response.LoginResponse;
import ga.feiyu.user.response.Response;
import ga.feiyu.user.thrift.ServiceProvider;
import org.apache.thrift.TException;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.Random;

@RestController
public class UserController {

    @Autowired
    ServiceProvider serviceProvider;
    @Autowired
    RedisClient redisClient;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Response login(@RequestParam("username") String username,

                          @RequestParam("password") String password) {
        //1.验证用户密码
        UserInfo userInfo = null;
        try {
            userInfo = serviceProvider.getUserService().getUserByName(username);
        } catch (TException e) {
            e.printStackTrace();
            return Response.USERNAME_PASSWORD_INVALID;
        }
        if (userInfo == null) {
            return Response.USERNAME_PASSWORD_INVALID;
        }
        if (!userInfo.getPassword().equalsIgnoreCase(md5(password))) {
            return Response.USERNAME_PASSWORD_INVALID;
        }
        //2.生成token
        String token = genTocken();

        //3.缓存用户
        redisClient.set(token, toDTO(userInfo), 3600);

        return new LoginResponse(token);
    }

    private Object toDTO(UserInfo userInfo) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDTO, userInfo);
        return userDTO;
    }

    private String genTocken() {

        return randomCode("0123456789abcdefghijklmnopqrstuvwxyz", 32);
    }

    private String randomCode(String s, int size) {

        StringBuffer result = new StringBuffer(size);

        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int loc = random.nextInt(s.length());
            result.append(s.charAt(loc));
        }

        return result.toString();
    }

    private String md5(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] md5Byte = md5.digest(password.getBytes("utf-8"));
            return HexUtils.toHexString(md5Byte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
