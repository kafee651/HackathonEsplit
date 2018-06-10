<?php
    $con = mysqli_connect("localhost", "root", "Cyac-hs8yvxAGN", "hackathon");
    
    $username = $_POST["username"];


    $statement = mysqli_prepare($con, "SELECT * FROM eventref WHERE username = ?");
    mysqli_stmt_bind_param($statement, "s", $username);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $eventid, $username, $amount, $shareamount, $due, $dateofcreation, $dateofupdation);
 
   $response = array();
   $response["success"] = false; 
   $i = 1;
    while(mysqli_stmt_fetch($statement)){  
        $temp = array();
        $temp["eventid"] = $eventid;
        $temp["username"] = $username;
        $temp["amount"] = $amount;
        $temp["shareamount"] = $shareamount;
        $temp["due"] = $due;
        $temp["dateofcreation"] = $dateofcreation;
        $temp["dateofupdation"] = $dateofupdation;
        $response[$i] = $temp;
        $i = $i + 1;
        $response["success"] = true;
    }
    echo json_encode($response);
?>
