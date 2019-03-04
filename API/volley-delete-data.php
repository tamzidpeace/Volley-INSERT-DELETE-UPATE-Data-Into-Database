<?php 


$username = "root";
$password = "";
$host = "localhost";
$db_name = "user_db";
$con = mysqli_connect($host, $username, $password, $db_name);

$name = $_POST["user_name"];


$sql = " delete from registration_info WHERE user_name = '".$name."' ";


if(mysqli_query($con, $sql)) {
	echo "Data deleted successfully";
} else {
	echo "Something went wrong";
}

?>

 <!-- "http://192.168.43.30/volley-delete-data.php" -->