package com.nhnacademy.edu.springframework.project.repository;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvScores implements Scores {
   private CsvScores() {
   }

   private static CsvScores instance = new CsvScores();

   List<Score> scoreList = new ArrayList<>();

   /**
    * TODO 2 :
    * Java Singleton 패턴으로 getInstance() 를 구현하세요.
    **/
   public static Scores getInstance() {
      return instance;
   }

   // TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
   @Override
   public void load() {

      String[] line;
      String path = "src/main/resources/data/score.csv";

      try (CSVReader csvReader = new CSVReader(new FileReader(path));) {
         while ((line = csvReader.readNext()) != null) {
            scoreList.add(new Score(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
         }
      } catch (CsvException e) {
         throw new RuntimeException(e);
      } catch (FileNotFoundException e) {
         throw new RuntimeException(e);
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
   }

   @Override
   public List<Score> findAll() {
      return scoreList;
   }
}
