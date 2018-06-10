<?php
    $con = mysqli_connect("localhost", "root", "Cyac-hs8yvxAGN", "hackathon");
    
    //$username = $_POST["username"];
    $eventid = $_POST["eventid"];
/*

    $statement = mysqli_prepare($con, "SELECT * FROM firebaseUserToken WHERE username = ?");
    mysqli_stmt_bind_param($statement, "s", $username);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $username, $token, $updatetime);
 
   $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["username"] = $username;
        $page = shell_exec("/var/www/html/hackathon/my_script.php '".$token."' '".$eventname."'");
    }*/
    $response["success"] = true;
    echo json_encode($response);
?>
