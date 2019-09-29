<%--
  Created by IntelliJ IDEA.
  User: fabio
  Date: 28/09/19
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <c:forEach var="file" items="${directories}">
            <div style="display: inline-block; padding: 15px" >
                <c:if test="${file.isDirectory}">
                    <img src="https://upload.wikimedia.org/wikipedia/commons/d/dc/Blue_folder_seth_yastrov_01.svg" style="height: 50px; width:50px" alt="directory">
                    <a href="/directory?directory=${file.absolutePath}">Access directory</a>
                </c:if>
                <c:if test="${!file.isDirectory}">
                    <img src="https://upload.wikimedia.org/wikipedia/commons/0/0c/File_alt_font_awesome.svg" style="height: 50px; width:50px" alt="file">
                    <br>
                    <p>Caminho absoluto do arquivo: ${file.absolutePath}</p>
                </c:if>
                <p>Nome: ${file.name}</p>
                <p>Data de criação: ${file.createDate}</p>
                <p>Ultima data de modificação: ${file.lastModified}</p>
                <p>Tamanho: ${file.size}</p>
            </div>
        </c:forEach>
    </body>
</html>
