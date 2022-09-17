package com.example.service.impl;

import com.example.entity.OnlineCourse;
import com.example.entity.Student;
import com.example.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final static AtomicInteger currentId = new AtomicInteger();

    private final Map<Integer, OnlineCourse> courseMap = new HashMap<>();

    @Override
    public OnlineCourse createCourse(OnlineCourse course) {
        course.setId(currentId.incrementAndGet());

        course.setCreateOn(LocalDateTime.now());
        course.setUpdateOn(LocalDateTime.now());
        courseMap.put(course.getId(), course);

        return course;
    }

    @Override
    public Set<OnlineCourse> findCourses(boolean showClosed) {
        return courseMap.values()
                .stream()
                .filter(x -> {
                    if (showClosed) {
                        return true;
                    }

                    return !x.isClosed();
                })
                .collect(Collectors.toSet());
    }

    @Override
    public OnlineCourse findCourseById(Integer courseId) {
        OnlineCourse course = courseMap.get(courseId);
        if (course == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return course;
    }

    @Override
    public OnlineCourse updateCourseInfoById(Integer courseId, OnlineCourse course) {
        OnlineCourse onlineCourse = courseMap.get(courseId);

        if (onlineCourse == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        onlineCourse.setName(course.getName());
        onlineCourse.setStudents(course.getStudents());

        onlineCourse.setUpdateOn(LocalDateTime.now());

        return onlineCourse;

    }

    @Override
    public void deleteCourseById(Integer courseId) {
        courseMap.remove(courseId);
    }

    @Override
    public Set<Student> addStudent(Integer courseId, String student) {

        OnlineCourse course = courseMap.get(courseId);
        if (course == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (course.getStudents() == null) {
            course.setStudents(new HashSet<>());
        }

        course.getStudents().add(new Student(student));

        return course.getStudents();
    }

    public Set<Student> showAllStudents(Integer courseId) {
        return courseMap
                .get(courseId)
                .getStudents();
    }

    @Override
    public void removeStudentsByName(String name, Integer courseId) {
        OnlineCourse course = findCourseById(courseId);
        if (course.getStudents() == null) {
            return;
        }

        course
                .getStudents()
                .removeIf(student -> name.equals(student.getName()));
    }

    @Override
    public void changeCourseStatus(Integer id) {
        OnlineCourse onlineCourse = findCourseById(id);
        onlineCourse.setClosed(!onlineCourse.isClosed());
    }
}
