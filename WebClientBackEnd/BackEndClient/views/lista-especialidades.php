<?php
require_once("../requests/consultaRequests.php");
$especialidades = new requestConsultas();
$result = $especialidades->listaEspecialidades();
print_r(json_encode($result));
?>