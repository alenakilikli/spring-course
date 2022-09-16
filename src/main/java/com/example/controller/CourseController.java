package com.example.controller;

import com.example.entity.OnlineCourse;
import com.example.entity.Student;
import com.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Set;


@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/courses")
    public OnlineCourse createCourse(@RequestBody OnlineCourse onlineCourse) {

        return courseService.createCourse(onlineCourse);
    }

    @GetMapping("/courses")
    public Collection<OnlineCourse> showCourses(
            @RequestParam(value = "showClosed", required = false) boolean showClosed // false
    ) {

        // course-1 closed
        // course-2 !closed
        // course-3 !closed

        return courseService.findCourses(showClosed);
    }

    @GetMapping("/courses/{id}")
    public OnlineCourse findById(@PathVariable("id") Integer id) {

        return courseService.findCourseById(id);
    }

    @PutMapping("/courses/{id}")
    public OnlineCourse updateById(@PathVariable("id") Integer id,
                                   @RequestBody OnlineCourse onlineCourse) {
        OnlineCourse course = courseService.updateCourseInfoById(id, onlineCourse);
        return course;
    }

    @DeleteMapping("courses/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        courseService.deleteCourseById(id);
    }

    @PutMapping("/courses/{id}/students/{name}")
    public Set<Student> addStudents(
            @PathVariable("id") Integer id,
            @PathVariable("name") String name) {

        return courseService.addStudent(id, name);

    }

    @GetMapping("/courses/{id}/students")
    public Set<Student> getAllStudents(@PathVariable("id") Integer id) {
        return courseService.showAllStudents(id);
    }

    @DeleteMapping("/courses/{id}/students/{name}")
    public void deleteStudents(
            @PathVariable("id") Integer courseId,
            @PathVariable("name") String name) {

        courseService.removeStudentsByName(name, courseId);

    }

    @PutMapping("/courses/{id}/toggle-course")
    public void changeStatusClosedOfCourse(@PathVariable("id") Integer id) {
        courseService.changeCourseStatus(id);
    }

}
