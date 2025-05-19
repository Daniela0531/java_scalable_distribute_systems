# Scalable distributed systems

## Задача 3
Вместо HashMap использовать БД. 
Реализовать шардирование.

## Решение
Написан сервис.
Есть два шарда, каждый хранится на своей ВМ.
Для запуска необходимо запустить класс Application.

Выполнение запросов:  

GET:
curl -X GET 'http://localhost:8080/get?key={key}'  

PUT:
curl -X PUT 'http://localhost:8080/put?key={key}&value={value}'
