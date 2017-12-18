# Booking Service

Для запуска приложения необходимо настроить рабочу среду.
1. Для начала нужно создать базу данных в субд *postgresql* на порту 5432:

* CREATE DATABASE booking_system;
* CREATE USER viktor_kulygin WITH password '123456';
* GRANT ALL ON DATABASE booking_system TO test_user;

2. Далее импортируем проект как *maven project* в *inteliJJ IDEA* щелкаем по вкладке *Database*
для того чтобы настроить соединение с базой данных
3. Создаем соединение с параметрами описаными выше
![Настройки соединения](http://www.picshare.ru/uploads/171218/BQt47YEH06.jpg)
После успешного создания БД нам наобходимо выполнить скрипт лежащий по адресу *dataBaseScheme/dbScheme.sql*
Вуаля, база создана,настроена и готова к работе!

4. После успешно создания соединения с базой данных, настроим запуск и деплоинг проекта,
кликаем по кнопке *Edit configurations*
![Настройки соединения](http://www.picshare.ru/uploads/171218/uU9r6vIz20.jpg)
5. Далее нажимаем значок *+* в разделе *Press the + button to create a new Tomcat Server Run Cinfiguration based ...*
![Настройки соединения](http://www.picshare.ru/uploads/171218/E983P7Tmkb.jpg)
6. Далее вибираем *Tomcat Server* -> *Local*
![Настройки соединения](http://www.picshare.ru/uploads/171218/F7N974x769.jpg)
7. Задаем желаемое имя для нашего соединения, меняем настройки портов, в случае необходимости
![Настройки соединения](http://www.picshare.ru/uploads/171218/LrcmLjpo0L.jpg)
8. Затем щелкаем по вкладке *Deployment* и добавлем необходимый *artifact* для сборки, как показано на рисунке ниже
![Настройки соединения](http://www.picshare.ru/uploads/171218/54t2418wjK.jpg) 
9. Теперь все настройки готовы, жмем везде кнопки *OK*
![Запуск](http://www.picshare.ru/uploads/171218/ZTqRGAtf9O.jpg) 
10. Теперь запускаем приложение, через созданное и настроеное нами соединение,
после чего мы должны увидеть следующее:
![Система бронирования](http://www.picshare.ru/uploads/171218/8H33g9hZ7h.jpg) 
![Система бронирования](http://www.picshare.ru/uploads/171218/99HAeYy192.jpg)
