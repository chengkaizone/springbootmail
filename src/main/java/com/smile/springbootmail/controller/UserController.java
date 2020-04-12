package com.smile.springbootmail.controller;

import com.smile.springbootmail.model.User;
import com.smile.springbootmail.model.ValidUser;
import com.smile.springbootmail.model.ValidationGroup1;
import com.smile.springbootmail.model.ValidationGroup2;
import io.swagger.annotations.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(tags = "用户数据接口")
public class UserController {

    @PostMapping("/validuser")
    public List<String> addValidUser(@Validated(ValidationGroup2.class) ValidUser user, BindingResult result) {

        List<String> errors = new ArrayList<>();
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            for (ObjectError error: allErrors) {
                errors.add(error.getDefaultMessage());
            }
        }
        return errors;
    }

    @ApiOperation(value = "查询用户", notes = "根据ID查询用户")
    @ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true)
    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable Integer id) {
        System.out.println("查询用户：" + id);
        return "/user/" + id;
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功！"),
            @ApiResponse(code = 500, message = "删除失败！")
    })
    @ApiOperation(value = "删除用户", notes = "通过ID删除用户")
    @DeleteMapping("/user/{id}")
    public Integer deleteUserById(@PathVariable Integer id) {
        System.out.println("执行删除用户：" + id);
        return id;
    }

    @ApiOperation(value = "添加用户", notes = "添加一个用户,传入用户名和地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户名", required = true, defaultValue = "sang"),
            @ApiImplicitParam(paramType = "query", name = "address", value = "用户地址", required = true, defaultValue = "shenzhen")
    })
    @PostMapping("/user")
    public String addUser(@RequestParam String username, @RequestParam String address) {
        return username + ":" + address;
    }

    @ApiOperation(value = "修改用户", notes = "修改用户，传入用户信息")
    @PutMapping("/user")
    public String updateUser(@RequestBody User user) {
        return user.toString();
    }

    @GetMapping("/ignore")
    @ApiIgnore
    public void ignoreMethod() {
        System.out.println("ignore method!");
    }



}
