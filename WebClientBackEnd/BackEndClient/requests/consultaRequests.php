<?php
class requestConsultas{
    
    public function listaGravidades(){
        require_once('../utils/URLS.php');
        $listGravidades = new Urls();
        $ch = curl_init();
        $url = $listGravidades->LISTAR_GRAVIDADES();
        curl_setopt($ch, CURLOPT_POST, 1);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_URL, $url);
        $result = curl_exec($ch);
        curl_close($ch);
        $result = json_decode($result, true);
        return $result;
    }

    public function listaEspecialidades(){
        require_once('../utils/URLS.php');
        $listGravidades = new Urls();
        $ch = curl_init();
        $url = $listGravidades->LISTAR_ESPECIALIDADES();
        curl_setopt($ch, CURLOPT_POST, 1);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_URL, $url);
        $result = curl_exec($ch);
        curl_close($ch);
        $result = json_decode($result, true);
        return $result;
    }


    public function inserirPedidoEmergencia(){
        require_once('../utils/URLS.php');
        require_once('../models/ConsultaEmergencia.php');
        $inserirConsultaEmergencia = new Urls();
        $url  = $inserirConsultaEmergencia->INSERIR_PEDIDOS_EMERGENCIA();
        $consulta_emergencia = new ConsultaEmergencia();
        $consulta_emergencia->setIdParamedico($_POST['id_paramedico']);
        $consulta_emergencia->setIdadePaciente($_POST['idade_paciente']);
        $consulta_emergencia->setSituacao(0);
        $consulta_emergencia->setGeneroPaciente($_POST['genero_paciente']);
        $consulta_emergencia->setTraumas($_POST['traumas']);
        $consulta_emergencia->setIdentificacaoPaciente($_POST['identificacao_paciente']);
        $consulta_emergencia->setIdGravidade($_POST['id_gravidade']);
        $consulta_emergencia->setIdEspecialidade($_POST['id_especialidade']);

    
        $data = array("id_paramedico" => $consulta_emergencia->getIdParamedico(),
                      "idade_paciente" => $consulta_emergencia->getIdadePaciente(),
                      "situacao" => $consulta_emergencia->getSituacao(),
                      "genero_paciente" => $consulta_emergencia->getGeneroPaciente(),
                      "traumas" => $consulta_emergencia->getTraumas(),
                      "identificacao_paciente" => $consulta_emergencia->getIdentificacaoPaciente(),
                      "id_gravidade" => $consulta_emergencia->getIdGravidade(),
                      "id_especialidade" => $consulta_emergencia->getIdEspecialidade());

        $postdata = json_encode($data, JSON_UNESCAPED_UNICODE);
        $ch = curl_init($url);
        curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);
        curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, 0);
        curl_setopt($ch, CURLOPT_POST, 1);
        curl_setopt($ch, CURLOPT_POSTFIELDS, $postdata);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
        curl_setopt($ch, CURLOPT_FOLLOWLOCATION, 1);
        curl_setopt($ch, CURLOPT_HEADER, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, array('Content-Type: application/json'));
        $result = curl_exec($ch);
        $http_status = curl_getinfo($ch, CURLINFO_HTTP_CODE);
        curl_close($ch);
        return $result;    
    }

    

}
?>