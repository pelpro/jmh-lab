package ru.sberbank.jmh.dto;

import java.util.ArrayList;
import java.util.Arrays;

public class FullDTO {
    public String fullName = "fullnameofthedto";
    public Integer fullSize = 120;
    public ArrayList<Double> fullList = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0));
    public PartDTO part = new PartDTO();
}
