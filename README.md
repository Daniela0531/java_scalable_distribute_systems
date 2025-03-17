# Scalable distributed systems

## Задача 2
Изменить способ хранения данных REST-сервиса из задачи 1:  
вместо HashMap использовать БД. 

## Решение
Написан сервис.
Для запуска необходимо запустить класс Application.

Выполнение запросов:  

GET:
curl -X GET 'http://localhost:8080/get?key={key}'  

PUT:
curl -X PUT 'http://localhost:8080/put?key={key}&value={value}'
