package com.amazon.user.Controller;

import java.util.logging.Logger;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.user.api.UserApi;

@RestController
public class UserController {
    private static final Logger LOG = Logger.getLogger(UserController.class.getName());

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String requestMethodName(@RequestParam String param) {
        return new String();
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String postMethodName(@RequestBody UserApi entity) {
        LOG.info("User name : " + entity.getName());
        LOG.info("User email : " + entity.getEmail());
        return "user created";
    }

}
