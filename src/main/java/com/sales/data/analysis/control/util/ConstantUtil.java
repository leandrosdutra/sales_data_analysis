package com.sales.data.analysis.control.util;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Component
public class ConstantUtil {

    public final String SELLER_ID = "001";
    public final String CUSTOMER_ID = "002";
    public final String SALE_ID = "003";
    public final String BREAK_DELIMITER = "ç";
    public final String BREAK_DELIMITER_ITEM = ",";
    public final String BREAK_DELIMITER_SALE_ITEM = "-";
    public final Charset CHARSET = StandardCharsets.ISO_8859_1;
    public final MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

}
