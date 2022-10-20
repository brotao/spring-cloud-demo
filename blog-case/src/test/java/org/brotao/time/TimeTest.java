package org.brotao.time;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author luotao
 * @Date 2022-07-13 9:28
 * @Description
 */

public class TimeTest {

    @Test
    public void testToday() {
        LocalDateTime now = LocalDateTime.now();
        boolean equals = now.toLocalDate().equals(LocalDate.now());
    }
}
