package com.sales.data.analysis.control.files;

import com.sales.data.analysis.control.model.Result;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class WriterFile {

    public void write(Result result, String pathfile) throws IOException {

        Path path = Paths.get(System.getProperty("user.home"), "data/out/" + pathfile);
        List<String> lines = new ArrayList<>();
        lines.add(String.format("%s: %s", "Number of Customers", result.getNumberCustomers()));
        lines.add(String.format("%s: %s", "Number of Sellers", result.getNumberSellers()));
        lines.add(String.format("%s: %s", "Biggest Sale Id", result.getBiggestSaleId()));
        lines.add(String.format("%s: %s", "Worst Seller Name", result.getWorstSellerName()));
        Files.write(path, lines);

    }

}
