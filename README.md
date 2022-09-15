# Automation_Selenide_Page_Objects [![Build status](https://ci.appveyor.com/api/projects/status/ixtpiw17xt969ukq/branch/main?svg=true)](https://ci.appveyor.com/project/holyblaz/automation-selenide-page-objects/branch/main)

# Обучение в Нетологии.

## Курс Автоматизированное тестирование

## Тема: Behaviour Driven Development

Тестирование функции перевода с карты на карту(приложение ``` app-ibank-build-for-testers.jar```) с методами:

- Перевода с определённой карты на другую карту n'ой суммы
- Проверки баланса по карте (со страницы списка карт)

**Для запуска проекта:**
1. Склонировать проект из репозитория командой 

```
git clone https://github.com/holyblaz/Automation_Selenide_Page_Objects.git
``` 
2. Открыть склонированный проект в Intellij IDEA
3. Открыть в терминале каталог ```artifacts```
4. Для запуска приложения ввести команду ```java -jar ./app-ibank-build-for-testers.jar```
5. Для запуска в браузере ввести ссылку http://localhost:9999/
6. Запустить команду ```./gradlew test```
