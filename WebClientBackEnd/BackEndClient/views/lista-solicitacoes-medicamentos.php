<?php
require_once("../requests/farmaceuticoRequests.php");
$medicamentos = new requestFarmaceutico();
$result = $medicamentos->listaSolicitacoesMedicamentos();
print_r(json_encode($result));
?>