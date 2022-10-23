package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.mock;

class StudentServiceTest {

   private DefaultStudentService service = new DefaultStudentService();

   @Test
   void getPassedStudents() throws NoSuchFieldException, IllegalAccessException {
      Students students = mock(Students.class);
      Field field = service.getClass().getDeclaredField("studentRepository");
      field.setAccessible(true);
      field.set(service, students);
      char charNum = 65;

      List<Student> studentList = new ArrayList<>();
      for (int i = 0; i < 10; i++) {
         Score tempScore = new Score(i, 10 * i);
         Student tempStudent = new Student(i, String.valueOf((char) charNum));
         tempStudent.setScore(tempScore);
         studentList.add(tempStudent);
         charNum++;
      }
      //given

      Mockito.when(students.findAll()).thenReturn(studentList);

      Collection<Student> passedStudents = service.getPassedStudents();
      System.out.println(passedStudents.size());
      Assertions.assertThat(passedStudents).hasSize(4);

   }

   @Test
   void getStudentsOrderByScore() throws NoSuchFieldException, IllegalAccessException {
      //given
      Students students = mock(Students.class);
      Field field = service.getClass().getDeclaredField("studentRepository");
      field.setAccessible(true);
      field.set(service, students);
      char charNum = 65;

      List<Student> studentList = new ArrayList<>();
      List<Score> scoreList = new ArrayList<>();
      for (int i = 0; i < 10; i++) {
         Score tempScore = new Score(i, 10 * i);
         Student tempStudent = new Student(i, String.valueOf((char) charNum));
         tempStudent.setScore(tempScore);
         scoreList.add(tempScore);
         studentList.add(tempStudent);
      }

      Mockito.when(students.findAll()).thenReturn(studentList);
      Collection<Student> studentsOrderByScore = service.getStudentsOrderByScore();
      List<Score> scoreCollect = studentsOrderByScore.stream().map(x -> x.getScore()).collect(Collectors.toList());
      Collections.reverse(scoreList);

      Assertions.assertThat(scoreCollect).isEqualTo(scoreList);

   }
}