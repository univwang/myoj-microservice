package com.univwang.myojbackenduserservice.controller.inner;


import com.univwang.myojbackendmodel.model.entity.User;
import com.univwang.myojbackendserviceclient.service.UserFeignClient;
import com.univwang.myojbackenduserservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 内部调用用户服务
 */
@RestController
@RequestMapping("/inner")
public class UserInnerController implements UserFeignClient {

    @Resource
    private UserService userService;

    /**
     * 通过Id获取用户
     * @param userId
     * @return
     */
    @GetMapping("/get/id")
    @Override
    public User getById(@RequestParam("userId") long userId) {
        return userService.getById(userId);
    }


    /**
     * 根据 id 集合获取用户列表
     * @param idList
     * @return
     */

    @GetMapping("/get/ids")
    @Override
    public List<User> listByIds(@RequestParam("idList") Collection<Long> idList) {
        return userService.listByIds(idList);
    }
}
