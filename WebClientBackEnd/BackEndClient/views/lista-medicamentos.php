<?php
require_once("../requests/medicamentosRequests.php");
$medicamentos = new requestMedicamentos();
$result = $medicamentos->listaMedicamentos();
print_r(json_encode($result));
?>