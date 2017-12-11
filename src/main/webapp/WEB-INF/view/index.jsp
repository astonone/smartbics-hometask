<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
    <jsp:attribute name="title">Система бронирования</jsp:attribute>

    <jsp:body>

        <div class="container">

            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        <small>Здесь вы сможете забронировать комнату для перегоров в удобное для вас время
                    </h1>
                </div>
                <div class="form-group">
                    <label for="comment">Введите данные для бронирования:</label>
                </div>
                <textarea class="form-control" rows="5" id="comment"></textarea>
                <button type="button" class="btn btn-success booking-button">Запрос на бронирование</button>
                <div class="panel-group">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h4 class="panel-title">
                                <a data-toggle="collapse" href="#collapse1">Нажмите чтобы узнать подробности</a>
                            </h4>
                        </div>
                        <div id="collapse1" class="panel-collapse collapse">
                            <div class="panel-body">
                                Первая строка входного текста представляет часы работы компании, в 24-часовом формате.<br>
                                Остальная часть ввода представляет собой индивидуальные запросы на бронирование.<br>
                                Каждый запрос на бронирование имеет следующий формат:<br>
                                [время отправки запроса в формате YYYY-MM-DD HH: MM: SS] [идентификатор сотрудника]<br>
                                [время начала встречи в формате YYYY-MM-DD HH: MM] [продолжительность встречи в часах]<br>
                            </div>
                            <div class="panel-footer">
                                <b>Пример запроса:</b><br>
                                0900 1730<br>
                                2011-03-17 10:17:06<br>
                                EMP001<br>
                                2011-03-21 09:00 2<br>
                                2011-03-16 12:34:56<br>
                                EMP002<br>
                                2011-03-21 09:00 2<br>
                                2011-03-16 09:28:23<br>
                                EMP003<br>
                                2011-03-22 14:00 2<br>
                                2011-03-17 11:23:45<br>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:body>
</page:template>