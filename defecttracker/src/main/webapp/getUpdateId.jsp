<!doctype html>
<html>
<head>
	<title>Get Update Id</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link href="https://fonts.googleapis.com/css?family=Josefin+Slab" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif' rel='stylesheet' type='text/css'>
</head>
<body>

<h2>    
    <img src="/images/bugTrackerLeft.png" alt="Bug Tracker" style="width:80;height:80px;">
    <span class="bugLarge">Bug</span> Tracker
    <img src="/images/bugTrackerRight.png" alt="Bug Tracker" style="width:80;height:80px;">
</h2>

<form action="DefectTrackerServlet" method="POST">

<input type="hidden" name="menuOption" value="updateDefect2"/>

<table>
    <tr>
        <td>Enter <span class="bugSmall">Bug</span> Id:</td>
        <td><input type="text" name="defectId"></td>
    </tr>
</table>
<table>
    <tr>
        <td><input type="submit" value="Submit" /></td>
    </tr>
</table>
</form>
</body>
</html>