package com.sales.data.analysis.control.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private int numberCustomers;
    private int numberSellers;
    private int biggestSaleId;
    private String worstSellerName;
}
