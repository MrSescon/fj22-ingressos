package br.com.caelum.ingresso.model.descontos;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;

public class DescontoTest {
	private Sala eldorado;
	private Filme rogueone;
	private Sessao sessaoDasDez;
	private Lugar a1;
	
	
	@Before
	public void preparaDescontos() {
		this.eldorado = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
		this.rogueone = new Filme("Rogue one", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
		this.sessaoDasDez = new Sessao(LocalTime.parse("10:00:00"), rogueone, eldorado);
		this.a1 = new Lugar("A",1);
		
	}
	
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {
		
		Ingresso ingresso = new Ingresso(sessaoDasDez, TipoDeIngresso.INTEIRO, a1);
		
		BigDecimal precoEsperado = new BigDecimal("32.50");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
	}
	
	@Test
	public void deveConcederDescontoDe30PorcentoParaIngressoClienteDeBancos() {
		
		Ingresso ingresso = new Ingresso(sessaoDasDez, TipoDeIngresso.BANCO, a1);
		
		BigDecimal precoEsperado = new BigDecimal("22.75");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
		
	}
	
	@Test
	public void deveConcederDescontoDe50PorcentoParaIngressoDeEstudante() {
		
		
		Ingresso ingresso = new Ingresso(sessaoDasDez, TipoDeIngresso.ESTUDANTE, a1);
		
		BigDecimal precoEsperado = new BigDecimal("16.25");
		
		Assert.assertEquals(precoEsperado, ingresso.getPreco());
		
	}
}
