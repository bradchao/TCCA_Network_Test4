<?php

$method = $_SERVER['REQUEST_METHOD'];
if ($method == 'POST'){
	$account = $_POST['account'];
	$passwd = $_POST['passwd'];
	echo "POST: {$account}:{$passwd}";
}else{
	$account = $_GET['account'];
	$passwd = $_GET['passwd'];
	echo "GET: {$account}:{$passwd}";
}


?>