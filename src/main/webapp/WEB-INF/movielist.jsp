<%@ page language="java" contentType="text/html; ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <meta charset="utf-8" />
    <title>Bootleg IMDb</title>
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
    />
  </head>
  <body>
    <h1>Bootleg IMDb</h1>
    <a href="http://localhost:8080/hallinta">Hallintasivu</a>
    <table class="table table_striped">
      <tr>
        <th>Id</th>
        <th>Elokuvan nimi</th>
        <th>Ohjaaja</th>
        <th>Genre</th>
        <th>Julkaisuvuosi</th>
        <th>Kesto minuuteissa</th>
        <th>Tuotto miljoonissa ($)</th>
        <th>&nbsp;</th>
      </tr>

      <c:forEach items="${movies}" var="movies">
        <tr>
          <td><c:out value="${movies.id}" /></td>
          <td><c:out value="${movies.name}" /></td>
          <td><c:out value="${movies.director}" /></td>
          <td><c:out value="${movies.genre}" /></td>
          <td><c:out value="${movies.releaseYear}" /></td>
          <td><c:out value="${movies.runtimeMinutes}" /></td>
          <td><c:out value="${movies.boxOfficeUsd}" /></td>
          <td>
            <a href="/poista-elokuva?id=<c:out value='${movies.id}'/>"
              >Poista</a
            >
          </td>
          <td>
            <a href="/muokkaa-elokuvaa?id=<c:out value='${movies.id}'/>"
              >Muokkaa</a
            >
          </td>
        </tr>
      </c:forEach>
    </table>
  </body>
</html>
