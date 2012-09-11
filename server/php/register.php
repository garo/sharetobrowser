<?php

function randString($length, $charset='ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789')
{
    $str = '';
    $count = strlen($charset);
    while ($length--) {
        $str .= $charset[mt_rand(0, $count-1)];
    }
    return $str;
}

$id = randString('32');
$uri = "sh2b://" . $id;

setcookie('sharetobrowser_id', $uri, time() + 86400 * 356 * 10);

?>
<h2>Please scan this code with the ShareToBrowser app to register this computer as a target</h2>
Your unique identifier is <?php echo $uri; ?><br>
<img src="generate-qr-code.php?code=<?php echo $uri; ?>" />
