package com.nhnacademy.edu.springframework.project.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class ScoresTest {

   @Test
   void load() throws NoSuchFieldException, IllegalAccessException {
      Scores scores = CsvScores.getInstance(); //싱글턴이니까 객체를 가져옴.. 이렇게
      scores.load(); //로드를 해서 리스트에 값을 넣어주기
      Field field = CsvScores.class.getDeclaredField("scoreList");
      //scoreList 라는 이름의 필드를 가져오겠다. 리스트를 가져오는게 아니라 필드를 가져오는거임
      field.setAccessible(true); //필드 액세스를 true로 바꿔줌
      List<Score> scoreList = (List<Score>) field.get(scores); //scores라는 객체의 filed를 가져옴
      //get은 이거저거 다 가져올 수 있기 때문에 오브젝트 타입. 리스트
      Assertions.assertThat(scoreList).isNotEmpty();
   }


   @Test
   void findAll() throws NoSuchFieldException, IllegalAccessException {

      List<Score> scoreList = new ArrayList<>();
      for (int i = 0; i < 10; i++) {
         Score tempScore = new Score(i, 10 * i);
         scoreList.add(tempScore);
      }

      Scores scores = CsvScores.getInstance();
      Field field = CsvScores.class.getDeclaredField("scoreList");

      field.set(scores, scoreList); //앞에 있는게 타겟이고 뒤에 있는게 들어갈 값

      List<Score> all = scores.findAll();
      Assertions.assertThat(all).isEqualTo(scoreList); //값이 반환되는지 확실히 알 수 있음


   }
}