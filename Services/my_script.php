token=$1;
body=$2;
cat /var/www/html/hackathon/original.php | sed -e "s/replacevalue2/${token}/g" > /var/www/html/hackathon/temp.php;
cat /var/www/html/hackathon/temp.php | sed -e "s/replacevalue1/${body}/g" > /var/www/html/hackathon/temp1.php;
sh /var/www/html/hackathon/temp1.php;
