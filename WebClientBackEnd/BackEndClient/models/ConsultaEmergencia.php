<?php
class ConsultaEmergencia 
{
    private $id_paramedico;
    private $idade_paciente;
    private $situacao;
    private $genero_Paciente;
    private $traumas;
    private $identificacao_paciente;
    private $id_gravidade;
    private $id_especialidade;

    //SETS
    function setIdParamedico($id_paramedico){
        $this->id_paramedico = $id_paramedico;
    }
    function setIdadePaciente($idade_paciente){
        $this->idade_paciente = $idade_paciente;
    }
    function setSituacao($situacao){
        $this->situacao = $situacao;
    }
    function setGeneroPaciente($genero_Paciente){
        $this->genero_Paciente = $genero_Paciente;
    }
    function setTraumas($traumas){
        $this->traumas = $traumas;
    }
    function setIdentificacaoPaciente($identificacao_paciente){
        $this->identificacao_paciente = $identificacao_paciente;
    }
    function setIdGravidade($id_gravidade){
        $this->id_gravidade = $id_gravidade;
    }
    function setIdEspecialidade($id_especialidade){
        $this->id_especialidade = $id_especialidade;
    }


    //GETS
    function getIdParamedico(){
        return $this->id_paramedico;
    }
    function getIdadePaciente() {
        return $this->idade_paciente;
    }
    function getSituacao() {
        return $this->situacao;
    }
    function getGeneroPaciente() {
        return $this->genero_Paciente;
    }
    function getTraumas() {
        return $this->traumas;
    }
    function getIdentificacaoPaciente() {
        return $this->identificacao_paciente;
    }
    function getIdGravidade() {
        return $this->id_gravidade;
    }
    function getIdEspecialidade() {
        return $this->id_especialidade;
    }
}



?>