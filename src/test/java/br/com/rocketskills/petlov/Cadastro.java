package br.com.rocketskills.petlov;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

class Cadastro {

	@Test
	@DisplayName("Deve poder cadastrar um ponto de doação")
	void cratePoint() {
		

		open("https://petlov.vercel.app/signup");
		$("h1").should(text("Cadastro de ponto de doação"));

		$("input[placeholder='Nome do ponto de doação']").setValue("Estação dos Pets");
		$("input[name=email]").setValue("leodatadev@pets.com.br");
		$("input[name=cep]").setValue("22775040");
		$("input[value='Buscar CEP']").click();
		$("input[name=addressNumber]").setValue("1300");
		$("input[name=addressDetails]").setValue("Ao lado do centro comercial");
		$(By.xpath("//span[text()=\"Cachorros\"]/..")).click();
		$(".button-register").click();

		String target = "Seu ponto de doação foi adicionado com sucesso. Juntos, podemos criar um mundo onde todos os animais recebam o amor e cuidado que merecem.";
		
		$("#success-page p").should(text(target));
		
	}
}
