package cn.tedu.miaosha.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.miaosha.domain.User;
import cn.tedu.miaosha.redis.RedisService;
import cn.tedu.miaosha.redis.UserKey;
import cn.tedu.miaosha.result.CodeMsg;
import cn.tedu.miaosha.result.Result;
import cn.tedu.miaosha.service.UserService;

@Controller
@RequestMapping("/demo")
public class DemoController {
	@RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }
				//1.rest api json输出2.页面
	@Autowired
	UserService userService;
	
	@Autowired
	RedisService redisService;
	
	
	@RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello() {
		return Result.success("hello wsy");
        //return new Result(0,"success","hello,wsy");
    }
	@RequestMapping("/helloError")
    @ResponseBody
    public Result<String> helloError() {
		return Result.error(CodeMsg.SERVER_ERROR);
//		 return new Result(500102,"xxx");
//		 return new Result(500101,"xxx");
//        return new Result(500100,"session失败");
    }
	@RequestMapping("/thymeleaf")
    public String  thymeleaf(Model model) {
 		model.addAttribute("name", "within");
 		return "hello";
    }
	@RequestMapping("/db/get")
    @ResponseBody
    public Result<User> doGet() {
		User user = userService.getById(1);
		return Result.success(user);
	}
	
	@RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> doTx() {
		userService.tx();
		return Result.success(true);
	}
	 @RequestMapping("/redis/get")
	    @ResponseBody
	    public Result<User> redisGet() {
	    	User  user  = redisService.get(UserKey.getById, ""+1, User.class);
	        return Result.success(user);
	    }
	    
	    @RequestMapping("/redis/set")
	    @ResponseBody
	    public Result<Boolean> redisSet() {
	    	User user  = new User();
	    	user.setId(3);
	    	user.setName("cvcx");
	    	redisService.set(UserKey.getById, ""+1, user);//UserKey:id1
	        return Result.success(true);
	    }
	    
    


}
