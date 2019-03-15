<?php 

// $user_name = $_POST["user_name"];
// $user_pass = $_POST["user_pass"];

$username = "root";
$password = "";
$host = "localhost";
$db_name = "user_db";
$con = mysqli_connect($host, $username, $password, $db_name);



// $sql = "insert into registration_info values('".$user_name."', '".$user_pass."');";



$sql = "INSERT INTO registration_info (user_name, user_pass)
VALUES (?, ?)";

if(mysqli_query($con, $sql)) {
	echo "Data insertion successful";
} else {
	echo "Something went wrong";
}

?>

 <!-- "http://192.168.43.30/volley-send-data.php" -->