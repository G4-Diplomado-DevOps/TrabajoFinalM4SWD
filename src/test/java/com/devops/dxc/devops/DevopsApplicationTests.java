package com.devops.dxc.devops;

import com.devops.dxc.devops.model.Dxc;
import com.devops.dxc.devops.model.Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DevopsApplicationTests {

	@Test
	void TestgetDxc() {

		// ahorro > 150 UF y << 150*10 -> retornar 35 UF!
		assertEquals(35*Util.getUf(), new Dxc(5000000,500000).getDxc());

		// ahorro >= 150UF*10
		assertEquals(150*Util.getUf(), new Dxc(45000000, 500000).getDxc());

		// ahorro < 35UF
		assertEquals(750000, new Dxc(750000, 500000).getDxc());

		// ahorro < 135 UF y > 35UF -> retornar 35 UF!
		assertEquals(35*Util.getUf(), new Dxc(2500000, 500000).getDxc());

	}

	@Test
	void TestgetSaldoDxc() {

		// saldo 45.000.000 - 150UF
		assertEquals(45000000-150*Util.getUf(), new Dxc(45000000,500000).getSaldo());

		// saldo 5.000.000 - 35UF
		assertEquals(5000000-35*Util.getUf(), new Dxc(5000000,500000).getSaldo());

		// saldo: 1.200.000 - 35UF
		assertEquals(1200000-35*Util.getUf(), new Dxc(1200000,500000).getSaldo());

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
