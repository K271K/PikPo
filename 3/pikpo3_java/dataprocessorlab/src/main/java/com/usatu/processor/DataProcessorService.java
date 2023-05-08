package com.usatu.processor;

/**
 * В данном модуле реализуется класс с основной бизнес-логикой приложения.
 * Обычно такие модули / классы имеют в названии слово "Service".
 */

public class DataProcessorService {

    private final DataProcessorFactory procFactory;

    private final String datasource;

    public DataProcessorService(String datasource) {
        this.datasource = datasource;
        procFactory = new DataProcessorFactory();
    }

    /**
     * ВАЖНО! Обратите внимание, что метод runService использует только методы базового абстрактного класса DataProcessor
     *        и, таким образом, будет выполняться для любого типа обработчика данных (CSV или TXT), что позволяет в дальнейшем
     *        расширять приложение, просто добавляя другие классы обработчиков, которые, например, работают с базой данных или
     *        сетевым хранилищем файлов (например, FTP-сервером).
     */
    public void runService() {
        DataProcessor processor = procFactory.getProcessor(datasource);
        processor.run();
        processor.printResult();
    }

    public String getDatasource() {
        return datasource;
    }
}
