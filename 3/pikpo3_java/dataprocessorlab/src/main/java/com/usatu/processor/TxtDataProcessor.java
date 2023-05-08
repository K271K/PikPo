package com.usatu.processor;

import joinery.DataFrame;

import java.io.*;
import java.util.*;

/**
 * Реализация абстрактного класса DataProcessor для обработчика txt-файлов
 */

public class TxtDataProcessor extends DataProcessor {

    @Override
    public boolean read(){
        try {
            File datasetFile = new File(this.datasource);
            Scanner scan = null;
            InputStream dataStream = new FileInputStream(datasetFile);
            // Для перевода txt-файла с данными, разделенными символом табуляции, используем Scanner для построчного чтения
            scan = new Scanner(dataStream, "UTF-8");
            // Читаем первую страку как заголовок таблицы, содержащий названия колонок
            String header = scan.nextLine();
            // С помощью функции split() получаем названия колонок в виде массива строк
            String[] colNames = header.split("\t");
            // Если размер массива < 2, то возвращаем false
            if (colNames.length < 2) return false;
            // Создаем из данного массива DataFrame
            dataset = new DataFrame(header.split("\t"));
            // Построчно читаем txt-файл, и также с помощью функции split() каждый новый массив с данными добавляем в DataFrame
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                dataset.append(Arrays.asList(line.split("\t")));
            }
            return true;
        } catch (IOException e) {
            System.out.println("Datasource error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void run() {
        result = sortByColName(dataset, "LKG", true);
    }

    @Override
    public void printResult() {
        System.out.println("TXT-file processor result:\n" + result);
    }

}

