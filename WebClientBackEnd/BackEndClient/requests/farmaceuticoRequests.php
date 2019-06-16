<?php
class requestFarmaceutico{

    public function listaSolicitacoesMedicamentos(){
        require_once('../utils/URLS.php');
        $listGravidades = new Urls();
        $ch = curl_init();
        $url = $listGravidades->LISTAR_SOLICITACOES_MEDICAMENTOS();
        curl_setopt($ch, CURLOPT_POST, 1);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_URL, $url);
        $result = curl_exec($ch);
        curl_close($ch);
        $result = json_decode($result, true);
        return $result;
    }
    
    

}
?>