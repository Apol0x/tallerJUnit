import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;

import com.everis.bootcamp.tallerjunit.Articulo;
import com.everis.bootcamp.tallerjunit.BaseDeDatosService;
import com.everis.bootcamp.tallerjunit.CarritoCompraService;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarritoCompraServiceTest {
	
	CarritoCompraService service;
	private static CarritoCompraService service1;
	private static BaseDeDatosService mock; 
	
	@Before
	public void setUp(){
		System.out.println("Inicializamos el servicio");
		service = new CarritoCompraService();
		mock = Mockito.mock(BaseDeDatosService.class);
		service.setBbddService(mock);
	}
	
	
	
	
	@BeforeClass
	public static void creaCarrito() {
		service1=new CarritoCompraService();
	}
	
	
	
	@Test
	public void ejercicio1() {
		Mockito.when(mock.findArticuloById(1)).thenReturn(new Articulo("Ejemplo",10.0));
		Double result = service.aplicarDescuento(1, 50.0);
		System.out.println("El precio definitivo del articulo tras el descuento: " + result);
		
		Assert.assertEquals(result,new Double(5.0));
		Mockito.verify(mock, Mockito.times(1)).findArticuloById(1);
	}
	
	
	@Test (expected= Exception.class) //Exception.class va a capturar todas las excepciones. Es el padre de las excepciones.
	public void ejercicio2() {
		Mockito.when(mock.findArticuloById(1)).thenThrow(Exception.class);
		Double result = service.aplicarDescuento(1, 12.0);
		System.out.println("El precio definitivo del articulo tras el descuento: " + result);
	}
	
	
//	@Test
//	public void reto() {
//		//Articulo a=new Articulo("Articulo", 10.0);
//		Mockito.when(mock.insertArticulo(null)).thenReturn(null);
//		//int id= mock.insertArticulo(a);
//	}
	
	
	@Test
	public void addArticuloTest() {
		service=new CarritoCompraService();
		Articulo a=new Articulo();
		service.addArticulo(a);
		assertTrue(service.getArticulos().size()>0);
	}
	
	/*
	 *  @Test
	public void testAddArticulo() {
		System.out.println("Probando addArticulo");
		assertTrue("La lista no tiene elementos", service.getArticulos().isEmpty());
		service.addArticulo(new Articulo("Articulo 1", 10d));
		assertFalse(service.getArticulos().isEmpty());
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
	 * */
	
	@Test
	public void limpiarCestaTest() {
		service=new CarritoCompraService();
		Articulo a=new Articulo();
		service.addArticulo(a);
		service.limpiarCesta();
		assertTrue(service.getArticulos().isEmpty());
	}
	
	
	/*
	 * @Test
	public void testTotalPrice() {
		System.out.println("Probando totalPrice");
		service.addArticulo(new Articulo("Articdulo 1", 10d));
		service.addArticulo(new Articulo("Articulo 2", 20d));
		service.addArticulo(new Articulo("Articulo 3", 30d));
		service.addArticulo(new Articulo("Articulo 4", 40d));
		assertEquals(service.totalPrice(), new Double(100));
	}
	 * */
	
	@Test
	public void totalPriceTest() {
		service=new CarritoCompraService();
		Articulo a=new Articulo();
		a.setPrecio(4.0);
		Articulo b=new Articulo();
		b.setPrecio(3.0);
		service.addArticulo(a);
		service.addArticulo(b);
		assertEquals(service.totalPrice(),new Double(7));
	}
	
	/*
	 * @Test
	public void testCalculaDescuento() {
		System.out.println("Probando calculaDescuento");
		assertEquals(service.calculadorDescuento(100, 10d), new Double(90));
	}
	 * 
	 * */
	
	@Test
	public void calculadorDescuentoTest() {
		service=new CarritoCompraService();
		double descuento=50;
		double precio=100;
		assertEquals(service.calculadorDescuento(precio, descuento),new Double(50));
	}
	
	@Test
	public void reto() {
		List<Articulo> mockedArti = mock(Articulo.class);
	}


}

