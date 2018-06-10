<?php
    $con = mysqli_connect("localhost", "root", "Cyac-hs8yvxAGN", "hackathon");
    
    $eventid = $_POST["eventid"];
    $username = $_POST["username"];
    $amount = $_POST["amount"];
    $shareamount = $_POST["shareamount"];
    $due = $_POST["due"];
    $dateofcreation = $_POST["dateofcreation"];
    $eventname = $_POST["eventname"];
    $statement = mysqli_prepare($con, "INSERT INTO eventref (eventid, username, amount, shareamount,due,dateofcreation) VALUES (?,?,?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "isddds", $eventid, $username, $amount, $shareamount, $due,$dateofcreation);
    mysqli_stmt_execute($statement);

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
    }
    
    echo json_encode($response);
?>
