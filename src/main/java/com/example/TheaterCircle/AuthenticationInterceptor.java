package com.example.TheaterCircle;

import com.example.TheaterCircle.controllers.AbstractController;
import com.example.TheaterCircle.models.User;
import com.example.TheaterCircle.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {


        // Authentication white list; add all publicly visible pages here
        List<String> nonAuthPages = Arrays.asList("/login", "/register");

        // Require sign-in for auth pages
        if (!nonAuthPages.contains(request.getRequestURI()) ) {

            Integer userId = (Integer) request.getSession().getAttribute(AbstractController.userSessionKey);

            if (userId != null) {
                Optional<User> user = userDao.findById(userId);

                if (user != null)
                    return true;

            }

            response.sendRedirect("/login");
            return false;


        }

        return true;

    }

}

