import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import com.everis.bootcamp.tallerjunit.Articulo;
import com.everis.bootcamp.tallerjunit.BaseDeDatosService;
import com.everis.bootcamp.tallerjunit.CarritoCompraService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarritoCompraTest {
	 CarritoCompraService service = new CarritoCompraService();
	//MOCK
	BaseDeDatosService mock;
	
	@Before
	public void beforeClass() {
		System.out.println("Before Class");
		service = new CarritoCompraService();
	}
	
	@After
	public void afterClass() {
		System.out.println("After class");
	}

	@After
	public void after() {
		System.out.println("after");
	}
	
	@Test
	public void addArticuloTest() {
		System.out.println("Probando addArticulo");
		assertTrue(service.getArticulos().isEmpty());
		service.addArticulo(new Articulo("Articulo 1", 10d));
	}
	
	@Test
	public void testTotalPrice(){
		System.out.println("Probando totalPrice");
		service.addArticulo(new Articulo("Articulo 1", 10d));
		service.addArticulo(new Articulo("Articulo 2", 20d));
		service.addArticulo(new Articulo("Articulo 3", 30d));
		service.addArticulo(new Articulo("Articulo 4", 40d));
		assertEquals(service.totalPrice(), new Double(100));
	}
	
	@Test
	public void testCalculaDescuento(){
		System.out.println("Probando calculaDescuento");
		assertEquals(service.calculadorDescuento(100, 10d), new Double(90));
	}
	
	@Test
	public void testLimpiarCestaCompra() {
		System.out.println("Probando llimpiarCesta");
		assertTrue(service.getArticulos().isEmpty());
		service.addArticulo(new Articulo("Articulo 1", 10d));
		assertFalse(service.getArticulos().isEmpty());
		service.limpiarCesta();
		assertTrue(service.getArticulos().isEmpty());
	}
	
	 
			
	@Before
	public void setUp(){
		System.out.println("Inicializamos el servicio");
		service = new CarritoCompraService();
		mock = Mockito.mock(BaseDeDatosService.class);
		service.setBbddService(mock);
	}
	
	
	@Test
	public void ejercicio1() {
		Mockito.when(mock.findArticuloById(1)).thenReturn(new Articulo("MI MOCK", 10.0));
		Double result = service.aplicarDescuento(1, 12.0);
		System.out.println("El precio definitivo del articulo tras el descuento: " + result);
	}
	
	@Test(expected = Exception.class)
	public void ejercicio2() {
		Mockito.when(mock.findArticuloById(1)).thenThrow(Exception.class);
		Double result = service.aplicarDescuento(1, 12.0);
		System.out.println("El precio definitivo del articulo tras el descuento: " + result);
	}
	
	@Test
	public void ejercicio3() {
		//Stubbing (definir comportamiento)
		Mockito.when(mock.findArticuloById(Mockito.eq(1))).thenReturn(new Articulo("ARTICULO", 10.0));
		// Invocar al modulo de codigo que quiero probar
		Double result = service.aplicarDescuento(1, 10.0);
		System.out.println("El precio definitivo del articulo tras el descuento: " + result);
		Assert.assertNotNull(result);
		Assert.assertEquals(new Double(9.0), result);
		Mockito.verify(mock, Mockito.times(1)).findArticuloById(Mockito.eq(1));
	}

}
