package automacaoCurriculo;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class StepCurriculo extends Runner {
	protected static WebDriver driver;

	public void cenario1() {

	}

	@Given("^Acesso a pagina de servicos$")

	public void acesso_a_pagina_de_servicos() throws Throwable {

		String currentUsersHomeDir = System.getProperty("user.home");
		String diretorioChrome = currentUsersHomeDir + File.separator + "/AppData/Local/Google/Chrome/User Data";

		// Abre navegador
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addArguments(diretorioChrome);
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(25, TimeUnit.SECONDS);

		// Acesso página inicial
		driver.get("https://www.vagas.com.br/login-candidatos");

		WebElement usuario = driver.findElement(By.id("login_candidatos_form_usuario"));
		String user = "testenat";
		usuario.sendKeys(user);

		WebElement senha = driver.findElement(By.id("login_candidatos_form_senha"));
		String pass = "testenat";
		senha.sendKeys(pass);

		WebElement entrar = driver.findElement(By.xpath("//*[@id=\"new_login_candidatos_form\"]/input"));
		entrar.click();

		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement clicaEditar = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"conteudo-servicos\"]/div[1]/section/div/div/a[1]")));
		clicaEditar.click();
	}

	@When("^Seleciono a foto a ser editada$")
	public void seleciono_a_foto_a_ser_editada() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		Thread.sleep(8000);
		WebElement uploadFoto = driver.findElement(By.id("cv-edit-foto"));
		uploadFoto.click();

		try {

			// Existe foto? Se sim, testa botão de remover foto\:
			if (driver.findElements(By.id("cvRemoverFoto")) == null) {

				WebElement removeFoto = wait.until(ExpectedConditions.elementToBeClickable(By.id("cvRemoverFoto")));
				removeFoto.click();
			}
			// Então, clica para adicionar:
			else if (uploadFoto != null) {

				uploadFoto.click();
			}
			// Tratamento no caso de erro no upload
		} catch (Exception e) {
			System.out.print("Arquivo não pode ser editado.");
		}

		// Se não, adiciona foto:
		
		
		String arquivo = "C:\\Users\\nathalia.r.santos\\eclipse-workspace_v3\\automacaoCurriculo\\src\\main\\java\\Vagas.jpg";
		driver.findElement(By.id("candidato_imagem")).sendKeys(arquivo);
		System.out.println("Foto adicionada com sucesso");

	}

	@When("^Infomo meu nome$")
	public void infomo_meu_nome() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Random edit = new Random();

		Thread.sleep(8000);
		WebElement editoNome = driver.findElement(By.xpath("//*[@id=\"cv-nome\"]/a"));
		editoNome.click();

		WebElement nome = wait.until(ExpectedConditions.elementToBeClickable(By.id("curriculo_nome_completo")));
		nome.clear();
		nome.sendKeys("Nathalia" + edit.nextInt());

		WebElement salvarNome = driver.findElement(By.xpath("//*[@id=\"nome-completo-edit\"]/div[2]/div/div/button"));
		salvarNome.click();

	}

	@Then("^alteracoes sao feitas com sucesso$")
	public void alteracoes_sao_feitas_com_sucesso() throws Throwable {
		System.out.println("Nome e foto alterados");

	}

	// -- Cenário 2 --//

	@Given("^Acessei edicao de dados pessoais$")
	public void acessei_edicao_de_dados_pessoais() throws Throwable {
		Thread.sleep(8000);
		
		WebElement editarDP = driver.findElement(By.xpath("//*[@id=\"cv-dados\"]/a"));
		editarDP.click();

	}

	@When("^Informo data de nascimento$")
	public void informo_data_de_nascimento() throws Throwable {
		// Informa data de nascimento
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		WebElement nascimento = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("dados_pessoais_data_de_nascimento")));

		String nasc = "01011990";
		nascimento.clear();
		nascimento.sendKeys(nasc);

		// Opção 2
		// String nasc2 = "020121992";
		// nascimento.clear();
		// nascimento.sendKeys(nasc2);

	}

	@When("^Informo genero$")
	public void informo_genero() throws Throwable {
		WebElement genero = driver.findElement(By.id("dados_pessoais_genero_masculino"));
		genero.click();
		
	}

	@When("^Informo estado civil$")
	public void informo_estado_civil() throws Throwable {
		WebElement estadoCivil = driver.findElement(By.id("dados_pessoais_estado_civil"));
		Select cmbEstadoCivil = new Select(estadoCivil);

		// Escolha uma opção:
		String Solteiro = "Solteiro(a)";
		String Casado = "Casado(a)";
		String Separado = "Separado(a)";
		String Divorciado = "Divorciado(a)";
		String Viuvo = "Viúvo(a)";

		cmbEstadoCivil.selectByVisibleText(Solteiro);
	}

	@When("^Informo filhos$")
	public void informo_filhos() throws Throwable {
		WebElement filhos = driver.findElement(By.id("filhos_sim"));
		filhos.click();

		WebElement inputFilhos = driver.findElement(By.id("dados_pessoais_filhos"));
		inputFilhos.clear();
		String qtdeFilhos = "//d{0-9, 2}";
		inputFilhos.sendKeys(qtdeFilhos);
		
	}

	@When("^Informo Nacionalidade$")
	public void informo_Nacionalidade() throws Throwable {
		WebElement pais = driver.findElement(By.id("dados_pessoais_pais_de_nacionalidade"));
		Select cmbpais = new Select(pais);
		cmbpais.selectByVisibleText("Brasil");
	}

	@When("^Informo documento$")
	public void informo_documento() throws Throwable {
		// Existe documento tipo CPF? Se não:
		if (driver.findElements(By.id("dados_pessoais_documentos_attributes_0_pais_id")) == null) {
			WebDriverWait wait = new WebDriverWait(driver, 15);
			// Clica em adicionar documento
			WebElement adcDocumento = driver.findElement(By.id("btn-add-doc"));
			adcDocumento.click();

			// Seleciona País
			WebElement paisDoc = wait.until(
					ExpectedConditions.elementToBeClickable(By.id("dados_pessoais_documentos_attributes_0_pais_id")));
			Select cmbPaisDoc = new Select(paisDoc);
			cmbPaisDoc.selectByVisibleText("Brasil");

			// Seleciona tipo de documento
			WebElement tipoDoc = wait.until(
					ExpectedConditions.elementToBeClickable(By.id("dados_pessoais_documentos_attributes_0_tipo_id")));
			Select cmbtipoDoc = new Select(tipoDoc);
			cmbtipoDoc.selectByVisibleText("CPF (BRA)");

			// Preenche CPF
			WebElement CPF = driver.findElement(By.id("dados_pessoais_documentos_attributes_0_numero"));
			String numCPF = "90642747032";
			CPF.clear();
			CPF.sendKeys(numCPF);
		}

		// Se sim, informa que CPF já foi informado
		else if (driver.findElements(By.id("dados_pessoais_documentos_attributes_0_pais_id")) != null) {

			System.out.println("CPF já informado");
		}
		
	}

	@Then("^Salvo e alteracoes de dados pessoais sao realizadas$")
	public void Salvo_e_alteracoes_de_dados_pessoais_sao_realizadas() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		//Salva informações sobre dados pessoais
				WebElement salvarDP = wait.until(ExpectedConditions
						.elementToBeClickable(By.xpath("//*[@id=\"edit_dados_pessoais_63289034\"]/div[3]/button")));
				salvarDP.click();
				
				//evidencia
				File screenPrincipal = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				try {
					// now copy the screenshot to desired location using copyFile //method
					FileUtils.copyFile(screenPrincipal, new File("/evidencias" + salvarDP+"_curriculo.jpeg"));
				}	

				catch (IOException e) {
					System.out.println(e.getMessage());
				}		
				
				
				System.out.println("Informações sobre dados pessoais salvas com sucesso");

			}
	
	  // -- Cenário 3 -- //
	@Given("^Acessei edicao endereco$")
	public void acessei_edicao_endereco() throws Throwable {
		
		Thread.sleep(8000);
		WebElement editarEndereco = driver.findElement(By.xpath("//*[@id=\"cv-endereco\"]/a"));
		editarEndereco.click();
	}

	@When("^Informo Pais$")
	public void informo_Pais() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement paisEnd = wait.until(ExpectedConditions.elementToBeClickable(By.id("endereco_pais_id")));
		Select cmbPaisEnd = new Select(paisEnd);
		cmbPaisEnd.selectByVisibleText("Brasil");
	}

	@When("^Informo CEP$")
	public void informo_CEP() throws Throwable {
		WebElement CEP = driver.findElement(By.id("endereco_cep"));
		String numCEP = "09621020";
		CEP.clear();
		CEP.sendKeys(numCEP);
	}

	@When("^Informo Estado$")
	public void informo_Estado() throws Throwable {
		
		WebElement estadoEnd = driver.findElement(By.id("endereco_uf_id"));
		Select cmbestadoEnd = new Select(estadoEnd);
		cmbestadoEnd.selectByVisibleText("Goias");
	}

	@When("^Informo cidade$")
	public void informo_cidade() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		WebElement cidadeEnd = wait.until(ExpectedConditions.elementToBeClickable(By.id("endereco_cidade_id")));
		Select cmbCidadeEnd = new Select(cidadeEnd);
		cmbCidadeEnd.selectByVisibleText("Damolândia");
	}

	@When("^Informo bairro$")
	public void informo_bairro() throws Throwable {
		
		WebElement bairro = driver.findElement(By.id("endereco_bairro"));
		String nomeBairro = "Guanabara";
		bairro.clear();
		bairro.sendKeys(nomeBairro);
	}

	@When("^Informo rua$")
	public void informo_rua() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		WebElement logradouro = wait.until(ExpectedConditions.elementToBeClickable(By.id("endereco_logradouro")));
		logradouro.clear();
		Random edit = new Random();
		logradouro.sendKeys("Rua " + edit.nextInt() + " ,100");	
	}
	
	@Then ("^Salvo e alteracoes de endereco sao realizadas$")
	public void Salvo_e_alteracoes_de_endereco_sao_realizadas() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		WebElement salvarEndereco = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"edit_endereco_\"]/div[3]/button")));
		salvarEndereco.click();
		System.out.println("Endereço salvo");
		
		//evidencia
		File screenPrincipal = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile //method
			FileUtils.copyFile(screenPrincipal, new File("/evidencias" + salvarEndereco+"_curriculo.jpeg"));
		}	

		catch (IOException e) {
			System.out.println(e.getMessage());
		}	

		
	}
	
	//-- Cenário 4 --//
	@Given("^Acessei edicao e-mail$")
	public void acessei_edicao_e_mail() throws Throwable {
		Thread.sleep(3000);
		WebElement editarIC = driver.findElement(By.xpath("//*[@id=\"informacoes-de-contato\"]/a"));
		editarIC.click();
	}

	@When("^Informo E-mail$")
	public void informo_E_mail() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.id("informacoes_de_contato_email")));
		email.clear();
		String novoEmail = "nathaliateste@gmail.com";
		email.sendKeys(novoEmail);
	}

	@When("^Confirmo E-mail$")
	public void confirmo_E_mail() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		WebElement confEmail = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("informacoes_de_contato_confirmacao_de_email")));
		confEmail.clear();
		String novoEmail = "nathaliateste@gmail.com";
		confEmail.sendKeys(novoEmail);
	}

	@When("^Informo telefone$")
	public void informo_telefone() throws Throwable {
		WebElement telefone = driver.findElement(By.id("informacoes_de_contato_telefone_numero"));
		telefone.clear();
		String infTel = "1142201666";
		telefone.sendKeys(infTel);
	}

	@When("^Informo ddd-celular$")
	public void informo_ddd_celular() throws Throwable {
		WebElement ddd = driver.findElement(By.id("informacoes_de_contato_celular_ddd"));
		ddd.clear();
		String infDdd = "13";
		ddd.sendKeys(infDdd);

		//Preenche número celular
		WebElement celular = driver.findElement(By.id("informacoes_de_contato_celular_numero"));
		celular.clear();
		String infCelular = "999909988";
		celular.sendKeys(infCelular);
	}

	@When("^Seto combo de recebimento de mensagem$")
	public void seto_combo_de_recebimento_de_mensagem() throws Throwable {
		WebElement cmbMsg = driver.findElement(By.id("informacoes_de_contato_aceita_receber_sms_de_empresas"));
		cmbMsg.click();
	}

	@Then("^Salvo e alteracoes de contato sao realizadas$")
	public void salvo_e_alteracoes_de_contato_sao_realizadas() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		WebElement salvarIC = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"edit_informacoes_de_contato_\"]/div[3]/button")));
		salvarIC.click();
		
		//evidencia
				File screenPrincipal = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				try {
					// now copy the screenshot to desired location using copyFile //method
					FileUtils.copyFile(screenPrincipal, new File("/evidencias" + salvarIC+"_curriculo.jpeg"));
				}	

				catch (IOException e) {
					System.out.println(e.getMessage());
				}	
		
		System.out.println("Informações de contato salvas");
	}
