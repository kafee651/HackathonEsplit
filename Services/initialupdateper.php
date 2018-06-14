<?php
    $con = mysqli_connect("localhost", "root", "Cyac-hs8yvxAGN", "hackathon");
    
    $eventid = $_POST["eventid"];
    $amountset = $_POST["amountset"];


    $statement = mysqli_prepare($con, "UPDATE eventref SET shareamount = ?, due = ? WHERE eventid = ?");
    mysqli_stmt_bind_param($statement, "ddi", $amountset, $amountset, $eventid);
    mysqli_stmt_execute($statement);

    $response = array();
    $response["success"] = false;  
    
    if(mysqli_stmt_execute($statement)){
        $response["success"] = true;  
    }
    echo json_encode($response);
?>
