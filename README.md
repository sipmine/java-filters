# Утилита фильтрации содержимого файлов

* Версия Java: 17
* Система сборки Maven 3.6.3

Зависимотси
* commons cli 1.6.0

    [docs](https://commons.apache.org/proper/commons-cli/)
    
    [mvn repository](https://mvnrepository.com/artifact/commons-cli/commons-cli/1.6.0)

Запуск 

Сборка программы ```mvn clean compile assembly:single```

Запуск ```java -jar filtres-1.0-snapshot-jar-with-dependencies.jar [option] [input files]```

Пример файла in1.txt
```
Lorem ipsum dolor sit amet
45
Пример
3.1415
consectetur adipiscing
-0.001
тестовое задание
10050
```

Пример запуска ```java -jar filtres-1.0-snapshot-jar-with-dependencies.jar -f  in1.txt```


