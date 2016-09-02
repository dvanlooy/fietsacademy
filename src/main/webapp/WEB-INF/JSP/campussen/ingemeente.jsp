<%@page contentType='text/html' pageEncoding='UTF-8' session='false'%>
<%@taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@taglib uri='http://vdab.be/tags' prefix='v'%>
<!doctype html>
<html lang='nl'>
<head>
<v:head
	title='Campussen in
${empty param.gemeente ? "een gemeente" : param.gemeente}'></v:head>
</head>
<body>
	<v:menu />
	<h1>Campussen in ${empty param.gemeente ? 'een gemeente' : param.gemeente}</h1>
	<form>
		<label>Gemeente: <span>${fouten.gemeente}</span></label>
		<select name='gemeente'>
		<c:forEach items='${locations}' var='location'>
		<option value ='${location}' <c:if test="${location == param.gemeente}">selected</c:if>>${location}</option>
		</c:forEach>
		</select>
		<input type='submit' value='Zoeken'>
	</form>
	<c:if test='${not empty param and empty fouten and empty campussen}'>
Geen campussen gevonden
</c:if>
	<c:if test="${not empty campussen}">
		<ul>
			<c:forEach items='${campussen}' var='campus'>
				<li>${campus.naam}:&nbsp;${campus.adres.straat}&nbsp;${campus.adres.huisNr}</li>
				<dl>
					<c:forEach items="${campus.telefoonNrs}" var="telefoonNr">
						<dt>${telefoonNr.fax ? "Fax" : "Telefoon"}</dt>
						<dd>${telefoonNr.nummer}&nbsp;${telefoonNr.opmerking}</dd>
					</c:forEach>
				</dl>
			</c:forEach>
		</ul>
	</c:if>
</body>
</html>