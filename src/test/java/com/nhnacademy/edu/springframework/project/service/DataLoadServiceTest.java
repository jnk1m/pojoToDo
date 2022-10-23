package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

class DataLoadServiceTest {
    private static CsvDataLoadService dataLoadService = new CsvDataLoadService();
    //얘가 진짜로 어떻게 동작하는지 알고 싶으니까 실제 객체를 만들어줌
    private static Scores mockScore = Mockito.mock(Scores.class);
    private static Students mockStudent = Mockito.mock(Students.class);
    //모킹할 대상의 클래스 타입을 넣어주면 아무것도 안하는 멍텅구리 객체가 생성이 됨.

    @BeforeAll
    //beforeEach는 매번 초기화가 필요할 때
    static void init() throws NoSuchFieldException, IllegalAccessException {
        Field scores = dataLoadService.getClass().getDeclaredField("scores");
        Field students = dataLoadService.getClass().getDeclaredField("students");
        scores.setAccessible(true);
        students.setAccessible(true);
        scores.set(dataLoadService,mockScore); //앞에 있는게 타겟이고 뒤에 있는게 들어갈 값
        students.set(dataLoadService,mockStudent);
        //dataLoadService라는 객체의 클래스의 student에 mockStudent를 강제 주입
    }

    @Test
    void loadAndMerge() {
        //given
        List<Score> scoreList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Score tempScore = new Score(i, 10 * i);
            scoreList.add(tempScore);
        }

        Mockito.when(mockScore.findAll()).thenReturn(scoreList);

        //when
        dataLoadService.loadAndMerge();

        //then
        InOrder inOrder = Mockito.inOrder(mockScore, mockStudent);

        inOrder.verify(mockScore, Mockito.times(1)).load();
        inOrder.verify(mockStudent, Mockito.times(1)).load();
        inOrder.verify(mockStudent, Mockito.times(1)).merge(scoreList);



    }
}