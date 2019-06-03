package ga.feiyu.course.service;
import com.alibaba.dubbo.config.annotation.Service;
import ga.feiyu.course.mapper.CourseMapper;
import ga.feiyu.course.service.dto.CourseDTO;
import ga.feiyu.thrift.dto.TeacherDTO;
import ga.feiyu.thrift.user.UserInfo;
import org.apache.thrift.TException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ServiceProvider serviceProvider;

    @Override
    public List<CourseDTO> courseList() {
        List<CourseDTO> courseDTOS = courseMapper.listCourse();
        if (courseDTOS != null) {
            for (CourseDTO courseDTO : courseDTOS) {
                Integer teacherID = courseMapper.getCourseTeacher(courseDTO.getId());
                if (teacherID != null) {
                    try {
                        UserInfo userInfo = serviceProvider.getUserService().getTeacherById(teacherID);
                        courseDTO.setTeacher(trans2Teacher(userInfo));
                    } catch (TException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return courseDTOS;
    }

    private TeacherDTO trans2Teacher(UserInfo userInfo) {
        TeacherDTO teacherDTO = new TeacherDTO();
        BeanUtils.copyProperties(userInfo, teacherDTO);
        return teacherDTO;
    }
}
