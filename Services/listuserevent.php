<?php
    $con = mysqli_connect("localhost", "root", "Cyac-hs8yvxAGN", "hackathon");
    
    $username = $_POST["username"];


    $statement = mysqli_prepare($con, "SELECT * FROM eventref WHERE username = ?");
    mysqli_stmt_bind_param($statement, "s", $username);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $eventid, $eventname, $username, $amount, $shareamount, $due, $dateofcreation, $dateofupdation);
 
   $response = array();
   $response["success"] = false; 
    while(mysqli_stmt_fetch($statement)){  
        $temp = array();
        $response[$eventid] = strtoupper($eventname)."  Rs:".$amount;
        $response["success"] = true;
    }
    echo json_encode($response);              
?>
