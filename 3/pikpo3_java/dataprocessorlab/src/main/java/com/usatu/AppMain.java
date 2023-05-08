package com.usatu;
import com.usatu.processor.DataProcessorService;

/**
 *   Main-класс с функцией main - это точка входа для Java-приложения.
 */

public class AppMain {

    public static void main(String[] args) {
        // Без указания полного пути, программа будет читать файл из своей корневой папки
        DataProcessorService service = new DataProcessorService("seeds_dataset.csv");
        service.runService();
    }

}
