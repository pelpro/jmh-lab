package ru.sberbank.jmh.dto;

import java.util.ArrayList;
import java.util.Arrays;

public class PartDTO {
    public String partName = "partNameq";
    public Double partSize = 12.5;
    public ArrayList<Integer> partList = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
}
