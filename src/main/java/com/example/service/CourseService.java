package com.example.service;

import com.example.entity.OnlineCourse;
import com.example.entity.Student;

import java.util.Set;

public interface CourseService {

    OnlineCourse createCourse(OnlineCourse onlineCourse);

    OnlineCourse findCourseById(Integer courseId);

    OnlineCourse updateCourseInfoById(Integer courseId, OnlineCourse course);

    void deleteCourseById(Integer courseId);

    Set<Student> addStudent(Integer courseId, String student);

    void removeStudentsByName(String name, Integer courseId);

    void changeCourseStatus(Integer id);

    Set<OnlineCourse> findCourses(boolean showClosed);

    Set<Student> showAllStudents(Integer courseId);
}


