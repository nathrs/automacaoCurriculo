#Author: nathalia.ribeiro@outlook.com.br
@tag
Feature: Editar um curriculo existente
  Como usuario do site vagas.com
  Eu desejo acessar a página de serviços
  Para que eu possa alterar informações de um currículo existente

  @tag1
  Scenario: Editar nome e foto do perfil
    Given Acesso a pagina de servicos
    When Seleciono a foto a ser editada
    And Infomo meu nome
    Then alteracoes sao feitas com sucesso

  @tag2
  Scenario: Editar dados pessoais
    Given Acessei edicao de dados pessoais
    When Informo data de nascimento
    And Informo genero
    And Informo estado civil
    And Informo filhos
    And Informo Nacionalidade
    And Informo documento
    Then Salvo e alteracoes de dados pessoais sao realizadas

  @tag3
  Scenario: Editar endereco
    Given Acessei edicao endereco
    When Informo Pais
    And Informo CEP
    And Informo Estado
    And Informo cidade
    And Informo bairro
    And Informo rua
    Then Salvo e alteracoes de endereco sao realizadas

  @tag4
  Scenario: Editar informacoes de contato
    Given Acessei edicao e-mail
    When Informo E-mail
    And Confirmo E-mail
    And Informo telefone
    And Informo ddd-celular
    And Seto combo de recebimento de mensagem
    Then Salvo e alteracoes de contato sao realizadas

  @tag5
  Scenario: Editar informacoes de rede social
    Given Acessei edicao rede social
    When Informo site pessoal
    Then Salvo e alteracoes de rede social sao realizadas

  @tag6
  Scenario: Editar informacoes de deficiencia
    Given Acessei edicao deficiencia
    When Seto SIM para deficiencia
    And Informo tipo deficiencia
    And Preencho observacoes referente a deficiencia
    Then Salvo e alteracoes de deficiencia sao realizadas
