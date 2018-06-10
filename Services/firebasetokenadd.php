<?php
    $con = mysqli_connect("localhost", "root", "Cyac-hs8yvxAGN", "hackathon");
    
    $username = $_POST["username"];
    $tokennew = $_POST["token"];

    $statement = mysqli_prepare($con, "SELECT * FROM firebaseUserToken WHERE username = ?");
    mysqli_stmt_bind_param($statement, "s", $username);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $username, $token, $updatetime);
 
   $response = array();
    $response["success"] = false;  
    
    if(mysqli_stmt_fetch($statement)){
        $statement = mysqli_prepare($con, "UPDATE firebaseUserToken SET token = ? WHERE username = ?");
        mysqli_stmt_bind_param($statement, "ss", $tokennew, $username);
        if(mysqli_stmt_execute($statement)) 
            $response["success"] = true;
    }
    else{
        $statement = mysqli_prepare($con, "INSERT INTO firebaseUserToken (username, token) VALUES (?,?)");
        mysqli_stmt_bind_param($statement, "ss", $username, $tokennew);
        if(mysqli_stmt_execute($statement)) 
            $response["success"] = true;
    }
    
    echo json_encode($response);
?>
