package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class StudentsTest {

   @Test
   void load() throws NoSuchFieldException, IllegalAccessException {
      Students students = CsvStudents.getInstance();
      students.load();
      Field field = CsvStudents.class.getDeclaredField("studentList");
      field.setAccessible(true);
      List<Student> studentList = (List<Student>) field.get(students);
      Assertions.assertThat(studentList).isNotEmpty();
   }

   @Test
   void findAll() throws NoSuchFieldException, IllegalAccessException {
      List<Student> studentList = new ArrayList<>();
      char charNum = 65;
      for (int i = 0; i < 10; i++){
         Student tempStudent = new Student(i,String.valueOf((char)charNum));
         studentList.add(tempStudent);
         charNum++;
      }

      Students students = CsvStudents.getInstance();
      Field field = CsvStudents.class.getDeclaredField("studentList");

      field.set(students,studentList);

      Collection<Student> all = students.findAll();
      Assertions.assertThat(all).isEqualTo(studentList);
   }

   @Test
   void merge() throws NoSuchFieldException, IllegalAccessException {
      List<Student> studentList = new ArrayList<>();
      char charNum = 65;
      for (int i = 0; i < 10; i++){
         Student tempStudent = new Student(i,String.valueOf((char)charNum));
         studentList.add(tempStudent);
         charNum++;
      }
      List<Score> scoreList = new ArrayList<>();
      for (int i = 0; i < 10; i++) {
         Score tempScore = new Score(i, 10 * i);
         scoreList.add(tempScore);
      }

      Students students = CsvStudents.getInstance();
      Field field = CsvStudents.class.getDeclaredField("studentList");
      field.setAccessible(true);
      field.set(students,studentList);

      students.merge(scoreList);

      List<Student> finalStudentList = (List<Student>)field.get(students);
      for (Student student : finalStudentList) {
         Assertions.assertThat(student.getScore().getStudentSeq()).isEqualTo(student.getSeq());
         //getScore().getStudentSeq() ????????? ?????? ????????? ??? ??????????????? np??? ??? (???????????????????????? ???????????????.. _
         //????????? ??????????????????.. ?????????????????? ???????????? ?????????.. ???????????? ?????? ???????????? ??????????????? ??????
         //?????? ???????????? ?????? ????????? ?????? ??????

      }



   }
}