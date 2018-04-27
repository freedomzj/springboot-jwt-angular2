package com.tutorial.springboot.angular2.starter.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.tutorial.springboot.angular2.starter.model.Audience;
import com.tutorial.springboot.angular2.starter.model.User;
import com.tutorial.springboot.angular2.starter.service.UserService;
import com.tutorial.springboot.angular2.starter.util.JwtHelper;
import com.tutorial.springboot.angular2.starter.util.ResultModel;
import com.tutorial.springboot.angular2.starter.util.ResultStatus;

@RestController
@RequestMapping(value="/api", produces = "application/json; charset=UTF-8")
public class LoginController {

	@Autowired
	private Audience audience;

	@Autowired
	private UserService userService;

	@RequestMapping(value="login",method=RequestMethod.POST)
	public ResponseEntity<ResultModel> login(
			@RequestBody String str, HttpServletRequest request) {
		JSONObject json=JSONObject.parseObject(str);
		ResultModel resultModel = null;
		User user = new User();
		user.setUsername(json.getString("email"));

		User query_user = userService.findByUser(user);
		if (query_user == null) {
			resultModel = ResultModel.error(ResultStatus.USER_001_ERROR);
			return new ResponseEntity<>(resultModel, HttpStatus.OK);
		}
		// 验证密码
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		boolean is_password = encoder.matches(json.getString("password"), query_user.getPassword());
		if (!is_password) {
			// 密码错误，返回提示
			resultModel = ResultModel.error(ResultStatus.USER_002_ERROR);
			return new ResponseEntity<>(resultModel, HttpStatus.OK);
		}

		String jwtToken = JwtHelper.createJWT(query_user.getUsername(), query_user.getId().toString(),
				query_user.getRole().toString(), audience.getClientId(), audience.getName(),
				audience.getExpiresSecond() * 1000, audience.getBase64Secret());

		String result_str = "bearer;" + jwtToken;
		resultModel = ResultModel.ok(result_str);

		return new ResponseEntity<>(resultModel, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="test",method=RequestMethod.POST)
	public ResponseEntity<ResultModel>  test(@RequestBody String str){
		JSONObject json=JSONObject.parseObject(str);
		ResultModel resultModel = null;
		User user = new User();
		user.setUsername(json.getString("email"));
		user.setName(json.getString("email"));
		User query_user = userService.findByUsername(user);
		if (query_user == null) {
			resultModel = ResultModel.error(ResultStatus.USER_001_ERROR);
			return new ResponseEntity<>(resultModel, HttpStatus.OK);
		}
		// 验证密码
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		boolean is_password = encoder.matches(json.getString("password"), query_user.getPassword());
		if (!is_password) {
			// 密码错误，返回提示
			resultModel = ResultModel.error(ResultStatus.USER_002_ERROR);
			return new ResponseEntity<>(resultModel, HttpStatus.OK);
		}

		String jwtToken = JwtHelper.createJWT(query_user.getUsername(), query_user.getId().toString(),
				query_user.getRole().toString(), audience.getClientId(), audience.getName(),
				audience.getExpiresSecond() * 1000, audience.getBase64Secret());

		String result_str = "bearer;" + jwtToken;
		resultModel = ResultModel.ok(result_str);

		return new ResponseEntity<>(resultModel, HttpStatus.OK);
		
	}

}
