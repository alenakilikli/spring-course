package com.example.service.impl;

import com.example.entity.OnlineCourse;
import com.example.entity.Students;
import com.example.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    private static Integer currentId;
    private Map< Integer, OnlineCourse > courseMap = new HashMap<> ( );

    @Override
    public OnlineCourse createCourse(OnlineCourse course) {
        course.setId( course.getId ());
        course.setName ( course.getName ( ) );
        course.setCreateOn (LocalDateTime.now());
        course.setUpdateOn(LocalDateTime.now());
        courseMap.put(course.getId(), course);

        return course;
    }

    @Override
    public List< OnlineCourse > showAllCourses() {
        return courseMap.values ( ).stream ( ).toList ( );
    }

    @Override
    public OnlineCourse findCourseById(Integer id) {
        return courseMap.get ( id );
    }

    @Override
    public OnlineCourse updateCourseInfoById(Integer id) {
        OnlineCourse onlineCourse = courseMap.get ( id );
        if ( onlineCourse == null ) {

            throw new ResponseStatusException ( HttpStatus.NO_CONTENT );
        }
        onlineCourse.setName ( onlineCourse.getName ( ) );
        onlineCourse.setStudentsList ( onlineCourse.getStudentsList ( ) );
        onlineCourse.setUpdateOn ( LocalDateTime.now ( ) );
        onlineCourse.setClosed ( onlineCourse.isClosed ( ) );
        return onlineCourse;

    }

    @Override
    public void deleteCourseById(Integer id) {
        courseMap.remove ( id );
    }

    @Override
    public List< Students > addStudent(String id, Students st) {
        List< Students > list = showAllStudents ( id );
        if ( list.contains ( st ) ) {
            throw new ResponseStatusException ( HttpStatus.IM_USED );
        } else {
            list.add ( st );
        }
        return list;
    }

    public List< Students > showAllStudents(String id) {
        return courseMap.get ( id ).getStudentsList ();
    }

    @Override
    public void removeStudentsByName(String name, String id) {
        List< Students > list = showAllStudents ( id );

        if ( list.contains ( Students.class.getName ( ).equals ( name ) ) ) {
            list.remove ( Students.class.getName ( ).equals ( name ) );
        } else {
            throw new ResponseStatusException ( HttpStatus.NOT_FOUND );
        }
    }

    @Override
    public void changeCourseStatus(String id) {
        OnlineCourse onlineCourse = courseMap.get ( id );
        if ( onlineCourse.isClosed ( ) == true ) {
            onlineCourse.setClosed ( false );
        } else {
            onlineCourse.setClosed ( true );
        }
    }
}
