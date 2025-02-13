## Тестовое задание на курс Java Шифт
Утилита фильтрации содержимого файлов

### Параметры запуска
- По умолчанию файлы результатов перезаписываются. С помощью опции -a можно задать режим добавления в существующие файлы.
- Опции -s и -f выводят краткую или полную статистику по записанным файлам соответственно.
- С помощью опции -o можно задать путь для сохранения файлов результатов.
- Опция -p задает префикс имен выходных файлов (например при вводе -p result- выходные файлы будут называться result-string.txt и т.д)

### Инструкция по запуску
### Файл для запуска утилиты находится в папке проекта [out/artifacts/TestTask_jar](https://github.com/IlyaSteshenko/TestTask/tree/main/out/artifacts/TestTask_jar)

- Скопируйте файл TestTask.jar из репозитория в любое удобное место
- Откройте командную строку в этой директории и напишите данную команду с необходимыми вам параметрами и файлами
  
  Пример команды

  ```
  java -jar TestTask.jar -s -p sample- in1.txt in2.txt
  ```

### Особенности реализации
Обработка всех возможных видов ошибок.

Многопоточное считывание входных файлов.

### Версия Java: 17.0.11
