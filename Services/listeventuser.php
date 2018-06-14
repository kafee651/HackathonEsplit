<?php
    $con = mysqli_connect("localhost", "root", "Cyac-hs8yvxAGN", "hackathon");
    
    $eventid = $_POST["eventid"];


    $statement = mysqli_prepare($con, "SELECT * FROM eventref WHERE eventid = ?");
    mysqli_stmt_bind_param($statement, "i", $eventid);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $eventid, $eventname, $username, $amount, $shareamount, $due, $dateofcreation, $dateofupdation);
 
   $response = array();
   $response["success"] = false; 
    while(mysqli_stmt_fetch($statement)){  
        $temp = array();
        $response[$username] = strtoupper($username)."  Share Rs:".$shareamount." Due Rs:".$due;
        $response["success"] = true;
    }
    echo json_encode($response);              
?>
