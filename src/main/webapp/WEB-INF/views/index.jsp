<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Keep-Board</title>
</head>

<body>
	<!-- Create a form which will have text boxes for Note title, content and status along with a Add
		 button. Handle errors like empty fields.  (Use dropdown-list for NoteStatus) -->
    <div class = "container">
        <h3> Add a Note </h3>
        <form action ="add" method = "post">
                <label> Note Title: </label>
                <input type = "text" id = "noteTitle" name ="noteTitle" placeholder="Note Title" required/> <br>

                <label> Note Content: </label>
                <input type = "text" id="noteContent" name = "noteContent" placeholder = "Note Content" required/> <br>

                <label> Note Status: </label>
                <select name = "noteStatus">
                 <option value = "active"> Active </option>
                 <option value = "inactive"> Not Active </option>
                 </select> <br>

                 <button type = "submit" > Add </button>
          </form>

          <h3>Delete Note</h3>
            <form action="deleteNote">
             <input type="text" name="noteId" />
             <input type="submit"/>
           </form>


	<!-- display all existing notes in a tabular structure with Title,Content,Status, Created Date and Action -->
   <div class="container">
       <h2> Saved Notes </h2>
       <table border = "2">
          <tr>
             <th> Note Id </th>
             <th> Title </th>
             <th> Content </th>
             <th> Status </th>
             <th> Created DateTime </th>
          </tr>
          <c:forEach items="${save}" var="note">
               <tr>
                 <td> <c:out value="${note.noteTitle}"> </c:out></td>
                 <td> <c:out value="${note.noteContent}"> </c:out></td>
                 <td> <c:out value="${note.noteStatus}"> </c:out></td>
                 <td> <c:out value="${note.createdAt}"></c:out></td>
               </tr>
          </c:forEach>
       </table>
      </div>

</body>

</html>