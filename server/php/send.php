<?php

require('datastore.inc');

if ($_REQUEST['url']) {
  $url = $_REQUEST['url'];
  $handle = get_mysql_db_handle();

  $sql = "INSERT INTO sharetobrowser_shares (`url`, ts) values('%s', now())";
  $sql = sprintf($sql, mysql_real_escape_string($url));
  $res = mysql_query($sql, $handle);
  if ($res) {
    $response = array('status' => 'ok', 'url' => $url);
  } else {
    error_log(mysql_error());
  }
} else {
  $response = array('status' => 'error', 'msg' => 'no url');
}

header("Content-Type: application/json");

echo json_encode($response) . "\n";