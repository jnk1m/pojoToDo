package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;

public class CsvDataLoadService implements DataLoadService {
   Scores scores = CsvScores.getInstance();
   Students students = CsvStudents.getInstance();

   @Override
   public void loadAndMerge() {

      scores.load();

      students.load();
      students.merge(scores.findAll());
   }
}
