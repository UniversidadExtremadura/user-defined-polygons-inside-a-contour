// UNIVERSITY OF EXTREMADURA
// MEDIA ENGINEERING GROUP
// CONTACT: andresc@unex.es

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Collections;


public class holes {
	static ArrayList<Integer> point1 = new ArrayList<Integer>(Arrays.asList(2,6));
	static ArrayList<Integer> point2 = new ArrayList<Integer>(Arrays.asList(3,6));
	static ArrayList<Integer> point3 = new ArrayList<Integer>(Arrays.asList(4,5));
	static ArrayList<Integer> point4 = new ArrayList<Integer>(Arrays.asList(4,4));
	static ArrayList<Integer> point5 = new ArrayList<Integer>(Arrays.asList(5,3));
	static ArrayList<Integer> point6 = new ArrayList<Integer>(Arrays.asList(5,2));
	static ArrayList<Integer> point7 = new ArrayList<Integer>(Arrays.asList(4,0));
	static ArrayList<Integer> point8 = new ArrayList<Integer>(Arrays.asList(3,1));
	static ArrayList<Integer> point9 = new ArrayList<Integer>(Arrays.asList(2,0));
	static ArrayList<Integer> point10 = new ArrayList<Integer>(Arrays.asList(1,2));
	static ArrayList<Integer> point11 = new ArrayList<Integer>(Arrays.asList(0,2));
	static ArrayList<Integer> point12 = new ArrayList<Integer>(Arrays.asList(1,3));
	static ArrayList<Integer> point13 = new ArrayList<Integer>(Arrays.asList(0,4));
	static ArrayList<Integer> point14 = new ArrayList<Integer>(Arrays.asList(1,5));
	static ArrayList<Integer> point15 = new ArrayList<Integer>(Arrays.asList(3,5));
	static ArrayList<Integer> point16 = new ArrayList<Integer>(Arrays.asList(3,3));
	static ArrayList<Integer> point17 = new ArrayList<Integer>(Arrays.asList(2,2));
	static ArrayList<Integer> point18 = new ArrayList<Integer>(Arrays.asList(2,1));
	static ArrayList<Integer> point19 = new ArrayList<Integer>(Arrays.asList(2,5));
	static ArrayList<Integer> point20 = new ArrayList<Integer>(Arrays.asList(3,4));
	static ArrayList<Integer> point21 = new ArrayList<Integer>(Arrays.asList(2,3));
	static ArrayList<Integer> point22 = new ArrayList<Integer>(Arrays.asList(1,4));
	static ArrayList<Integer> point23 = new ArrayList<Integer>(Arrays.asList(4,3));
	static ArrayList<Integer> point24 = new ArrayList<Integer>(Arrays.asList(4,2));
	static ArrayList<Integer> point25 = new ArrayList<Integer>(Arrays.asList(4,1));
	static ArrayList<Integer> point26 = new ArrayList<Integer>(Arrays.asList(3,2));

	static ArrayList<ArrayList<Integer>> points = new ArrayList<ArrayList<Integer>>() {
		{
			add(point1);
			add(point2);
			add(point3);
			add(point4);
			add(point5);
			add(point6);
			add(point7);
			add(point8);
			add(point9);
			add(point10);
			add(point11);
			add(point12);
			add(point13);
			add(point14);
			add(point15);
			add(point16);
			add(point17);
			add(point18);
			add(point19);
			add(point20);
			add(point21);
			add(point22);
			add(point23);
			add(point24);
			add(point25);
			add(point26);
		}
	};

