
import java.io.*;
import java.util.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import java.util.stream.*;

public class Test {
	static PrintStream orgout = System.out;
	static ByteArrayOutputStream record = new ByteArrayOutputStream();
	static PrintStream bufout = new PrintStream(record);
	static void out(Object o) {
		System.out.println(o);
	}
	static void ln() {
		System.out.println();
	}
	static int hack;
	@org.junit.Test
	public void test01() {
		System.setOut(bufout);
		checkProtectedField(Food.class, String.class, "name");
		checkProtectedField(Food.class, int.class, "price");
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("Food.name has the right type and modifiers\n" +
			"Food.price has the right type and modifiers\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test02() {
		System.setOut(bufout);
		out("Price: " + new Food("Burger", 65).getPrice());
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("Price: 65\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test03() {
		System.setOut(bufout);
		out("Price: " + new Food("Burger", 42).getPrice());
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("Price: 42\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test04() {
		System.setOut(bufout);
		out("Price: " + new Food("Burger", 0).getPrice());
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("Price: 0\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test05() {
		System.setOut(bufout);
		new Food("Burger", 65).display();
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("65 kr Burger\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test06() {
		System.setOut(bufout);
		new Food("Coke", 25).display();
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("25 kr Coke\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test07() {
		System.setOut(bufout);
		checkSubclass(Pizza.class, Food.class);
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("You succesfully created a subclass of Food called Pizza\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test08() {
		System.setOut(bufout);
		checkProtectedGenericField(Pizza.class, "java.util.List<java.lang.String>", "toppings");
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("Pizza.toppings has the right type and modifiers\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test09() {
		System.setOut(bufout);
		out("Pizza with zero toppings price: " + new Pizza().getPrice());
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("Pizza with zero toppings price: 45\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test10() {
		System.setOut(bufout);
		checkMethod(Pizza.class, void.class, "addTopping", String.class);
		checkMethod(Food.class, void.class, "addTopping", String.class);
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("The method addTopping of Pizza has the right signature\n" +
			"Food does not have a method addTopping(String)\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test11() {
		System.setOut(bufout);
		Pizza pizza1 = new Pizza();
		pizza1.addTopping("Mushrooms");
		out("Pizza with one topping price: " + pizza1.getPrice());
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("Pizza with one topping price: 55\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test12() {
		System.setOut(bufout);
		Pizza pizza2 = new Pizza();
		pizza2.addTopping("Pepperoni");
		pizza2.addTopping("Pineapple");
		pizza2.addTopping("Onion");
		out("Pizza with three toppings price: " + pizza2.getPrice());
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("Pizza with three toppings price: 75\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test13() {
		System.setOut(bufout);
		new Pizza().display();
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("45 kr Pizza { }\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test14() {
		System.setOut(bufout);
		Pizza pizza3 = new Pizza();
		pizza3.addTopping("Mushrooms");
		pizza3.display();
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("55 kr Pizza { Mushrooms }\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test15() {
		System.setOut(bufout);
		Pizza pizza4 = new Pizza();
		pizza4.addTopping("Pepperoni");
		pizza4.addTopping("Pineapple");
		pizza4.addTopping("Onion");
		pizza4.display();
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("75 kr Pizza { Pepperoni, Pineapple, Onion }\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test16() {
		System.setOut(bufout);
		Pizza pizza5 = new Pizza();
		pizza5.addTopping("Bacon");
		pizza5.addTopping("Pineapple");
		pizza5.setName("Hawaiian pizza");
		pizza5.display();
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("65 kr Hawaiian pizza { Bacon, Pineapple }\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test17() {
		System.setOut(bufout);
		checkProtectedGenericField(Order.class, "java.util.List<Food>", "ordered");
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("Order.ordered has the right type and modifiers\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test18() {
		System.setOut(bufout);
		Order order1 = new Order();
		out("Total of empty order: " + order1.total());
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("Total of empty order: 0\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test19() {
		System.setOut(bufout);
		Order order2 = new Order();
		order2.addFood(new Pizza());
		out("Total of single pizza order: " + order2.total());
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("Total of single pizza order: 45\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test20() {
		System.setOut(bufout);
		Order order3 = new Order();
		order3.addFood(new Food("Burger", 65));
		order3.addFood(new Food("Coke", 25));
		Pizza pizza6 = new Pizza();
		pizza6.addTopping("Pepperoni");
		pizza6.addTopping("Pineapple");
		pizza6.addTopping("Onion");
		order3.addFood(pizza6);
		out("Total of big order: " + order3.total());
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("Total of big order: 165\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test21() {
		System.setOut(bufout);
		Order order4 = new Order();
		order4.display();
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("0 kr TOTAL\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test22() {
		System.setOut(bufout);
		Order order5 = new Order();
		order5.addFood(new Pizza());
		order5.display();
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("45 kr Pizza { }\n" +
			"45 kr TOTAL\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test23() {
		System.setOut(bufout);
		Order order6 = new Order();
		order6.addFood(new Food("Burger", 65));
		order6.addFood(new Food("Coke", 25));
		Pizza pizza7 = new Pizza();
		pizza7.addTopping("Pepperoni");
		pizza7.addTopping("Pineapple");
		pizza7.addTopping("Onion");
		order6.addFood(pizza7);
		order6.display();
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("65 kr Burger\n" +
			"25 kr Coke\n" +
			"75 kr Pizza { Pepperoni, Pineapple, Onion }\n" +
			"165 kr TOTAL\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test24() {
		System.setOut(bufout);
		out("Payment successful: " + new Order().payWith(i->{hack=i;return true;}));
		out("Withdrawn: " + hack);
		ln();	
		System.setOut(orgout);
		org.junit.Assert.assertEquals("Payment successful: true\n" +
			"Withdrawn: 0\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test25() {
		System.setOut(bufout);
		Order order7 = new Order();
		order7.addFood(new Food("Foo", 87));
		out("Payment successful: " + order7.payWith(i->{hack=i;return true;}));
		out("Withdrawn: " + hack);
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("Payment successful: true\n" +
			"Withdrawn: 87\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test26() {
		System.setOut(bufout);
		Order order8 = new Order();
		order8.addFood(new Food("Foo", 22));
		order8.addFood(new Food("Foo", 20));
		out("Payment successful: " + order8.payWith(i->{hack=i;return true;}));
		out("Withdrawn: " + hack);
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("Payment successful: true\n" +
			"Withdrawn: 42\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test27() {
		System.setOut(bufout);
		Order order9 = new Order();
		order9.addFood(new Food("Foo", 78));
		out("Payment failed: " + !order9.payWith(i->false));
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("ERROR: Payment failed\n" +
			"Payment failed: true\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test28() {
		System.setOut(bufout);
		Order order10 = new Order();
		order10.addFood(new Food("Foo", 78));
		out("Payment failed: " + !order10.payWith(i->false));
		ln();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("ERROR: Payment failed\n" +
			"Payment failed: true\n" +
			"\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	@org.junit.Test
	public void test29() {
		System.setOut(bufout);
		out("Big random test");
		Order order11 = new Order();
		Random rnd = new Random(0);
		for (int i = 0 ; i < 100 ; i++) {
			order11.addFood(new Food("Item" + rnd.nextInt(43), rnd.nextInt(90) + 10));
		}
		order11.display();
		System.setOut(orgout);
		org.junit.Assert.assertEquals("Big random test\n" +
			"98 kr Item1\n" +
			"57 kr Item37\n" +
			"93 kr Item5\n" +
			"31 kr Item14\n" +
			"84 kr Item34\n" +
			"27 kr Item17\n" +
			"92 kr Item42\n" +
			"24 kr Item3\n" +
			"25 kr Item16\n" +
			"30 kr Item30\n" +
			"28 kr Item42\n" +
			"87 kr Item22\n" +
			"20 kr Item23\n" +
			"62 kr Item25\n" +
			"63 kr Item36\n" +
			"75 kr Item2\n" +
			"57 kr Item20\n" +
			"12 kr Item40\n" +
			"93 kr Item15\n" +
			"25 kr Item26\n" +
			"35 kr Item37\n" +
			"30 kr Item8\n" +
			"31 kr Item41\n" +
			"26 kr Item17\n" +
			"52 kr Item37\n" +
			"67 kr Item1\n" +
			"76 kr Item9\n" +
			"26 kr Item4\n" +
			"27 kr Item29\n" +
			"17 kr Item6\n" +
			"98 kr Item37\n" +
			"88 kr Item36\n" +
			"97 kr Item7\n" +
			"18 kr Item17\n" +
			"28 kr Item1\n" +
			"32 kr Item7\n" +
			"21 kr Item24\n" +
			"70 kr Item23\n" +
			"82 kr Item3\n" +
			"20 kr Item1\n" +
			"43 kr Item25\n" +
			"59 kr Item27\n" +
			"82 kr Item4\n" +
			"53 kr Item14\n" +
			"71 kr Item17\n" +
			"80 kr Item1\n" +
			"94 kr Item24\n" +
			"23 kr Item25\n" +
			"22 kr Item27\n" +
			"91 kr Item10\n" +
			"55 kr Item35\n" +
			"33 kr Item5\n" +
			"77 kr Item4\n" +
			"97 kr Item19\n" +
			"23 kr Item1\n" +
			"75 kr Item5\n" +
			"71 kr Item9\n" +
			"17 kr Item17\n" +
			"11 kr Item23\n" +
			"61 kr Item17\n" +
			"84 kr Item36\n" +
			"65 kr Item35\n" +
			"24 kr Item19\n" +
			"42 kr Item13\n" +
			"40 kr Item34\n" +
			"33 kr Item34\n" +
			"15 kr Item11\n" +
			"50 kr Item16\n" +
			"42 kr Item25\n" +
			"19 kr Item12\n" +
			"21 kr Item40\n" +
			"93 kr Item10\n" +
			"98 kr Item7\n" +
			"51 kr Item20\n" +
			"21 kr Item20\n" +
			"83 kr Item25\n" +
			"87 kr Item31\n" +
			"88 kr Item16\n" +
			"48 kr Item41\n" +
			"25 kr Item24\n" +
			"55 kr Item24\n" +
			"69 kr Item39\n" +
			"45 kr Item18\n" +
			"50 kr Item22\n" +
			"26 kr Item21\n" +
			"32 kr Item10\n" +
			"91 kr Item33\n" +
			"29 kr Item20\n" +
			"41 kr Item19\n" +
			"11 kr Item28\n" +
			"61 kr Item14\n" +
			"81 kr Item24\n" +
			"33 kr Item20\n" +
			"12 kr Item34\n" +
			"83 kr Item19\n" +
			"95 kr Item11\n" +
			"51 kr Item6\n" +
			"72 kr Item8\n" +
			"74 kr Item19\n" +
			"68 kr Item24\n" +
			"5270 kr TOTAL\n", record.toString().replaceAll("\r",""));
		record.reset();
	}
	public static void main(String[] args) {
		org.junit.runner.Result result = org.junit.runner.JUnitCore.runClasses(Test.class);
		for (org.junit.runner.notification.Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
			break;
		}
		System.out.printf("Tests run: %d,  Failures: %d\n", result.getRunCount(), result.getFailureCount());
	}

	static String getTypesAsString(Class<?> ... parameters) {
		StringBuilder sb = new StringBuilder("(");
		for (Class<?> c : parameters) {
			if (sb.length() > 1) sb.append(", ");
			sb.append(c.getSimpleName());
		}
		return sb.append(")").toString();
	}

	static void checkProtectedField(Class<?> cls, Class<?> type, String name) {
		try {
			Field f = cls.getDeclaredField(name);
			if (f.getType().equals(type)) {
				if (Modifier.isProtected(f.getModifiers())) {
					out(cls.getName()+"."+name + " has the right type and modifiers");
				} else {
					out(cls.getName()+"."+name + " is not protected");
				}
			} else {
				out(cls.getName()+"."+name + " has the wrong type");
			}
		} catch (NoSuchFieldException e) {
			out(cls.getName() + " does not have a field called " + name);
		}
	}

	static void checkPrivateField(Class<?> cls, Class<?> type, String name) {
		try {
			Field f = cls.getDeclaredField(name);
			if (f.getType().equals(type)) {
				if (Modifier.isPrivate(f.getModifiers())) {
					out(cls.getName()+"."+name + " has the right type and modifiers");
				} else {
					out(cls.getName()+"."+name + " is not private");
				}
			} else {
				out(cls.getName()+"."+name + " has the wrong type");
			}
		} catch (NoSuchFieldException e) {
			out(cls.getName() + " does not have a field called " + name);
		}
	}

	static void checkProtectedGenericField(Class<?> cls, String signature, String name) {
		try {
			Field f = cls.getDeclaredField(name);
			if (f.getGenericType().getTypeName().equals(signature)) {
				if (Modifier.isProtected(f.getModifiers())) {
					out(cls.getName()+"."+name + " has the right type and modifiers");
				} else {
					out(cls.getName()+"."+name + " is not protected");
				}
			} else {
				out(cls.getName()+"."+name + " has the wrong type");
			}
		} catch (NoSuchFieldException e) {
			out(cls.getName() + " does not have a field called " + name);
		}
	}

	static void checkSubclass(Class<?> sub, Class<?> cls) {
		if (sub.getSuperclass().equals(cls)) {
			out("You succesfully created a subclass of " + cls.getName() + " called " + sub.getName());
		} else {
			out(sub.getName() + " doesn't have the right super class");
		}
	}

	static void checkInterface(Class<?> c) {
		if (Modifier.isInterface(c.getModifiers())) {
			out(c.getName() + " is an interface");
		} else {
			out(c.getName() + " is not an interface");
		}
	}

	static void checkContructor(Class<?> c, Class<?> ... parameters) {
		try {
			Constructor<?> constructor = c.getDeclaredConstructor(parameters);
			if (constructor.getModifiers() != Modifier.PUBLIC) {
				out("The constructor of " + c.getName() + " should be public!");
			} else {
				out("The constructor of " + c.getName() + " has the right signature.");
			}
		} catch (NoSuchMethodException e) {
			out(c.getName() + " does not have the constructor " + c.getName() + getTypesAsString(parameters));
		}
	}

	static void checkMethod(Class<?> c, Class<?> rtype, String name, Class<?> ... parameters) {
		try {
			Method m = c.getMethod(name, parameters);
			if (!Modifier.isPublic(m.getModifiers())) {
				out("The method " + name + " of " + c.getName() + " should be public!");
			} else if (!m.getReturnType().equals(rtype)) {
				out("The method " + name + " of " + c.getName() + " has the wrong return type.");
			} else {
				out("The method " + name + " of " + c.getName() + " has the right signature");
			}
		} catch (NoSuchMethodException e) {
			out(c.getName() + " does not have a method " + name + getTypesAsString(parameters));
		}
	}

	static void checkMethodException(Class<?> c, String name, Class<?> exception, Class<?> ... parameters) {
		try {
			Method m = c.getMethod(name, parameters);
			boolean found = false;
			for (Class<?> exc : m.getExceptionTypes())
				if (exc.equals(exception)) {
					out(c.getName() +"."+name + " declares throwing " + exception.getName());
					found = true;
				}
			if (!found) out(c.getName() +"."+name + " does not declare the relevant exception");
		} catch (NoSuchMethodException e) {
			out(c.getName() + " does not have a method " + name + getTypesAsString(parameters));
		}
	}

	static void checkCheckedException(Class<?> c) {
		try {
			Object o = c.getDeclaredConstructor(int.class, String.class).newInstance(42,"foo");
			if (o instanceof Exception) {
				if (o instanceof RuntimeException) {
					out(c.getName() + " is an unchecked exception");
				} else {
					out(c.getName() + " is a checked exception");
				}
			} else {
				out(c.getName() + " isn't an exception");
			}
		} catch (Exception e) {
			out(c.getName() + " does not have the right constructor");
			e.printStackTrace();
		}
	}
}

