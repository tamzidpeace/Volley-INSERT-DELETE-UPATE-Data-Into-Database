<?php 

$user_name = $_POST["user_name"];
$user_pass = $_POST["user_pass"];
$pre_user_name = $_POST["pre_user_name"];
$pre_user_pass = $_POST["pre_user_pass"];

$username = "root";
$password = "";
$host = "localhost";
$db_name = "user_db";

$con = mysqli_connect($host, $username, $password, $db_name);

$sql = " UPDATE `registration_info` SET `user_name`='".$user_name."',`user_pass`='".$user_pass."' WHERE `user_name` = 
 '".$pre_user_name."' AND `user_pass` = '".$pre_user_pass."' "; 


if(mysqli_query($con, $sql)) {
	echo "Data update successful";
} else {
	echo "Something went wrong";
}


 // <!-- "http://192.168.43.30/volley-update-data.php" -->
?>

