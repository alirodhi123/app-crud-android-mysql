<?php

if($_SERVER['REQUEST_METHOD']=='POST') {

   $response = array();
   //mendapatkan data
   $npm = $_POST['npm'];
   $nama = $_POST['nama'];
   $deskripsi = $_POST['deskripsi'];
   $jeniskelamin = $_POST['jeniskelamin'];

   require_once('dbConnect.php');
   //Cek npm sudah terdaftar apa belum
   $sql = "SELECT * FROM perusahaan WHERE npm ='$npm'";
   $check = mysqli_fetch_array(mysqli_query($con,$sql));
   if(isset($check)){
     $response["value"] = 0;
     $response["message"] = "oops! ID sudah terdaftar!";
     echo json_encode($response);
   } else {
     $sql = "INSERT INTO perusahaan (npm,nama,deskripsi,jeniskelamin) VALUES('$npm','$nama','$deskripsi','$jeniskelamin')";
     if(mysqli_query($con,$sql)) {
       $response["value"] = 1;
       $response["message"] = "Sukses mendaftar";
       echo json_encode($response);
     } else {
       $response["value"] = 0;
       $response["message"] = "oops! Coba lagi!";
       echo json_encode($response);
     }
   }
   // tutup database
   mysqli_close($con);
} else {
  $response["value"] = 0;
  $response["message"] = "oops! Coba lagi!";
  echo json_encode($response);
}
?>