<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>personList</title>
</head>
<body>
<th:block th:include="/_menu"></th:block>
<div class="container">
  <div class="col-md-offset-1 col-md-10">

<h2>Personal Account Page</h2>
<h3>Welcome :  <span>${userInfo}</span> </h3>
<b>This is protected page!</b>

<a href="addGrowBox">Add GrowBox</a>
<br/><br/>

<div class="panel panel-info">
    <div class="panel-heading">
     <div class="panel-title">Customer List</div>
    </div>
    <div class="panel-body">
    <table class="table table-striped table-bordered">
        <tr>
            <th>Length</th>
            <th>Width</th>
            <th>Height</th>
            <th>Plants</th>
            <th>Sensors</th>
            <th>Action</th>
        </tr>

         <!-- loop over and print our growboxes -->
              <c:forEach var="growBox" items="${growboxes}">

         <!-- construct an "update" link with id -->
                <c:url var="updateLink" value="/updateForm">
                    <c:param name="growBoxId" value="${growBox.id}" />
                </c:url>

         <!-- construct an "delete" link with id -->
                <c:url var="deleteLink" value="/delete">
                    <c:param name="growBoxId" value="${growBox.id}" />
                </c:url>

                <c:url var = "plantsLink" value = "/plants">
                    <c:param name = "growBoxId" value =  "${growBox.id}" />
                </c:url>

                <c:url var = "sensorsLink" value = "/sensors">
                    <c:param name = "growBoxId" value =  "${growBox.id}" />
                </c:url>

        <tr>
            <td>${growBox.length}</td>
            <td>${growBox.width}</td>
            <td>${growBox.height}</td>

            <td><a href="${plantsLink}">Plants</a>
            <td><a href = "${sensorsLink}">Sensors</a>

            <td>
                     <!-- display the update link -->
                     <a href="${updateLink}">Update</a>
                     |
                     <a href="${deleteLink}">Delete</a>
            </td>
        </tr>
    </table>
    </div>
    </div>
    </div>
</div>


<form th:action="@{/logout}" method="post">
    <input type="submit" value="Sign Out"/>
</form>
</body>
</html>