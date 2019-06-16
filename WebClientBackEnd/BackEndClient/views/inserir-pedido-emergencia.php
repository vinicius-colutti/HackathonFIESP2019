<?php
require_once("../requests/consultaRequests.php");
$inserirConsulta = new requestConsultas();
$result = $inserirConsulta->inserirPedidoEmergencia();
print_r($result);
?>