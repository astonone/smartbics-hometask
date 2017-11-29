<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>
  <jsp:attribute name="title">Hello</jsp:attribute>

  <jsp:body>

    <div class="container">

      <div class="row">
        <div class="col-lg-12">
          <h1 class="page-header">Hello
            <small>hello
          </h1>
        </div>
      </div>
    </div>

  </jsp:body>
</page:template>