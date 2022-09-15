package com.example.controller;

import com.example.entity.OnlineCourse;
import com.example.entity.Students;
import com.example.service.impl.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@RestController
public class CourseController {

    @Autowired
    CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/courses")
    @ResponseBody
    public OnlineCourse createCourse(@RequestBody OnlineCourse onlineCourse) {

        return courseService.createCourse ( onlineCourse );
    }

    @GetMapping("/courses-show")
    public Collection< OnlineCourse > showCourses(
           // @RequestParam("show-closed") boolean isClosed
    ) {

        return courseService.showAllCourses ( );
    }

    @GetMapping("/courses/{id}")
    public OnlineCourse findById(@PathVariable("id") Integer id) {

        return courseService.findCourseById ( id );
    }

    @PutMapping("/courses/{id}")
    public OnlineCourse updateById(@PathVariable("id") Integer id) {
        OnlineCourse course = courseService.updateCourseInfoById ( id );
        return course;
    }

    @DeleteMapping("courses/{id}")
    public void deletById(@PathVariable("id") Integer id) {
        courseService.deleteCourseById ( id );
    }

    @PutMapping("/courses/{id}/students/{name}")
    public List< Students > addStudents(@PathVariable("id") String id
            , @PathVariable("name") Students name) {
        return courseService.addStudent ( id, name );

    }

    @GetMapping("/courses/{id}/students")
    public List< Students > getAllStudents(@PathVariable("id") String id) {
        return courseService.showAllStudents ( id );
    }

    @DeleteMapping("/courses/{id}/students/{name}")
    public void deleteStudents(@PathVariable("id") String id
            , @PathVariable("name") String name) {
        courseService.removeStudentsByName ( name, id );

    }

    @PutMapping("/courses/{id}/toggle-course")
    public void changeStatusClosedOfCourse(@PathVariable("id") String id) {
        courseService.changeCourseStatus ( id );
    }

}
