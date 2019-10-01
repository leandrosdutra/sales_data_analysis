package com.sales.data.analysis.control.service;

import com.sales.data.analysis.control.util.ConstantUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ItemService {

    private final ConstantUtil constantUtil;

    public Double getItemSum(String line){

        return Collections.list(new StringTokenizer(line, constantUtil.BREAK_DELIMITER_ITEM))
                          .stream()
                          .map(token -> (String) token)
                          .collect(Collectors.toList()).stream()
                          .map(this::getItemValue).reduce(0.0, Double::sum);

    }

    private Double getItemValue(String line){

        String[] fields = Collections.list(new StringTokenizer(line, constantUtil.BREAK_DELIMITER_SALE_ITEM))
                                     .stream()
                                     .map(token -> ((String) token).replace("[", "").replace("]", ""))
                                     .toArray(String[]::new);

        return Integer.parseInt(fields[1]) * Double.parseDouble(fields[2]);
    }

}