package com.javarush.stepanov;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.io.IOException;

@BenchmarkMode(Mode.All)
@Warmup(iterations = 4, time = 2)
@Measurement(iterations = 5, time = 2)
@Fork(value = 1)
public class BenchmarkDemo {

    public void benchmark() throws IOException {
        Main.main(new String[0]);
    }


    @Benchmark
    public void concat(Blackhole bh) {
        String text = "";
        for (int i = 0; i < 10000; i++) {
            text += "Hello, World!";
        }
        bh.consume(text);
    }


    @Benchmark
    public void stringBuilder(Blackhole bh) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            stringBuilder.append("Hello, World!");
        }
        bh.consume(stringBuilder);
    }

}
