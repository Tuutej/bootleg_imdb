<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />

<link href="styles/demo.css" rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<title>Muokkaa elokuvaa</title>
<style type="text/css">
label {
	display: block;
	width: 8em;
	float: left;
}
</style>
</head>
<body>
<h1>Muokkaa elokuvaa</h1>
	<form action="/muokkaa-elokuvaa" method="post">
	
		<input type="hidden" name="id" value="${movie.id}" />	
		<p>
			<label>Nimi:</label> <input type="text" value="${movie.name}" name="name" size="50" />
		</p>
		<p>
			<label>Ohjaaja:</label> <input type="text" value="${movie.director}" name="director" size="50" />
		</p>
		<p>
			<label>Genre</label> <input type="text" value="${movie.genre}" name="genre" size="50" />
			
		</p>
		<p>
			<label>Julkaisuvuosi</label> <input type="text" value="${movie.releaseYear}" name="releaseYear" size="50" />
			
		</p>
		<p>
			<label>Kesto minuuteissa</label> <input type="text" value="${movie.runtimeMinutes}" name="runtimeMinutes" size="50" />
			
		</p>
		<p>
			<label>Tuotto miljoonissa ($)</label> <input type="text" value="${movie.boxOfficeUsd}" name="boxOfficeUsd" size="50" />
			
		</p>
		<p>
		<span class="button"><a href="/listaa-elokuvat" >Peruuta</a></span>
		<input type="submit" name="submit-button" class="btn btn-success" value="Muokkaa ja tallenna" />
		</p>
	</form>
</body>
</html>

