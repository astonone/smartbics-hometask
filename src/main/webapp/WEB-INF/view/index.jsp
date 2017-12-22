<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:attribute name="title">Система бронирования</jsp:attribute>

    <jsp:body>
        <ul class="container" ng-app="booking-service" ng-controller="BookingController">
            <ul class="nav nav-tabs">
                <li class="active"><a data-toggle="tab" href="#home">Бронирование</a></li>
                <li><a data-toggle="tab" href="#menu1">Просмотр брони по дате</a></li>
            </ul>
            <div class="tab-content">
                <div id="home" class="tab-pane fade in active">
                    <div class="row">
                        <div class="col-lg-12">
                            <h1 class="page-header">
                                <small>Здесь вы сможете забронировать комнату для перегоров в удобное для вас время
                                </small>
                            </h1>
                        </div>
                        <div class="form-group">
                            <label for="comment">Введите данные для бронирования:</label>
                        </div>
                        <textarea class="form-control" rows="5" id="comment" ng-model="bookingsRequestData"></textarea>
                        <button type="button" class="btn btn-primary" style="margin-bottom: 10px;
                                                        margin-top: 10px" ng-click="sendRequestForBooking()">Запрос на
                            бронирование
                        </button>
                        <div class="alert alert-danger" ng-show="isNOData">
                            <strong>Отказ!</strong> По вашему запросу нельзя ничего забронировать
                        </div>
                        <div class="alert alert-success" ng-show="isRequested && !isNoData">
                            <strong>Следующие заявки могут быть успешно забронированы</strong><br>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Рабочие часы компании</th>
                                    <th>Дата отправки запроса</th>
                                    <th>Сотрудник</th>
                                    <th>Дата бронирования</th>
                                    <th>Часы</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr ng-repeat="row in successfullBookingsData">
                                    <td>{{row.companyWorkTime}}</td>
                                    <td>{{row.requestDate}}</td>
                                    <td>{{row.employee}}</td>
                                    <td>{{row.bookingDate}}</td>
                                    <td>{{row.bookingTime}}</td>
                                </tr>
                                </tbody>
                            </table>
                            <strong>Забронировать успешные заявки?</strong><br>
                            <button type="button" class="btn btn-success" ng-click="makeBooking()">Да</button>
                            <button type="button" class="btn btn-danger" ng-click="isRequested = false">Нет</button>
                        </div>
                        <div class="alert alert-success" ng-show="isBooked">
                            <strong>Указанные выше заявки были успешно забронированы</strong><br>
                        </div>
                        <div class="panel-group">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a data-toggle="collapse" href="#collapse1">Нажмите чтобы узнать подробности</a>
                                    </h4>
                                </div>
                                <div id="collapse1" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        Первая строка входного текста представляет часы работы компании, в 24-часовом
                                        формате.<br>
                                        Остальная часть ввода представляет собой индивидуальные запросы на бронирование.<br>
                                        Каждый запрос на бронирование имеет следующий формат:<br>
                                        [время отправки запроса в формате YYYY-MM-DD HH: MM: SS] [идентификатор
                                        сотрудника]<br>
                                        [время начала встречи в формате YYYY-MM-DD HH: MM] [продолжительность встречи в
                                        часах]<br>
                                    </div>
                                    <div class="panel-footer">
                                        <b>Пример запроса:</b><br>
                                        0900 1730<br>
                                        2011-03-17 10:17:06 EMP001<br>
                                        2011-03-21 09:00 2<br>
                                        2011-03-16 12:34:56 EMP002<br>
                                        2011-03-21 09:00 2<br>
                                        2011-03-16 09:28:23 EMP003<br>
                                        2011-03-22 14:00 2<br>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="menu1" class="tab-pane fade">
                    <div class="row">
                        <div class="form-group">
                            <h1 class="page-header">
                                <small>В данном разделе можно посмотреть уже имеющиеся брони</small>
                            </h1>
                            <label for="date">Введите дату:</label>
                            <input type="text" class="form-control" id="date" placeholder="ГГГГ/ММ/ДД"
                                   ng-model="existsBookingsByDate">
                            <button type="button" class="btn btn-primary booking-button" style="margin-bottom: 10px;
                                                        margin-top: 10px" ng-click="getBookingsByDate()">Посмотреть
                                результаты
                            </button>
                            <table class="table" ng-show="isNotEmpty">
                                <thead>
                                <tr>
                                    <th>Рабочие часы компании</th>
                                    <th>Дата отправки запроса</th>
                                    <th>Сотрудник</th>
                                    <th>Дата бронирования</th>
                                    <th>Часы</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr ng-repeat="row in bookingsData">
                                    <td>{{row.companyWorkTime}}</td>
                                    <td>{{row.requestDate}}</td>
                                    <td>{{row.employee}}</td>
                                    <td>{{row.bookingDate}}</td>
                                    <td>{{row.bookingTime}}</td>
                                </tr>
                                </tbody>
                            </table>
                            <div class="alert alert-info" ng-show="!isNotEmpty && !error">
                                <strong>На введенную дату пока ничего не забронировано</strong>
                            </div>
                            <div class="alert alert-danger" ng-show="error">
                                <strong>Ошибка!</strong> Вы ввели дату в неккоректном формате(отличающемся от ГГГГ/ММ/ДД), либо ввели её не полностью или совсем не ввели</div>
                        </div>
                    </div>
                    <div>
                    </div>
                </div>
            </div>
            </div>
            </div>
        </ul>
        </div>

    </jsp:body>
</page:template>