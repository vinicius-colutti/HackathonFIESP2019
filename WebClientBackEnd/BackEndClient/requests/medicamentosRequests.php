<?php
class requestMedicamentos{

    public function listaMedicamentos(){
        require_once('../utils/URLS.php');
        $listGravidades = new Urls();
        $ch = curl_init();
        $url = $listGravidades->LISTAR_MEDICAMENTOS();
        curl_setopt($ch, CURLOPT_POST, 1);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_URL, $url);
        $result = curl_exec($ch);
        curl_close($ch);
        $result = json_decode($result, true);
        return $result;
    }
    
    public function inserirMedicamentosConsulta(){
        require_once('../utils/URLS.php');
        require_once('../models/ConsultaMedicamentos.php');
        $inserirReceita = new Urls();
        $url  = $inserirReceita->INSERIR_RECEITA_MEDICAMENTO();
        $consulta_medicamentos = new ConsultaMedicamentos();

        $consulta_medicamentos->setNomePaciente($_POST['nome_paciente']);
        $consulta_medicamentos->setDocPaciente($_POST['doc_paciente']);
        $consulta_medicamentos->setObservacoes($_POST['observacoes']);
        $consulta_medicamentos->setFinalizado(0);
        $consulta_medicamentos->setQtdMedicamentos($_POST['qtd_medicamentos']);

        $consulta_medicamentos->setNomePaciente("Jose da Silva");
        $consulta_medicamentos->setDocPaciente("54786848382");
        $consulta_medicamentos->setObservacoes("Tomar a cada 7 horas o remedio x");
        $consulta_medicamentos->setFinalizado(0);
        $consulta_medicamentos->setQtdMedicamentos(4);

        $cont = 1;
        $codigos = array();
        while($cont <= $consulta_medicamentos->getQtdMedicamentos()) {  
            $codigos[] = $_POST[$cont.'medicamentos'];
            $cont++;
        }
    
        $data = array("nome_paciente" => $consulta_emergencia->getIdParamedico(),
                      "doc_paciente" => $consulta_emergencia->getIdadePaciente(),
                      "observacoes" => $consulta_emergencia->getSituacao(),
                      "finalizado" => $consulta_emergencia->getGeneroPaciente(),
                      "medicamentos" => $codigos);

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