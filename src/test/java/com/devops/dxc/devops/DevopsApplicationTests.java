package com.devops.dxc.devops;

import com.devops.dxc.devops.model.Dxc;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DevopsApplicationTests {

	@Test
	void TestgetDxc() {

		// ahorro > 135 UF y << 135UF*10
		assertEquals(1000000, new Dxc(5000000,500000).getDxc());

		// ahorro >= 135UF*10*10
		assertEquals(4369405, new Dxc(45000000, 500000).getDxc());

	}

	@Test
	void TestgetSaldoDxc() {

		// saldo 5.000.000 - 1.000.000
		assertEquals(4000000, new Dxc(5000000,500000).getSaldo());

		// saldo: 1.200.000 - 1.000.000
		assertEquals(200000, new Dxc(1200000,500000).getSaldo());

		// saldo: 0 (750.000 - 750.000)
		assertEquals(0, new Dxc(750000,500000).getSaldo());

	}

	@Test
	void TestgetImpuesto() {

		// impuesto 0 (sueldo < 1.500.000)
		assertEquals(0, new Dxc(10000000,1400000).getImpuesto());

		// impuesto 174531 (sueldo entre 1.500.000 y 2.500.000)
		assertEquals(174531, new Dxc(10000000,2000000).getImpuesto());

		// impuesto 364086 (sueldo entre 2.500.000 y 3.000.000)
		assertEquals(364086, new Dxc(10000000,2750000).getImpuesto());

		// impuesto 612396 (sueldo entre 3.000.000 y 4.000.000)
		assertEquals(612396, new Dxc(10000000,3500000).getImpuesto());

		// impuesto 980357 (sueldo entre 4.000.000 y 5.000.000)
		assertEquals(980357, new Dxc(10000000,4500000).getImpuesto());

		// impuesto 1294052 (sueldo entre 5.000.000 y 6.000.000)
		assertEquals(1294052, new Dxc(10000000,5500000).getImpuesto());

		// impuesto 1491042 (sueldo > 6.000.000)
		assertEquals(1491042, new Dxc(10000000,6111222).getImpuesto());
	}


	@Test
	void contextLoads() {
	}

}
