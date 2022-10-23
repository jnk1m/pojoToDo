package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class GradeQueryServiceTest {
   private static DefaultGradeQueryService defaultGradeQueryService = new DefaultGradeQueryService();
   private static Students mockStudent = Mockito.mock(Students.class);
   private static Scores mockScore = Mockito.mock(Scores.class);


   @BeforeAll
   static void init() throws NoSuchFieldException, IllegalAccessException {
      Field students = defaultGradeQueryService.getClass().getDeclaredField("studentsRepository");
      Field scores = defaultGradeQueryService.getClass().getDeclaredField("scoresRepository");
      scores.setAccessible(true);
      students.setAccessible(true);
      students.set(defaultGradeQueryService, mockStudent);
      scores.set(defaultGradeQueryService, mockScore);
   }

   @Test
   void getScoreByStudentName() {
      List<Student> studentList = new ArrayList<>();
      char charNum = 65;
      for (int i = 0; i < 10; i++) {
         Score tempScore = new Score(i, 10 * i);
         Student tempStudent = new Student(i, String.valueOf(charNum));
         tempStudent.setScore(tempScore);
         studentList.add(tempStudent);
         charNum++;
      }

      Mockito.when(mockStudent.findAll()).thenReturn(studentList);
      List<Score> scoreByStudentName = defaultGradeQueryService.getScoreByStudentName(studentList.get(1).getName());
      List<Score> expectedScoreds = new ArrayList<>();

      for (int i = 0; i < studentList.size(); i++) {
         if (studentList.get(i).getName().equals(studentList.get(1).getName())) {
            expectedScoreds.add(studentList.get(i).getScore());
         }
      }
      Assertions.assertThat(scoreByStudentName).isEqualTo(expectedScoreds);
   }

   @Test
   void getScoreByStudentSeq() {
      List<Score> scoreList = new ArrayList<>();
      for (int i = 0; i < 10; i++) {
         Score tempScore = new Score(i, 10 * i);
         scoreList.add(tempScore);
      }
      Mockito.when(mockScore.findAll()).thenReturn(scoreList);
      Score scoreByStudentSeq = defaultGradeQueryService.getScoreByStudentSeq(scoreList.get(1).getStudentSeq());
      Assertions.assertThat(scoreByStudentSeq).isEqualTo(scoreList.get(1));
      //int score = scoreList.get(1).getScore();


   }
}