	static ArrayList<Integer> polygon = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14));
	static ArrayList<Integer> holes1 = new ArrayList<Integer>(Arrays.asList(19,20,21,22));
	static ArrayList<Integer> holes2 = new ArrayList<Integer>(Arrays.asList(23,24,25,26));
	static ArrayList<ArrayList<Integer>> holes = new ArrayList<ArrayList<Integer>>() {
		{
			add(holes1);
			add(holes2);
		}
	};

	public static void main(String[] args) {
		long start;
		long end;
		double time;

		int nodos= 26;

		start = System.currentTimeMillis();
		ArrayList<ArrayList<Integer>> matrix;
		matrix = matrix(points);
		System.out.println(matrix);
		end = System.currentTimeMillis();
		time = (double) ((end - start));
		System.out.println("Matrix = " + time);
		System.out.println("---------------------");

		start = System.currentTimeMillis();
		System.out.println("TRIANGLE = " + polygons(nodos, 2, matrix, 0));
		end = System.currentTimeMillis();
		time = (double) ((end - start));
		System.out.println("Tiempo = " + time/1000 + "seg");
		System.out.println("---------------------");

		start = System.currentTimeMillis();
		System.out.println("QUADRILATERAL = " + polygons(nodos, 3, matrix, 0));
		end = System.currentTimeMillis();
		time = (double) ((end - start));
		System.out.println("Tiempo = " + time/1000 + "seg");
		System.out.println("---------------------");

		start = System.currentTimeMillis();
		System.out.println("PENTAGON = " + polygons(nodos, 4, matrix, 0));
		end = System.currentTimeMillis();
		time = (double) ((end - start));
		System.out.println("Tiempo = " + time/1000 + "seg");
		System.out.println("---------------------");

		start = System.currentTimeMillis();
		System.out.println("HEXAGON = " + polygons(nodos, 5, matrix, 0));
		end = System.currentTimeMillis();
		time = (double) ((end - start));
		System.out.println("Tiempo = " + time/1000 + "seg");
		System.out.println("---------------------");

		start = System.currentTimeMillis();
		System.out.println("HEPTAGON = " + polygons(nodos, 6, matrix, 0));
		end = System.currentTimeMillis();
		time = (double) ((end - start));
		System.out.println("Tiempo = " + time/1000 + "seg");
		System.out.println("---------------------");

		start = System.currentTimeMillis();
		System.out.println("OCTAGON = " + polygons(nodos, 7, matrix, 0));
		end = System.currentTimeMillis();
		time = (double) ((end - start));
		System.out.println("Tiempo = " + time/1000 + "seg");
		System.out.println("---------------------");
	}

	static int direction(ArrayList<Integer> p, ArrayList<Integer> q, ArrayList<Integer> r) {
		int dire;
		dire = (q.get(0) - p.get(0)) * (r.get(1) - p.get(1)) - (r.get(0) - p.get(0)) * (q.get(1) - p.get(1));
		return dire;
	}

	static boolean alignedp(ArrayList<Integer> p, ArrayList<Integer> q, ArrayList<Integer> r) {
		boolean alinp;
		alinp = Math.min(p.get(0), q.get(0)) <= r.get(0) && r.get(0) <= Math.max(p.get(0), q.get(0))
				&& Math.min(p.get(1), q.get(1)) <= r.get(1) && r.get(1) <= Math.max(p.get(1), q.get(1));
		return alinp;
	}

	static double GetAngleABCbig( ArrayList<BigDecimal> uno,ArrayList<BigDecimal>tres , ArrayList<Integer> dos){
		float uno0,uno1,tres0,tres1;
		uno0 = uno.get(0).floatValue();
		uno1 = uno.get(1).floatValue();
		tres0 = tres.get(0).floatValue();
		tres1 = tres.get(1).floatValue();
		float ab0,ab1,cb0,cb1;
		ab0 = dos.get(0) - uno0;
		ab1 = dos.get(1) - uno1;
		cb0 = dos.get(0) - tres0;
		cb1 = dos.get(1) - tres1;
		double dot = (ab0 * cb0 + ab1 * cb1); // dot product
		double cross = (ab0 *cb1 - ab1 * cb0); // cross product
		return Math.atan2(cross, dot);
	}

	static double angle(ArrayList<BigDecimal> u,ArrayList<BigDecimal> v) {
		ArrayList<Integer> p3 = new ArrayList<>(Arrays.asList(0, 0));
		double angle = 0;
		float u0,u1,v0,v1;
		u0 = u.get(0).floatValue();
		u1 = u.get(1).floatValue();
		v0 = v.get(0).floatValue();
		v1 = v.get(1).floatValue();
		if(u0*u0+u1*u1 != 0 && v0*v0+v1*v1 != 0) {
			angle = GetAngleABCbig(u, v, p3);
		}
		return angle;
	}

	static float directionBig(ArrayList<Integer> p, ArrayList<Integer> q, ArrayList<BigDecimal> r) {
		float dire;
		float r0 = r.get(0).floatValue();
		float r1 = r.get(1).floatValue();
		dire = (q.get(0) - p.get(0)) * (r1 - p.get(1)) - (r0 - p.get(0)) * (q.get(1) - p.get(1));
		return dire;
	}

	static boolean alignedpBig(ArrayList<Integer> p, ArrayList<Integer> q, ArrayList<BigDecimal> r) {
		boolean alinp;
		alinp = Math.min(p.get(0), q.get(0)) <= r.get(0).floatValue() && r.get(0).floatValue() <= Math.max(p.get(0), q.get(0))
				&& Math.min(p.get(1), q.get(1)) <= r.get(1).floatValue() && r.get(1).floatValue() <= Math.max(p.get(1), q.get(1));
		return alinp;
	}

	static boolean alignedpolBig(ArrayList<ArrayList<Integer>> punt,ArrayList<BigDecimal> p, ArrayList<Integer> polygon) {
		boolean alin = false;
		int temp1, temp2;
		ArrayList<Integer> lad = new ArrayList<>(polygon);
		lad.add(polygon.get(0));
		for (int i = 0; i <lad.size()-1; i++) {
			temp1=lad.get(i)-1;
			temp2=lad.get(i+1)-1;
			if(directionBig(punt.get(temp1),punt.get(temp2),p)==0 && alignedpBig(punt.get(temp1), punt.get(temp2), p)) {
				alin = true;
				break;
			}
		}
		return alin;
	}

	static boolean PointIn(ArrayList<ArrayList<Integer>> punt,ArrayList<BigDecimal> p,ArrayList<Integer> polygon) {
		boolean point;
		ArrayList<Integer> temp1, temp2;
		float pd0 = p.get(0).floatValue();
		float pd1 = p.get(1).floatValue();
		float valor1, valor2, valor3, valor4;
		ArrayList<BigDecimal> p1, p2;

		if(alignedpolBig(punt, p, polygon)) {
			point = true;
		}else {
			point = false;
			double suma = 0;
			for (int i = 0; i < polygon.size()-1; i++) {
				temp1 = points.get(polygon.get(i)-1);
				temp2 = points.get(polygon.get(i+1)-1);
				valor1 = temp1.get(0)-pd0;
				valor2 = temp1.get(1)-pd1;
				valor3 = temp2.get(0)-pd0;
				valor4 = temp2.get(1)-pd1;
				p1 = new ArrayList<>(Arrays.asList(new BigDecimal(valor1), new BigDecimal(valor2)));
				p2 = new ArrayList<>(Arrays.asList(new BigDecimal(valor3), new BigDecimal(valor4)));
				suma = suma + angle(p1,p2);
			}
			temp1 = points.get(polygon.get(polygon.size()-1)-1);
			temp2 = points.get(polygon.get(0)-1);
			valor1 = temp1.get(0)-pd0;
			valor2 = temp1.get(1)-pd1;
			valor3 = temp2.get(0)-pd0;
			valor4 = temp2.get(1)-pd1;
			p1 = new ArrayList<>(Arrays.asList(new BigDecimal(valor1), new BigDecimal(valor2)));
			p2 = new ArrayList<>(Arrays.asList(new BigDecimal(valor3), new BigDecimal(valor4)));
			suma = suma + angle(p1,p2);
			if(Math.abs(suma) >= Math.PI) {
				point = true;
			}
		}
		return point;
	}

	static boolean intersection(ArrayList<ArrayList<Integer>> l1, ArrayList<ArrayList<Integer>> l2) {
		boolean inter = false;
		ArrayList<Integer> p1 = new ArrayList<>(l1.get(0));
		ArrayList<Integer> p2 = new ArrayList<>(l1.get(1));
		ArrayList<Integer> p3 = new ArrayList<>(l2.get(0));
		ArrayList<Integer> p4 = new ArrayList<>(l2.get(1));
		int d1 = direction(p3, p4, p1);
		int d2 = direction(p3, p4, p2);
		int d3 = direction(p1, p2, p3);
		int d4 = direction(p1, p2, p4);
		if ((!p1.get(0).equals(p3.get(0)) || !p1.get(1).equals(p3.get(1))) &&
				(!p1.get(0).equals(p4.get(0)) || !p1.get(1).equals(p4.get(1))) &&
				(!p2.get(0).equals(p3.get(0)) || !p2.get(1).equals(p3.get(1))) &&
				(!p2.get(0).equals(p4.get(0)) || !p2.get(1).equals(p4.get(1)))) {
			if (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) && ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0))) {
				inter = true;
			} else {
				if (d1 == 0 && alignedp(p3, p4, p1) || d2 == 0 && alignedp(p3, p4, p2) || d3 == 0 && alignedp(p1, p2, p3) || d4 == 0 && alignedp(p1, p2, p4) ) {
					inter = true;
				}
			}
		}
		return inter;
	}

	static ArrayList<BigDecimal> intersectionp(ArrayList<ArrayList<Integer>> l1, ArrayList<ArrayList<Integer>> l2) {
		ArrayList<Integer> u1 = new ArrayList<>();
		ArrayList<Integer> u2 = new ArrayList<>();
		ArrayList<BigDecimal> p = new ArrayList<>();
		float a, b, c, d, e, f;
		ArrayList<Integer> temp1, temp2, temp3, temp4;
		temp1 = l1.get(1);
		temp2 = l1.get(0);
		temp3 = l2.get(1);
		temp4 = l2.get(0);
		u1.add(temp1.get(0)-temp2.get(0));
		u1.add(temp1.get(1)-temp2.get(1));
		u2.add(temp3.get(0)-temp4.get(0));
		u2.add(temp3.get(1)-temp4.get(1));
		boolean coinc = u1.get(0) * u2.get(1) == u1.get(1) * u2.get(0);
		if(!coinc) {
			if(intersection(l1, l2)) {
				a = temp1.get(1)-temp2.get(1);
				b = temp2.get(0)-temp1.get(0);
				c = (temp2.get(0)*temp1.get(1))-(temp1.get(0)*temp2.get(1));
				d = temp3.get(1)-temp4.get(1);
				e = temp4.get(0)-temp3.get(0);
				f = (temp4.get(0)*temp3.get(1))-(temp3.get(0)*temp4.get(1));
				if(temp2.get(0).equals(temp1.get(0))) {
					p.add(new BigDecimal(c/a));
					p.add(BigDecimal.valueOf((a * f - c * d) / (a * e)));
				}else {
					if(temp2.get(1).equals(temp1.get(1))) {
						p.add(BigDecimal.valueOf((b * f - c * e) / (b * d)));
						p.add(new BigDecimal(c/b));
					}else {
						p.add(BigDecimal.valueOf((b * f - c * e) / (b * d - a * e)));
						p.add(BigDecimal.valueOf((a * f - c * d) / (a * e - b * d)));
					}
				}
			}
		}else {
			p.add(new BigDecimal(temp4.get(0)));
			p.add(new BigDecimal(temp4.get(1)));
		}
		return p;
	}

	static boolean intersectionpoly(ArrayList<ArrayList<Integer>> l, ArrayList<ArrayList<Integer>> lado, ArrayList<Integer> poly, int n) {
		ArrayList<BigDecimal> p;
		ArrayList<BigDecimal> m1 = new ArrayList<>();
		ArrayList<BigDecimal> m2 = new ArrayList<>();
		BigDecimal a, b, c;
		boolean int2 = false;
		BigDecimal eps = BigDecimal.valueOf(1 / 100000.0);
		ArrayList<Integer> temp1, temp2;
		BigDecimal temp3, temp4;
		temp1 = l.get(0);
		temp2 = l.get(1);
		if(intersection(l, lado)) {
			p = intersectionp(l, lado);
			temp3 = p.get(0);
			temp4 = p.get(1);
			if(temp1.get(0).equals(temp2.get(0))) {
				m1.add(new BigDecimal(temp1.get(0)));
				m1.add(BigDecimal.valueOf(temp4.floatValue() - eps.floatValue()));
				m2.add(new BigDecimal(temp1.get(0)));
				m2.add(BigDecimal.valueOf(temp4.floatValue() + eps.floatValue()));
			}else {
				if(temp1.get(1).equals(temp2.get(1))) {
					m1.add(BigDecimal.valueOf(temp3.floatValue() - eps.floatValue()));
					m1.add(new BigDecimal(temp1.get(1)));
					m2.add(BigDecimal.valueOf(temp3.floatValue() + eps.floatValue()));
					m2.add(new BigDecimal(temp1.get(1)));
				}else {
					a = new BigDecimal(temp2.get(1)-temp1.get(1));
					b = new BigDecimal(temp1.get(0)-temp2.get(0));
					c = new BigDecimal((temp1.get(0)*temp2.get(1))-(temp2.get(0)*temp1.get(1)));
					m1.add(BigDecimal.valueOf(temp3.floatValue() - eps.floatValue()));
					BigDecimal aux = BigDecimal.valueOf(temp3.floatValue() - eps.floatValue());
					BigDecimal aux2 = a.multiply(aux);
					BigDecimal aux3 = c.subtract(aux2);
					BigDecimal aux4 = aux3.divide(b, MathContext.DECIMAL128);
					m1.add(aux4);
					m2.add(BigDecimal.valueOf(temp3.floatValue() + eps.floatValue()));
					BigDecimal auxb = BigDecimal.valueOf(temp3.floatValue() + eps.floatValue());
					BigDecimal aux2b = a.multiply(auxb);
					BigDecimal aux3b = c.subtract(aux2b);
					BigDecimal aux4b = aux3b.divide(b, MathContext.DECIMAL128);
					m2.add(aux4b);
				}
			}
			if(n == 0) {
				if(!PointIn(points, m1, poly) || !PointIn(points, m2, poly)) {
					int2 = true;
				}
			}
			if(n == 1) {
				if((PointIn(points, m1, poly) && !alignedpolBig(points, m1, poly)) || (PointIn(points, m2, poly) && !alignedpolBig(points, m2, poly))) {
					int2 = true;
				}
			}
		}
		return int2;
	}

	static ArrayList<ArrayList<ArrayList<Integer>>> sidespol(ArrayList<ArrayList<Integer>> punt, ArrayList<Integer> poly ) {
		ArrayList<ArrayList<ArrayList<Integer>>> lad = new ArrayList<>();
		ArrayList<Integer> punto1aux;
		ArrayList<Integer> punto2aux;
		ArrayList<ArrayList<Integer>> pointsaux;
		ArrayList<Integer> temp1, temp2;
		for (int i = 0; i < poly.size()-1; i++) {
			temp1 = punt.get(poly.get(i) - 1);
			temp2 = punt.get(poly.get(i + 1) - 1);
			punto1aux = new ArrayList<>();
			punto1aux.add(temp1.get(0));
			punto1aux.add(temp1.get(1));
			punto2aux = new ArrayList<>();
			punto2aux.add(temp2.get(0));
			punto2aux.add(temp2.get(1));
			pointsaux = new ArrayList<>();
			pointsaux.add(punto1aux);
			pointsaux.add(punto2aux);
			lad.add(pointsaux);
		}
		punto1aux = new ArrayList<>();
		temp1 = punt.get(poly.get(poly.size() - 1) - 1);
		temp2 = punt.get(poly.get(0) - 1);
		punto1aux.add(temp1.get(0));
		punto1aux.add(temp1.get(1));
		punto2aux = new ArrayList<>();
		punto2aux.add(temp2.get(0));
		punto2aux.add(temp2.get(1));
		pointsaux = new ArrayList<>();
		pointsaux.add(punto1aux);
		pointsaux.add(punto2aux);
		lad.add(pointsaux);
		return lad;
	}

	static boolean intersectionpol(ArrayList<ArrayList<Integer>> l) {
		boolean int2=false;
		ArrayList<ArrayList<ArrayList<Integer>>>  sides;
		ArrayList<ArrayList<ArrayList<Integer>>>  aguj;
		sides = sidespol(points,polygon);
		for (int i = 0; i <sides.size(); i++) {
			int2 = intersectionpoly(l, sides.get(i), polygon, 0);
			if(int2) {
				break;
			}
		}
	    if(!int2) {
	    	if(!holes.isEmpty()) {
	    		int holesi;
	    		for (int i = 0; i < holes.size(); i++) {
	    			aguj = sidespol(points,holes.get(i));
	    			if(int2) {
	    				break;
	    			}
					holesi = holes.get(i).size();
	    			for (int j = 0; j <holesi; j++) {
						int2 = intersectionpoly(l, aguj.get(j), holes.get(i), 1);
						if(int2) {
							break;
						}
					}
				}
	    	}
		}
		return int2;
	}

	static ArrayList<BigDecimal> medio(ArrayList<Integer> p, ArrayList<Integer> q) {
		ArrayList<BigDecimal> m = new ArrayList<>();
		m.add(BigDecimal.valueOf((p.get(0) + q.get(0)) / 2.0));
		m.add(BigDecimal.valueOf((p.get(1) + q.get(1)) / 2.0));
		return m;
	}

	static ArrayList<ArrayList<Integer>> matrix(ArrayList<ArrayList<Integer>> punt) {
		ArrayList<ArrayList<ArrayList<Integer>>>  sides;
		ArrayList<ArrayList<Integer>>  matriz = new ArrayList<>();
		ArrayList<ArrayList<Integer>>  l;
		ArrayList<BigDecimal> m;
		ArrayList<Integer> temp1, temp2;
		for (int i = 0; i < punt.size(); i++) {
			ArrayList<Integer>  fila = new ArrayList<>();
			for (int j = 0; j < punt.size(); j++) {
				fila.add(0);
			}
			matriz.add(fila);
		}
		sides = sidespol(punt, polygon);
		for (int i = 0; i < punt.size()-1; i++) {
			for (int j = i+1; j < punt.size(); j++) {

				temp1 = punt.get(i);
				temp2 = punt.get(j);

				l = new ArrayList<>();
				l.add(temp1);
				l.add(temp2);
				if(sides.contains(l)) {
					matriz.get(i).set(j, 1);
				}else {
					if(!intersectionpol(l)) {
						m = medio(temp1,temp2);
						if(PointIn(punt, m, polygon)) {
							matriz.get(i).set(j, 1);
						}
					}
				}
			}
		}
		if(!holes.isEmpty()) {
			for (int i = 0; i <holes.size(); i++) {
				temp1 = holes.get(i);
				for (int j = 0; j < temp1.size()-1; j++) {
					for (int k = 0; k < temp1.size(); k++) {
						m = medio(points.get(temp1.get(j)-1), points.get(temp1.get(k)-1));
						if(!alignedpolBig(points, m, temp1)) {
							matriz.get(temp1.get(j)-1).set(temp1.get(k)-1,0);
						}
					}
				}
			}
		}

		for (int i = 0; i < punt.size(); i++) {
			for (int j = 0; j < punt.size(); j++) {
				matriz.get(j).set(i, matriz.get(i).get(j));
			}
		}
		return matriz;
	}

	static boolean holes(ArrayList<ArrayList<Integer>> punt, ArrayList<Integer> polygon, ArrayList<Integer> hole) {
		boolean int2 = false;
		ArrayList<Integer> aguj = new ArrayList<>(hole);
		aguj.add(hole.get(0));
		ArrayList<BigDecimal> m;
		int n = aguj.size();
		for (int i = 0; i < n-1; i++) {
			m = medio(punt.get(aguj.get(i) - 1), punt.get(aguj.get(i+1) - 1));
			if (PointIn(punt, m, polygon)) {
				if (!alignedpolBig(punt, m, polygon)) {
					int2 = true;
					break;
				}
			}
		}
		return int2;
	}

	static boolean segments(ArrayList<ArrayList<Integer>> punt, ArrayList<Integer> camino) {
		ArrayList<ArrayList<ArrayList<Integer>>> lad = new ArrayList<>();
		ArrayList<ArrayList<Integer>> points;
		ArrayList<Integer> punto1;
		ArrayList<Integer> punto2;
		ArrayList<Integer> temp1,temp2;
		for (int i = 0; i <camino.size()-1; i++) {
			temp1 = punt.get(camino.get(i) - 1);
			temp2 = punt.get(camino.get(i + 1) - 1);
			punto1 = new ArrayList<>();
			punto1.add(temp1.get(0));
			punto1.add(temp1.get(1));
			punto2 = new ArrayList<>();
			punto2.add(temp2.get(0));
			punto2.add(temp2.get(1));
			points = new ArrayList<>();
			points.add(punto1);
			points.add(punto2);
			lad.add(points);
		}
		temp1 = punt.get(camino.get(camino.size() - 1) - 1);
		temp2 = punt.get(camino.get(0) - 1);
		punto1 = new ArrayList<>();
		punto1.add(temp1.get(0));
		punto1.add(temp1.get(1));
		punto2 = new ArrayList<>();
		punto2.add(temp2.get(0));
		punto2.add(temp2.get(1));
		points = new ArrayList<>();
		points.add(punto1);
		points.add(punto2);
		lad.add(points);

		boolean seg = false;
		for (int i = 0; i < lad.size()-1; i++) {
			if (seg) {
				break;
			}
			for (int j = i; j < lad.size(); j++) {
				seg = intersection(lad.get(i), lad.get(j));
				if (seg) {
					break;
				}
			}
		}
		return seg;
	}

	static int aligned(ArrayList<ArrayList<Integer>> punt, ArrayList<Integer> camino) {
		int alig = -1;
		ArrayList<Integer> temp1,temp2,temp3;
		ArrayList<Integer> cam = new ArrayList<>(camino);
		cam.add(camino.get(0));
		cam.add(camino.get(1));
		for (int i = 0; i <camino.size(); i++) {
			temp1 = punt.get(cam.get(i) - 1);
			temp2 = punt.get(cam.get(i + 1) - 1);
			temp3 = punt.get(cam.get(i + 2) - 1);
			alig = temp1.get(0) * (temp2.get(1) - temp3.get(1))
					+ temp2.get(0) * (temp3.get(1) - temp1.get(1))
					+ temp3.get(0) * (temp1.get(1) - temp2.get(1));
			if (alig == 0) {
				break;
			}
		}
		return alig;
	}

	static ArrayList<ArrayList<Integer>> sides(int nodo1, int nodo2, int distancia, ArrayList<ArrayList<Integer>> matriz) {
		ArrayList<ArrayList<Integer>> solucion = new ArrayList<>();
		ArrayList<ArrayList<Integer>> aristas = new ArrayList<>();
		ArrayList<ArrayList<Integer>> temp = null;
		int ultimo, aguj;
		ArrayList<Integer> temp1;
		aristas.add(new ArrayList<>());
		aristas.get(0).add(nodo1);
		if (matriz.get(nodo1 - 1).get(nodo2 - 1) == 1) {
			for (int i = 0; i < distancia; i++) {
				temp = new ArrayList<>();
				for (int k = 0; k < aristas.size(); k++) {
					temp1 = aristas.get(k);
					ultimo = temp1.get(i);
					if (ultimo != nodo2) {
						for (int j = 1; j <matriz.size()+1; j++) {
							if (matriz.get(ultimo - 1).get(j - 1) == 1 && !temp1.contains(j)) {
								temp1.add(j);
								temp.add((ArrayList<Integer>) temp1.clone());
								temp1.remove(temp1.size() - 1);
							}
						}
					}
				}
				aristas.clear();
				aristas.addAll(temp);
			}
			assert temp != null;
			temp.clear();
			for (int k = 0; k < aristas.size(); k++) {
				temp1 = aristas.get(k);
				if (temp1.get(distancia) == nodo2 && aligned(points, temp1) != 0
						&& !segments(points, temp1)) {
					temp.add(temp1);
				}
			}
			for (int i = 0; i <temp.size(); i++) {
				aguj = 0;
				for (int j = 0; j <holes.size(); j++) {
					if (!holes(points, temp.get(i), holes.get(j))) {
						aguj = aguj + 1;
					}
				}
				if (aguj == holes.size()) {
					solucion.add(temp.get(i));
				}
			}
		}
		return solucion;
	}

	static ArrayList<ArrayList<Integer>> duplicates(ArrayList<ArrayList<Integer>> polygons) {
		ArrayList<ArrayList<Integer>> dupl = new ArrayList<>();
		ArrayList<ArrayList<Integer>> aux = new ArrayList<>();
		ArrayList<Integer> aux2 = new ArrayList<>();
		for (int i = 0; i < polygons.size(); i++) {
			aux2.addAll(polygons.get(i));
			Collections.sort(aux2);
			if (!aux.contains(aux2)) {
				dupl.add((ArrayList<Integer>) polygons.get(i).clone());
				aux.add((ArrayList<Integer>) aux2.clone());
			}
			aux2.clear();
		}
		return dupl;
	}

	static ArrayList<ArrayList<Integer>> update(ArrayList<ArrayList<Integer>> polygons, int fun) {
		ArrayList<ArrayList<Integer>> upd = new ArrayList<>();
		double maxi = 0;
		double f;
		ArrayList<Integer> temp1;
		for (int i = 0; i < polygons.size(); i++) {
			temp1 = polygons.get(i);
			f = function(temp1, fun);
			if (f >= maxi) {
				upd.add(temp1);
			}
			if (f > maxi) {
				upd = new ArrayList<>();
				maxi = f;
				upd.add(temp1);
			}
		}
		return upd;
	}

	static double function(ArrayList<Integer> polygon, int fun) {
		double funcion = 0;
		ArrayList<Integer> temp1, temp2;
		if (fun == 0) {
			for (int i = 1; i < polygon.size(); i++) {
				temp1 = points.get(polygon.get(i - 1) - 1);
				temp2 = points.get(polygon.get(i) - 1);
				funcion = funcion + ((temp1.get(0) * temp2.get(1)) - (temp2.get(0) * temp1.get(1)));
			}
			temp1 = points.get(polygon.get(polygon.size() - 1) - 1);
			temp2 = points.get(polygon.get(0) - 1);
			funcion = funcion + ((temp1.get(0) * temp2.get(1)) - (temp2.get(0) *temp1.get(1)));
			funcion = Math.abs(0.5 * funcion);
		}
		if (fun == 1) {
			for (int i = 1; i < polygon.size(); i++) {
				temp1 = points.get(polygon.get(i - 1) - 1);
				temp2 = points.get(polygon.get(i) - 1);
				funcion = (funcion + Math.sqrt(Math
						.pow(temp1.get(1) - temp2.get(1), 2)
						+ Math.pow(temp1.get(0) - temp2.get(0),
						2)));
			}
			temp1 = points.get(polygon.get(polygon.size() - 1) - 1);
			temp2 = points.get(polygon.get(0) - 1);
			funcion = (funcion + Math.sqrt(Math.pow(temp1.get(1) - temp2.get(1), 2) + Math.pow(temp1.get(0) - temp2.get(0), 2)));
			funcion = Math.round(funcion * 10000000000.0) / 10000000000.0;
		}
		return funcion;
	}
	
	static ArrayList<ArrayList<Integer>> polygons(int nodos, int distancia, ArrayList<ArrayList<Integer>> matriz,
			int fun) {
		ArrayList<ArrayList<Integer>> pol = new ArrayList<>();
		ArrayList<ArrayList<Integer>> polygons;
		ArrayList<ArrayList<Integer>> aux;
		for (int i = 0; i < nodos-1; i++) {
			for (int j = i + 1; j < nodos+1; j++) {
				aux = sides(i + 1, j, distancia, matriz);
				pol = (ArrayList<ArrayList<Integer>>) union(aux, pol);
			}
		}
		polygons = duplicates(update(pol, fun));

		return polygons;
	}

	static public <T> List<T> union(ArrayList<T> list1, ArrayList<T> list2) {
		Set<T> set = new HashSet<>();
		set.addAll(list1);
		set.addAll(list2);
		return new ArrayList<>(set);
	}
}
