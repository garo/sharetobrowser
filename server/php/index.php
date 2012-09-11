<?php

require('datastore.inc');

$handle = get_mysql_db_handle();

$target = $_COOKIE['sharetobrowser_id'];
if (!$target) {
  header("Location: register.php");
  exit;
}

$sql = "SELECT id, url, ts FROM sharetobrowser_shares WHERE target = '%s' ORDER BY ts DESC limit 20";
$sql = sprintf($sql, mysql_real_escape_string($target));
$res = mysql_query($sql, $handle);
if ($res) {
?>
<ul>
<?php
  while ($row = mysql_fetch_array($res, MYSQL_ASSOC)) {
?>
    <li><a href="<?php echo $row['url'] ?>"><?php echo htmlspecialchars($row['url']);?></a></li>
<?php
  }
?>  

</ul>
<?php


} else {
  error_log(mysql_error());
}

