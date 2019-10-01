package com.sales.data.analysis.control.service;

import com.sales.data.analysis.control.model.Sale;
import com.sales.data.analysis.control.util.ConstantUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class SaleServiceTest {

    private SaleService saleService;

    @Before
    public void init() {
        ConstantUtil constantUtil = new ConstantUtil();
        saleService = new SaleService(new ItemService(constantUtil), constantUtil);
    }

    @Test
    public void testGetSaleWithSuccess() {

        String line = "003ç10ç[1-15-100,2-30-2.50,3-40-3.10,4-2-8]çJoao Silva";
        Sale sale = saleService.getSale(line);

        Assert.assertEquals(10, sale.getSaleId());
        Assert.assertEquals("Joao Silva", sale.getSeller());

    }

}
