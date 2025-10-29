package com.pgc.exguides.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;

@RestController
@RequiredArgsConstructor
public class DBConnTest {

    //    1.SLF4J 로거 선언
    private static final Logger log = LoggerFactory.getLogger(DBConnTest.class);

    //    2. DataSource를 생성자 주입을 받음
    private final DataSource dataSource;

//    3. dbconn Get 요청 처리
    @GetMapping("/dbconn")
    public String dbconn() {
//        4. try-with-resources 구문으로 Connection 자동 close 보장
        try (Connection conn = dataSource.getConnection()) {
//        5. 연결 성공 시 DB URL 가져오기
            String result = conn.getMetaData().getURL();
//            6. SLF4J 파라미터 방식으로 로그 출력
                    log.info("DB 연결 성공 : {}", result);
                    return "DB연결 성공 : " + result;
        } catch (Exception e) {
            log.error("DB 연결 실패", e);
            return "DB연결실패";
        }
    }
}
