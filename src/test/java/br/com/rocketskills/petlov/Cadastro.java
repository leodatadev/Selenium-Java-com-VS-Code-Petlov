package br.com.rocketskills.petlov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

class PontoDoacao {
	String nome;
	String email;
	String cep;
	Integer numero;
	String complemento;
	String pets;

	public PontoDoacao(String nome, String email, String cep, Integer numero, String complemento, String pets) {
		this.nome = nome;
		this.email = email;
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
		this.pets = pets;

	}

}

class Cadastro {

	private void submeterFormulario(PontoDoacao ponto) {
		// Ação
		$("input[placeholder='Nome do ponto de doação']").setValue(ponto.nome);
		$("input[name=email]").setValue(ponto.email);
		$("input[name=cep]").setValue(ponto.cep);
		$("input[value='Buscar CEP']").click();
		$("input[name=addressNumber]").setValue(ponto.numero.toString());
		$("input[name=addressDetails]").setValue(ponto.complemento);
		$(By.xpath("//span[text()=\"" + ponto.pets + "\"]/..")).click();
		$(".button-register").click();

	}

	@Test
	@DisplayName("Deve poder cadastrar um ponto de doação")
	void cratePoint() {

		// Pré-condição
		PontoDoacao	 ponto = new PontoDoacao(
			"Estação dos pets",
			"leodatadev@pets.com.br",
			"22775040",
			1300,
			"Ao lado do centro comercial",
			"Cachorros"
		); 	

		open("https://petlov.vercel.app/signup");
		$("h1").should(text("Cadastro de ponto de doação"));

		//Ação
		submeterFormulario(ponto);

		// Resultado esperado
		String target = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";
		$("#success-page p").should(text(target));
		
	}
}
