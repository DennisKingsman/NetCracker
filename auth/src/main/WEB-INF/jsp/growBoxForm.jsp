<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GrowBoxForm </title>
</head>
<body>
    <div class="container">
      <div class="col-md-offset-2 col-md-7">
        <div class="panel panel-info">
            <div class="panel-heading">
             <div class="panel-title">Add GrowBox</div>
        </div>
         <div class="panel-body">
             <form:form action="saveGrowBox" method="post" modelAttribute="growBox">

             <!-- need to associate this data with customer id -->
             <form:hidden path="id" />

             <div class="form-group">
                    <label for="length" class="col-md-3 control-label">Length</label>
                    <div class="col-md-9">
                    <form:input path="length" />
             </div>
             </div>

             <div class="form-group">
                    <label for="width" class="col-md-3 control-label">Width</label>
                    <div class="col-md-9">
                    <form:input path="width"/>
             </div>
             </div>

             <div class="form-group">
                    <label for="height" class="col-md-3 control-label">Height</label>
                    <div class="col-md-9">
                    <form:input path="height"/>
             </div>
             </div>
            </form:form>
         </div>
        </div>
       </div>
    </div>
</body>
</html>