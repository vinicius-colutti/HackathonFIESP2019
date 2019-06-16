<?php
require_once("../requests/medicamentosRequests.php");
$inserirMedicamento = new requestMedicamentos();
$result = $inserirMedicamento->inserirMedicamentosConsulta();
print_r($result);
?>