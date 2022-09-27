package com.junwoo.objects.chapter12.step01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LectureTest {

    Lecture givenLecture() {
        return new Lecture(
                70,
                "객체지향 프로그래밍",
                Arrays.asList(81, 95, 75, 50, 45));
    }

    Lecture givenGradeLecture() {
        return new GradeLecture(
                70,
                "스프링",
                Arrays.asList(81, 95, 75, 50, 45),
                Arrays.asList(new Grade("A", 100, 95),
                        new Grade("B", 94, 80),
                        new Grade("C", 79, 70),
                        new Grade("D", 69, 50),
                        new Grade("F", 49, 0)));
    }

    @Test
    @DisplayName("이수한 학생의 수와 낙제한 학생의 수를 표현하는 문자열을 리턴한다")
    void returns_format_string() {
        String evaluate = givenLecture().evaluate();
        assertEquals(evaluate, "Pass:3 Fail:2");
    }

    @Test
    @DisplayName("이수한 학생의 수와 낙제한 학생의 수 각 등급의 수를 표현하는 문자열을 리턴한다")
    void returns_grade_format_string() {
        String evaluate = givenGradeLecture().evaluate();
        assertEquals(evaluate, "Pass:3 Fail:2, A:1 B:1 C:1 D:1 F:1");
    }
}