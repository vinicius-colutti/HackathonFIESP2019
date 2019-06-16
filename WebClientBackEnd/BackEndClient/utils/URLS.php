<?php
class Urls
{

  public function BASE_API(){return 'http://5e2df4da.ngrok.io';}
  public function INSERIR_PEDIDOS_EMERGENCIA(){return $this->BASE_API().'/servicos/cadastra-pedidos-emergenciais';}
  public function LISTAR_GRAVIDADES(){return $this->BASE_API().'/servicos/lista-gravidades';}
  public function LISTAR_ESPECIALIDADES(){return $this->BASE_API().'/servicos/lista-especialidades';}
  public function LISTAR_MEDICAMENTOS(){return $this->BASE_API().'/servicos/lista-medicamentos';}
  public function INSERIR_RECEITA_MEDICAMENTO(){return $this->BASE_API().'/servicos/lista-especialidades';}
  public function LISTAR_SOLICITACOES_MEDICAMENTOS(){return $this->BASE_API().'/servicos/lista-dados-farmaceuticos';}
}

?>