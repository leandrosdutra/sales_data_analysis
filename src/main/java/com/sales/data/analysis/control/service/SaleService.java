package com.sales.data.analysis.control.service;

import com.sales.data.analysis.control.mapper.CustomSaleMapper;
import com.sales.data.analysis.control.model.Sale;
import com.sales.data.analysis.control.util.ConstantUtil;
import lombok.AllArgsConstructor;
import ma.glasnost.orika.BoundMapperFacade;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.StringTokenizer;

@Service
@AllArgsConstructor
public class SaleService {

    private final ItemService itemService;
    private final ConstantUtil constantUtil;

    public Sale getSale(String line){

        String[] fields = Collections.list(new StringTokenizer(line, constantUtil.BREAK_DELIMITER))
                                     .stream()
                                     .map(token -> (String) token)
                                     .toArray(String[]::new);

        constantUtil.MAPPER_FACTORY.classMap(String[].class, Sale.class).customize(new CustomSaleMapper()).register();
        BoundMapperFacade<String[], Sale> boundMapper = constantUtil.MAPPER_FACTORY.getMapperFacade(String[].class, Sale.class);

        Sale sale = boundMapper.map(fields);
        sale.setSumSale(itemService.getItemSum(fields[2]));

        return sale;

    }

}