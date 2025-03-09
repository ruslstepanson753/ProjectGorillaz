package com.javarush.lesson23;

import com.javarush.stepanov.config.NanoSpring;
import com.javarush.stepanov.dto.UserTo;
import com.javarush.stepanov.service.UserService;
import org.junit.jupiter.api.Test;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;

@BenchmarkMode(Mode.All)
@Warmup(iterations = 2, time = 1)
@Measurement(iterations = 2, time = 1)
@Fork(value = 1)
public class BenchmarkDemo {

    @Test
    public void benchmark() throws IOException {
        Main.main(new String[0]);
    }

    @Benchmark
    public void createUpdateFindUser(Blackhole bh) {
        UserService userService = NanoSpring.find(UserService.class);
        UserTo userTo = userService.createUser("Petr", "123");
        userService.addUserWin(userTo,"game-quest");
        bh.consume(userTo);
        UserTo userTo2 = userService.findUser("Ivanov");
        bh.consume(userTo2);
    }
}
