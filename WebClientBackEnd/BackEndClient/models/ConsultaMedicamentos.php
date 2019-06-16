<?php


class ConsultaMedicamentos
{
    private $nome_paciente;
    private $doc_paciente;
    private $observacoes;
    private $finalizado;
    private $qtd_medicamentos;


    //SETS
    function setNomePaciente($nome_paciente){
        $this->nome_paciente = $nome_paciente;
    }
    function setDocPaciente($doc_paciente){
        $this->doc_paciente = $doc_paciente;
    }
    function setObservacoes($observacoes){
        $this->observacoes = $observacoes;
    }
    function setFinalizado($finalizado){
        $this->finalizado = $finalizado;
    }
    function setQtdMedicamentos($qtd){
        |$this->qtd_medicamentos= $qtd;
    }

    //GETS
    function getNomePaciente(){
        return $this->nome_paciente;
    }
    function getDocPaciente() {
        return $this->doc_paciente;
    }
    function getObservacoes() {
        return $this->observacoes;
    }
    function getFinalizado() {
        return $this->finalizado;
    }
    function getQtdMedicamentos(){
        return $this->qtd_medicamentos;
    }
    
}

?>