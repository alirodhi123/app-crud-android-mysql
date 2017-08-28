<?php
require_once('dbConnect.php');
if($_SERVER['REQUEST_METHOD']=='GET') {
  $sql = "SELECT * FROM perusahaan ORDER BY nama ASC";
  $res = mysqli_query($con,$sql);
  $result = array();
  while($row = mysqli_fetch_array($res)){
    array_push($result, array('npm'=>$row[0], 'nama'=>$row[1], 'deskripsi'=>$row[2], 'jeniskelamin'=>$row[3],"image"=>$row[4]));
  }
  echo json_encode(array("value"=>1,"result"=>$result));
  mysqli_close($con);
}
?>