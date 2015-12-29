package test;

import java.util.*;

public class Test {
	public static void mostrar(String p) {
		System.out.println(p);
	}

	public static void mostrar(Object p) {
		System.out.println(p);
	}

	public static void mostrar(String s, Object p) {
		System.out.println(s + p);
	}

	public static void mostrar(String s1, Object[] p) {
		String s = s1 + "  [";
		for (Object o : p) {
			s = s + o;
		}
		s = s + "]";
		System.out.println(s);
	}

	public static <N> void mostrar(String s1, List<N> p) {
		String s = s1 + "  {";
		if (!p.isEmpty())
			s = s + p.get(0);
		if (p.size() > 1) {
			for (N o : p.subList(1, p.size() - 1)) {
				s = s + "," + o;
			}
		}
		s = s + "}";
		System.out.println(s);
	}

}
