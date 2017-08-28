<?php
require_once('dbConnect.php');

if($_SERVER['REQUEST_METHOD']=='POST') {

  $response = array();
  //mendapatkan data
  $npm = $_POST['npm'];
  $nama = $_POST['nama'];
  $deskripsi = $_POST['deskripsi'];
  $jeniskelamin = $_POST['jeniskelamin'];

  $sql = "UPDATE perusahaan SET nama = '$nama', deskripsi = '$deskripsi', jeniskelamin = '$jeniskelamin' WHERE npm = '$npm'";
  if(mysqli_query($con,$sql)) {
    $response["value"] = 1;
    $response["message"] = "Berhasil diperbarui";
    echo json_encode($response);
  } else {
    $response["value"] = 0;
    $response["message"] = "oops! Gagal merubah!";
    echo json_encode($response);
  }
  mysqli_close($con);
}

?>