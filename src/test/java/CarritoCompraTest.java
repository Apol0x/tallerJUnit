import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.everis.bootcamp.tallerjunit.Articulo;
import com.everis.bootcamp.tallerjunit.CarritoCompraService;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CarritoCompraTest {
	 CarritoCompraService service = new CarritoCompraService();
	
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
}
