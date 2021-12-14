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
import ru.sberbank.jmh.dto.ProtoDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ProtoDTODeserialisator {
    private final static ProtoDTO.Full.Part part = ProtoDTO.Full.Part.newBuilder().
            setPartName("").
            setPartSize(12.5).
            addAllPartList(new ArrayList<>(Arrays.asList(1, 2, 3, 4))).
            build();

    public final static ProtoDTO.Full full = ProtoDTO.Full.newBuilder().
            setFullName("").
            setFullSize(120).
            addAllFullList(new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0))).
            setPart(part).
            build();

    private final static byte[] data = full.toByteArray();

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void deserializationAvgTime() throws IOException {
        ProtoDTO.Full.parseFrom(data);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void deserializationThroughput() throws IOException {
        ProtoDTO.Full.parseFrom(data);
    }

    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    public void deserializationSampleTime() throws IOException {
        ProtoDTO.Full.parseFrom(data);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ProtoDTODeserialisator.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(10)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
