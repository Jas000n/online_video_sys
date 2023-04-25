package com.Jason.recservice.utils;

import com.Jason.recservice.entity.Collection;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class WriteCSV {

    public static void writeCSV2(List<Collection> dataList, String finalPath) {
        try {
            Writer writer = new FileWriter(finalPath);
            writer.write(new String(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF }));

            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .withEscapechar('\\').build();
            beanToCsv.write(dataList);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
        System.out.println(finalPath + "数据导出成功");
    }
}

