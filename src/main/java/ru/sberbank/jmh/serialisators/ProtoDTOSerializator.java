package ru.sberbank.jmh.serialisators;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.sberbank.jmh.dto.FullDTO;

public class ProtoDTOSerializator {

    private final static FullDTO fullDTO = new FullDTO();
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void serializationAvgTime() throws JsonProcessingException {
        objectMapper.writeValueAsString(fullDTO);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void serializationThroughput() throws JsonProcessingException {
        objectMapper.writeValueAsString(fullDTO);
    }

    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    public void serializationSampleTime() throws JsonProcessingException {
        objectMapper.writeValueAsString(fullDTO);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(DtoSerializator.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(10)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
