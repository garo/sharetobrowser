<?php

require('datastore.inc');

$handle = get_mysql_db_handle();

$sql = "SELECT id, url, ts FROM sharetobrowser_shares ORDER BY ts DESC limit 20";
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

