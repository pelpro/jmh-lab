package ru.sberbank.jmh.deserialisators;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.sberbank.jmh.dto.FullDTO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DtoDeserialisator {

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static byte[] data;

    static {
        try {
            data = Files.readAllBytes(Paths.get("src/main/resources/data/data.json"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void deserializationAvgTime() throws IOException {
        objectMapper.readValue(data, FullDTO.class);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void deserializationThroughput() throws IOException {
        objectMapper.readValue(data, FullDTO.class);
    }

    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    public void deserializationSampleTime() throws IOException {
        objectMapper.readValue(data, FullDTO.class);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(DtoDeserialisator.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(10)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
