package com.poshist.sys.config;

import com.poshist.sys.entity.User;
import com.poshist.sys.vo.UserVO;
import org.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationSuccesssHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
         httpServletResponse.setContentType("application/json; charset=UTF-8");
        String jsonHeader = httpServletRequest.getHeader("content-type");
        boolean isjson = "application/json".equals(jsonHeader);
        if (isjson) {
            JSONObject tokenJson=new JSONObject("{token:"+((UserVO)authentication.getPrincipal()).getUsername()+"}");
            JSONObject returnObj = new JSONObject();
            returnObj.put("code", "20000");

            returnObj.put("date",tokenJson);
            httpServletResponse.getWriter().print(returnObj.toString());
            httpServletResponse.getWriter().flush();
        } else {
            super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
        }
    }
}
