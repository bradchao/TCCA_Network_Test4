<?php

$method = $_SERVER['REQUEST_METHOD'];
if ($method == 'POST'){
	$account = $_POST['account'];
	$passwd = $_POST['passwd'];
	$realname = $_POST['realname'];

	$conn = new mysqli("localhost", "root", "root", "tcca");
	mysqli_query("SET NAME 'utf8'");

	$sql = "INSERT INTO member (account,passwd,realname) VALUES ('{$account}','{$passwd}','{$realname}')";
	$result = mysqli_query($conn, $sql);
	
	if ($result){
		echo "OK";
	}else{
		echo "XX2";	
	}
}else{
	echo "XX1";
}


?>