<?php

require('db-settings.inc');

function get_mysql_db_handle() {
  global $DB;
  static $handle = null;
  if (!$handle) {
    $handle = mysql_connect($DB['host'], $DB['user'], $DB['password']);
    mysql_select_db($DB['name']);
  }

  return $handle;
} 

function set_value($key, $value) {
  $handle = get_mysql_db_handle();
  $sql = "INSERT INTO keyvalue (`key`, value) values('%s', '%s') ON DUPLICATE KEY UPDATE value = '%s'";
  $sql = sprintf($sql, mysql_real_escape_string($key), mysql_real_escape_string($value), mysql_real_escape_string($value));
  $res = mysql_query($sql, $handle);
  echo mysql_error();

}

function get_value($key) {
  $handle = get_mysql_db_handle();
  $res = mysql_query("SELECT value FROM keyvalue WHERE `key` = '" . mysql_real_escape_string($key) . "'", $handle);
  if (!$res) {
    error_log("no res");
    error_log(mysql_error());
    return null;
  }
  $arr = mysql_fetch_array($res, MYSQL_NUM);
  if (!$arr) {
    return null;
  }
  mysql_free_result($res);

  return $arr[0];
}

