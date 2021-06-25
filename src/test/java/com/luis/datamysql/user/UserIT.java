package com.luis.datamysql.user;

import com.luis.datamysql.schema.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIT {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    private static RestTemplate restTemplate = null;

    @BeforeAll
    public static void init(){
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp(){
        baseUrl = baseUrl.concat(":").concat(port+"/").concat("user/");
    }

    @Test
    @Sql(statements = "INSERT INTO user (id, name, email) VALUES (1, 'luismiguel', 'miguelprime@gmail.com')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(statements = "DELETE FROM user", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void returnAPerson(){
        User user = restTemplate.getForObject(baseUrl.concat("{id}"), User.class, 1);

        assertEquals(user.getName(), "luismiguel");
    }
}
