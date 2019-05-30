package ga.feiyu.course.controller;


import ga.feiyu.course.service.ICourseService;
import ga.feiyu.course.service.dto.CourseDTO;
import ga.feiyu.thrift.dto.UserDTO;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "/course")
public class CourseController {


    @Reference
    ICourseService iCourseService;

    @RequestMapping(value = "/courseDTOList", method = RequestMethod.GET)
    @ResponseBody
    public List<CourseDTO> courseDTOList(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getAttribute("user");
        System.err.print(user);
        return iCourseService.courseList();
    }
}
