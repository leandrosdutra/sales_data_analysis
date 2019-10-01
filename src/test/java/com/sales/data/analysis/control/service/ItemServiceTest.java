package com.sales.data.analysis.control.service;

import com.sales.data.analysis.control.util.ConstantUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ItemServiceTest {

    private ItemService itemService;

    @Before
    public void init() {
        itemService = new ItemService(new ConstantUtil());
    }

    @Test
    public void testGetItemWithSuccess() {

        String line = "[1-15-100,2-30-2.50,3-40-3.10,4-50-6.15]";
        Assert.assertEquals(2006.5, itemService.getItemSum(line), 0.0);

    }

}
