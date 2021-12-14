package ru.sberbank.jmh.serialisators;

import com.googlecode.protobuf.format.XmlFormat;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import ru.sberbank.jmh.deserialisators.ProtoDTODeserialisator;
import ru.sberbank.jmh.dto.ProtoDTO;

public class ProtoDTOSerializator {

    private final static ProtoDTO.Full dto = ProtoDTODeserialisator.full;

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void serializationAvgTime() {
        XmlFormat.printToString(dto);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void serializationThroughput() {
        XmlFormat.printToString(dto);
    }

    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    public void serializationSampleTime() {
        XmlFormat.printToString(dto);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ProtoDTOSerializator.class.getSimpleName())
                .warmupIterations(5)
                .measurementIterations(10)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