//--Cenário 5--//
	@Given("^Acessei edicao rede social$")
	public void acessei_edicao_rede_social() throws Throwable {

		Thread.sleep(3000);
		WebElement editarRedeSocial = driver.findElement(By.xpath("//*[@id=\"redes\"]/a"));
		editarRedeSocial.click();
	}

	@When("^Informo site pessoal$")
	public void informo_site_pessoal() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		WebElement blog = wait.until(ExpectedConditions.elementToBeClickable(By.id("sites_pessoal")));
		blog.clear();
		String site = "www.vagas.com.br";
		blog.sendKeys(site);

	}
	
	@Then("^Salvo e alteracoes de rede social sao realizadas$")
	public void salvo_e_alteracoes_de_rede_social_sao_realizadas() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		WebElement salvarRedes = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"edit_sites_63289034\"]/div[3]/button")));
		salvarRedes.click();
		
		//evidencia
		File screenPrincipal = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile //method
			FileUtils.copyFile(screenPrincipal, new File("/evidencias" + salvarRedes+"_curriculo.jpeg"));
		}	

		catch (IOException e) {
			System.out.println(e.getMessage());
		}	

		System.out.println("Informações sobre Redes Sociais salvas");
	}
//--Cenário 6 --//
	
	@Given("^Acessei edicao deficiencia$")
	public void acessei_edicao_deficiencia() throws Throwable {
		Thread.sleep(8000);
		
		WebElement editarDeficiencia = driver.findElement(By.xpath("//*[@id=\"deficiencias\"]/a"));
		editarDeficiencia.click();
	}

	@When("^Seto SIM para deficiencia$")
	public void seto_SIM_para_deficiencia() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		WebElement rbDeficiencia = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("deficiencias_possui_alguma_deficiencia_true")));
		rbDeficiencia.click();
	}

	@When("^Informo tipo deficiencia$")
	public void informo_tipo_deficiencia() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		//Deficiencia já preenchida? Se não, preenche deficiencia:
				if (driver.findElements(By.id("dados_pessoais_documentos_attributes_0_pais_id")) == null) {
					
					//Tipo deficiencia - Auditiva
					WebElement chkbAuditiva = driver.findElement(By.id("deficiencias_possui_deficiencia_auditiva"));
					chkbAuditiva.click();
					
					//Seleciona o nível de deficiência auditiva
					WebElement nivelAuditiva = wait
							.until(ExpectedConditions.elementToBeClickable(By.id("deficiencias_nivel_de_deficiencia_auditiva")));
					Select cmbnivel = new Select(nivelAuditiva);
					cmbnivel.selectByVisibleText("de 71 a 90 dB – Surdez Severa");
					
					//Seleciona detalhamento de deficiencia
					WebElement detalhamentoDeficiencia = wait.until(
							ExpectedConditions.elementToBeClickable(By.id("deficiencias_detalhamento_de_deficiencia_auditiva")));
					Select cmbdetalhamentoDeficiencia = new Select(detalhamentoDeficiencia);
					cmbdetalhamentoDeficiencia.selectByVisibleText("bilateral");
					
					
					//Seleciona Usuário de libras
					WebElement libras = driver.findElement(By.id("deficiencias_usuario_de_libras"));
					libras.click();

					
				}
	}

	@When("^Preencho observacoes referente a deficiencia$")
	public void preencho_observacoes_referente_a_deficiencia() throws Throwable {
		//Preenche observações referente a deficiência
		WebElement observacoesDeficiencia = driver.findElement(By.id("deficiencias_observacoes"));
		observacoesDeficiencia.clear();
		String textoObservacoes = "Boa comunicação comunicação em libras e bom entendimento de leitura labial.";
		observacoesDeficiencia.sendKeys(textoObservacoes);
		System.out.println("Deficiencia incluída");
	}

	@Then("^Salvo e alteracoes de deficiencia sao realizadas$")
	public void salvo_e_alteracoes_de_deficiencia_sao_realizadas() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		
		WebElement salvarDeficiencia = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"edit_deficiencias_63289034\"]/div[3]/button")));
		salvarDeficiencia.click();
		
		//evidencia
				File screenPrincipal = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				try {
					// now copy the screenshot to desired location using copyFile //method
					FileUtils.copyFile(screenPrincipal, new File("/evidencias" + salvarDeficiencia+"_curriculo.jpeg"));
				}	

				catch (IOException e) {
					System.out.println(e.getMessage());
				}	
		
		System.out.println("Informações sobre Deficiência salvas");
	}
	
}
