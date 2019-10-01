package com.sales.data.analysis.control.mapper;

import com.sales.data.analysis.control.model.Sale;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class CustomSaleMapper extends CustomMapper<String[], Sale> {

    @Override
    public void mapAtoB(String[] a, Sale b, MappingContext context) {
        b.setSaleId(Integer.parseInt(a[1]));
        b.setSeller(a[3]);
    }

}

