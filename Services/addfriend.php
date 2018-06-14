<?php
    $con = mysqli_connect("localhost", "root", "Cyac-hs8yvxAGN", "hackathon");
    
    $username = $_POST["username"];
    $friend = $_POST["friendnameadd"];
    $statement = mysqli_prepare($con, "INSERT INTO friendslist (username, friend) VALUES (?, ?)");
    mysqli_stmt_bind_param($statement, "ss", $username, $friend);
    
    $response = array();
    $response["success"] = false;  
    $response["username"] = $username;
    $response["friend"]=$friend;
    if(mysqli_stmt_execute($statement))
        $response["success"] = true;
    
    echo json_encode($response);
?>
