<?php

$method = $_SERVER['REQUEST_METHOD'];
if ($method == 'POST'){
	$account = $_POST['account'];
	$passwd = $_POST['passwd'];

	$conn = new mysqli("localhost", "root", "root", "tcca");
	mysqli_query("SET NAME 'utf8'");

	$sql = "SELECT * FROM member WHERE account='{$account}' AND passwd='{$passwd}'";
	$result = mysqli_query($conn, $sql);
	$data = mysqli_fetch_object($result);
	
	if ($data){
		$data->login = "OK";
		echo json_encode($data);
	}else{
		$data = array("login"=>"xx1");
		echo json_encode($data);
	}
}else{
	$data = array("login"=>"xx2");
	echo json_encode($data);
}


?>