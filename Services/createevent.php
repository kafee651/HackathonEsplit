<?php
    $con = mysqli_connect("localhost", "root", "Cyac-hs8yvxAGN", "hackathon");
    
    $name = $_POST["name"];
    $description = $_POST["description"];
    $createdby = $_POST["username"];
    $amount = $_POST["amount"];
    $statement = mysqli_prepare($con, "INSERT INTO eventlist (name, description, createdby, amount) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sssd", $name, $description, $createdby, $amount);
    mysqli_stmt_execute($statement);


    $statement = mysqli_prepare($con, "SELECT * FROM eventlist WHERE name = ? AND description = ? AND createdby = ? AND amount = ? order by dateofcreation");
    mysqli_stmt_bind_param($statement, "sssd", $name, $description, $createdby, $amount);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
    mysqli_stmt_bind_result($statement, $eventid, $name, $description, $createdby, $dateofcreation, $amount);
 
   $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        $response["success"] = true;  
        $response["eventid"] = $eventid;
        $response["dateofcreation"] = $dateofcreation;
    }
    echo json_encode($response);
?>
