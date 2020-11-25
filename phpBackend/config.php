<?php
/* Database credentials. Assuming you are running MySQL
server with default setting (user 'root' with no password) */
require "vendor/autoload.php";
define('DB_SERVER', 'localhost');
define('DB_USERNAME', 'root');
define('DB_PASSWORD', '');
define('DB_NAME', 'poker');
define('CHARSET', 'utf8mb4_polish_ci');
$key = 'Poker';
date_default_timezone_set('Europe/Warsaw');
$arrLocales = array('pl_PL.utf8', 'pl');
setlocale( LC_ALL, $arrLocales );

/* Attempt to connect to MySQL database */
try {
    $pdo = new PDO("mysql:host=" . DB_SERVER . ";dbname=" . DB_NAME . ";charset=" . CHARSET, DB_USERNAME, DB_PASSWORD);
    $pdo->setAttribute( PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION );
} catch (PDOException $e) {
    print "Error!: " . $e->getMessage() . "<br/>";
    die();
}
?>
