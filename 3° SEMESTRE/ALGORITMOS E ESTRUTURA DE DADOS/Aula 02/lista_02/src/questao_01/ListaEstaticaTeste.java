package questao_01;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ListaEstaticaTeste {

	@Test
	@DisplayName("Testar método de inclusão de dados na lista")
	void test01() {
		ListaEstatica<Integer> lista = new ListaEstatica<>();
		String esperado = "5,10,15,20";

		lista.inserir(5);
		lista.inserir(10);
		lista.inserir(15);
		lista.inserir(20);

		String resultado = lista.toString();

		assertEquals(esperado, resultado);
	}

	@Test
	@DisplayName("Testar método de obtenção de tamanho da lista")
	void test02() {
		ListaEstatica<Integer> lista = new ListaEstatica<>();
		int tamanhoEsperado = 4;

		lista.inserir(5);
		lista.inserir(10);
		lista.inserir(15);
		lista.inserir(20);

		int resultado = lista.getTamanho();

		assertEquals(tamanhoEsperado, resultado);
	}

	@Test
	@DisplayName("Testar método buscar() com elemento existente")
	void test03() {
		ListaEstatica<Integer> lista = new ListaEstatica();
		int buscarEsperado = 2;

		lista.inserir(5);
		lista.inserir(10);
		lista.inserir(15);
		lista.inserir(20);

		int resultado = lista.buscar(15);

		assertEquals(buscarEsperado, resultado);
	}

	@Test
	@DisplayName("Testar método buscar() com elemento inexistente")
	void test04() {
		ListaEstatica<Integer> lista = new ListaEstatica<>();
		int buscarEsperado = -1;

		lista.inserir(5);
		lista.inserir(10);
		lista.inserir(15);
		lista.inserir(20);

		int resultado = lista.buscar(30);

		assertEquals(buscarEsperado, resultado);
	}

	@Test
	@DisplayName("Testar método retirar()")
	void test05() {
		ListaEstatica<Integer> lista = new ListaEstatica<>();
		String esperado = "5,15,20";
		int tamanhoEsperado = 3;

		lista.inserir(5);
		lista.inserir(10);
		lista.inserir(15);
		lista.inserir(20);
		
		lista.retirar(10);

		String resultado = lista.toString();
		int tamanhoResultado = lista.getTamanho();
		
		assertEquals(esperado, resultado);
		assertEquals(tamanhoEsperado, tamanhoResultado);
	}
	
	@Test
	@DisplayName("Testar inclusão que provoque redimensionamento")
	void test06() {
		ListaEstatica<Integer> lista = new ListaEstatica<>();
		String esperado = "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15";
		int tamanhoEsperado = 15;
		
		lista.inserir(1);
		lista.inserir(2);
		lista.inserir(3);
		lista.inserir(4);
		lista.inserir(5);
		lista.inserir(6);
		lista.inserir(7);
		lista.inserir(8);
		lista.inserir(9);
		lista.inserir(10);
		lista.inserir(11);
		lista.inserir(12);
		lista.inserir(13);
		lista.inserir(14);
		lista.inserir(15);

		String resultado = lista.toString();
		int tamanhoResultado = lista.getTamanho();

		assertEquals(esperado, resultado);
		assertEquals(tamanhoEsperado, tamanhoResultado);
	}
	
	@Test
	@DisplayName("Testar método obterElemento()")
	void test07() {
		ListaEstatica<Integer> lista = new ListaEstatica<>();
		int esperado = 20;
		
		lista.inserir(5);
		lista.inserir(10);
		lista.inserir(15);
		lista.inserir(20);

		int resultado = (int) lista.obterElemento(3);

		assertEquals(esperado, resultado);
	}
	
	@Test
	@DisplayName("Testar lançamento de exceção no método obterElemento()")
	void test08() {
		ListaEstatica<Integer> lista = new ListaEstatica<>();
		
		lista.inserir(5);
		lista.inserir(10);
		lista.inserir(15);
		lista.inserir(20);

//		try {
//			lista.obterElemento(5);
//			fail();
//		} catch (IndexOutOfBoundsException ex) {
//			assertEquals("", ex.getMessage());
//		} catch (Exception e) {
//			fail();
//		}

		// certificar que lança: IndexOutOfBoundsException quando fazer tal coisa...
		assertThrows(IndexOutOfBoundsException.class, () -> {
			lista.obterElemento(5);
		});
	}
	
	@Test
	@DisplayName("Certificar que liberar() remove todos os elementos")
	void test09() {
		ListaEstatica<Integer> lista = new ListaEstatica<>();
		
		lista.inserir(5);
		lista.inserir(10);
		lista.inserir(15);
		lista.inserir(20);
		
		lista.liberar();
		
		assertTrue(lista.estaVazia());
	}

	@Test
	@DisplayName("Testar o método inverter() com quantidade par de dados")
	void test10() {
		ListaEstatica<Integer> lista = new ListaEstatica<>();
		
		String esperado = "20,15,10,5";
		
		lista.inserir(5);
		lista.inserir(10);
		lista.inserir(15);
		lista.inserir(20);
		
		lista.inverter();
		
		String resultado = lista.toString();
		
		assertEquals(esperado, resultado);
	}
	
	@Test
	@DisplayName("Testar o método inverter() com quantidade ímpar de dados")
	void test11() {
		ListaEstatica<Integer> lista = new ListaEstatica<>();
		
		String esperado = "25,20,15,10,5";
		
		lista.inserir(5);
		lista.inserir(10);
		lista.inserir(15);
		lista.inserir(20);
		lista.inserir(25);
		
		lista.inverter();
		
		String resultado = lista.toString();
		
		assertEquals(esperado, resultado);
	}
	
	@Test
	@DisplayName("Testar o método inverter() com lista vazia (Extra meu mesmo)")
	void testExtra() {
		ListaEstatica<Integer> lista = new ListaEstatica<>();
		
		lista.inverter();
		
		assertTrue(lista.estaVazia());
	}
	
}
