<?php

require('datastore.inc');

if ($_REQUEST['url'] && $_REQUEST['target']) {
  $url = $_REQUEST['url'];
  $target = $_REQUEST['target'];
  $handle = get_mysql_db_handle();

  $sql = "INSERT INTO sharetobrowser_shares (`url`, `target`, ts) values('%s', '%s', now())";
  $sql = sprintf($sql, mysql_real_escape_string($url), mysql_real_escape_string($target));
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