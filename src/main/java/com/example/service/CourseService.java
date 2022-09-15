package com.example.service;

import com.example.entity.OnlineCourse;
import com.example.entity.Students;

import java.util.List;

public interface CourseService {

    OnlineCourse createCourse(OnlineCourse onlineCourse);

    List<OnlineCourse> showAllCourses();

    OnlineCourse findCourseById(Integer id);

    OnlineCourse updateCourseInfoById(Integer id);

    void deleteCourseById(Integer id);

    List< Students > addStudent(String id, Students st);

    void removeStudentsByName(String name, String id);

    void changeCourseStatus(String id);
}


