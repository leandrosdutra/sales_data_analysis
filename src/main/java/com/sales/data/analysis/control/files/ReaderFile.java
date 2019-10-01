package com.sales.data.analysis.control.files;

import com.sales.data.analysis.control.model.Result;
import com.sales.data.analysis.control.model.Sale;
import com.sales.data.analysis.control.service.SaleService;
import com.sales.data.analysis.control.util.ConstantUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class ReaderFile {

    private final SaleService saleService;
    private final ConstantUtil constantUtil;

    public Result read(Path path) throws IOException {

        List<String> lines = Files.lines(path, constantUtil.CHARSET)
                                  .collect(Collectors.toList());

        Stream<String> lin = Files.lines(path, constantUtil.CHARSET);

        List<Sale> saleList = lines.stream()
                                   .filter(line -> line.startsWith(constantUtil.SALE_ID))
                                   .map(saleService::getSale)
                                   .collect(Collectors.toList());

        return Result.builder().numberSellers((int) lines.stream()
                                                         .filter(line -> line.startsWith(constantUtil.SELLER_ID)).count())
                               .numberCustomers((int) lines.stream()
                                                           .filter(line -> line.startsWith(constantUtil.CUSTOMER_ID)).count())
                               .biggestSaleId(saleList.stream()
                                                      .max(Comparator.comparing(Sale::getSumSale)).get().getSaleId())
                               .worstSellerName(saleList.stream()
                                                        .min(Comparator.comparing(Sale::getSumSale)).get().getSeller()).build();

    }

}
