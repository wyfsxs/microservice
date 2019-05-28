package ga.feiyu.course.filter;

import ga.feiyu.thrift.dto.UserDTO;
import ga.feiyu.user.client.LoginFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CourseFilter extends LoginFilter {


    @Override
    protected void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO) {
        request.setAttribute("user", userDTO);
    }
}
