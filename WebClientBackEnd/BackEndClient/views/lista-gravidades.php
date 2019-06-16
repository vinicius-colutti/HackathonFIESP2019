<?php
require_once("../requests/consultaRequests.php");
$gravidades = new requestConsultas();
$result = $gravidades->listaGravidades();
print_r(json_encode($result));
?>