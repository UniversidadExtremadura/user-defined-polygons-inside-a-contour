"""
UNIVERSITY OF EXTREMADURA
MEDIA ENGINEERING GROUP
CONTACT: andresc@unex.es
"""

import numpy as np
from time import time
from fractions import Fraction

points = [[2,6],[3,6],[4,5],[4,4],[5,3],
[5,2],[4,0],[3,1],[2,0],[1,2],[0,2],
[1,3],[0,4],[1,5],[3,5],[3,3],
[2,2],[2,1],[2,5],[3,4],[2,3],[1,4],
[4,3],[4,2],[4,1],[3,2]]

polygon= [1,2,3,4,5,6,7,8,9,10,11,12,13,14]
holes = [[19,20,21,22],[23,24,25,26]]

def direction(p, q, r):
    dire = (q[0] - p[0]) * (r[1] - p[1]) - (r[0] - p[0]) * (q[1] - p[1])
    round(dire, 1)
    return dire

def alignedp(p, q, r):
    alinp = min(p[0], q[0]) <= r[0] <= max(p[0], q[0]) and min(p[1], q[1]) <= r[1] <= max(p[1], q[1])
    return alinp

def intersection(l1, l2):
    inter = False
    p1 = l1[0]
    p2 = l1[1]
    p3 = l2[0]
    p4 = l2[1]
    d1 = direction(p3, p4, p1)
    d2 = direction(p3, p4, p2)
    d3 = direction(p1, p2, p3)
    d4 = direction(p1, p2, p4)
    if p1 != p3 and p1 != p4 and p2 != p3 and p2 != p4:
        if ((d1 > 0 and d2 < 0) or (d1 < 0 and d2 > 0)) and ((d3 > 0 and d4 < 0) or (d3 < 0 and d4 > 0)):
            inter = True
        else:
            if d1 == 0 and alignedp(p3, p4, p1):
                inter = True
            else:
                if d2 == 0 and alignedp(p3, p4, p2):
                    inter = True
                else:
                    if d3 == 0 and alignedp(p1, p2, p3):
                        inter = True
                    else:
                        if d4 == 0 and alignedp(p1, p2, p4):
                            inter = True
    else:
        inter = False
    return inter

def alignedpol(punt, p, poly):
    alin = False
    lad = []
    for i in range(0, len(poly)):
        lad.append(poly[i])
    lad.append(poly[0])
    for i in range(0, len(lad) - 1):
        if direction(punt[lad[i] - 1], punt[lad[i + 1] - 1], p) == 0:
            if alignedp(punt[lad[i] - 1], punt[lad[i + 1] - 1], p):
                alin = True
                break
    return alin

def angle(u, v):
    if not isinstance(u[0], int) and not isinstance(u[0], float):
        num = u[0].numerator
        dem = u[0].denominator
        u[0] = num / dem
    if not isinstance(u[1], int) and not isinstance(u[1], float):
        num = u[1].numerator
        dem = u[1].denominator
        u[1] = num / dem
    if not isinstance(v[0], int) and not isinstance(v[0], float):
        num = v[0].numerator
        dem = v[0].denominator
        v[0] = num / dem
    if not isinstance(v[1], int) and not isinstance(v[1], float):
        num = v[1].numerator
        dem = v[1].denominator
        v[1] = num / dem
    angle = 0
    if u[0] * u[0] + u[1] * u[1] != 0:
        if v[0] * v[0] + v[1] * v[1] != 0:
            det = u[0] * v[1] - u[1] * v[0]
            v1_u = u / np.linalg.norm(u)
            v2_u = v / np.linalg.norm(v)
            if det > 0:
                angle = np.arccos(np.clip(np.dot(v1_u, v2_u), -1.0, 1.0))
            else:
                angle = -np.arccos(np.clip(np.dot(v1_u, v2_u), -1.0, 1.0))
    return angle

def pointin(punt, p, poly):
    if alignedpol(punt, p, poly):
        point = True
    else:
        point = False
        n = len(poly)
        suma = 0
        for i in range(0, n - 1):
            suma = suma + angle([points[poly[i] - 1][0] - p[0], points[poly[i] - 1][1] - p[1]],
                                 [points[poly[i + 1] - 1][0] - p[0], points[poly[i + 1] - 1][1] - p[1]])
        suma = suma + angle([points[poly[n - 1] - 1][0] - p[0], points[poly[n - 1] - 1][1] - p[1]],
                             [points[poly[0] - 1][0] - p[0], points[poly[0] - 1][1] - p[1]])

        if np.abs(suma) >= np.pi:
            point = True
    return point

def sidespol(punt, poly):
    n = len(poly)
    lad = []
    for i in range(0, n - 1):
        lad.append([punt[poly[i] - 1], punt[poly[i + 1] - 1]])
    lad.append([punt[poly[n - 1] - 1], punt[poly[0] - 1]])
    return lad

def medio(p, q):
    c1 = (p[0] + q[0]) / 2
    c2 = (p[1] + q[1]) / 2
    m = [c1, c2]
    return m

def intersectionp(l1, l2):
    u1 = [l1[1][0] - l1[0][0], l1[1][1] - l1[0][1]]
    u2 = [l2[1][0] - l2[0][0], l2[1][1] - l2[0][1]]
    coinc = False
    if u1[0] * u2[1] == u1[1] * u2[0]:
        coinc = True
    if not coinc:
        if intersection(l1, l2):
            a = l1[1][1] - l1[0][1]
            b = l1[0][0] - l1[1][0]
            c = l1[0][0] * l1[1][1] - l1[1][0] * l1[0][1]

            d = l2[1][1] - l2[0][1]
            e = l2[0][0] - l2[1][0]
            f = l2[0][0] * l2[1][1] - l2[1][0] * l2[0][1]
            if l1[0][0] == l1[1][0]:
                p = [c / a, (a * f - c * d) / (a * e)]
            else:
                if l1[0][1] == l1[1][1]:
                    p = [Fraction((b * f - c * e), (b * d)), Fraction(c, b)]
                else:
                    p = [Fraction((b * f - c * e), (b * d - a * e)), Fraction((a * f - c * d), (a * e - b * d))]
    else:
        p = l2[0]
    return p

def intersectionpoly(l, lado, poly, n):
    int2 = False
    eps = Fraction(1, 100000)
    if intersection(l, lado):
        p = intersectionp(l, lado)
        if l[0][0] == l[1][0]:
            m1 = [l[0][0], p[1] - eps]
            m2 = [l[0][0], p[1] + eps]
        else:
            if l[0][1] == l[1][1]:
                m1 = [p[0] - eps, l[0][1]]
                m2 = [p[0] + eps, l[0][1]]
            else:
                a = l[1][1] - l[0][1]
                b = l[0][0] - l[1][0]
                c = l[0][0] * l[1][1] - l[1][0] * l[0][1]

                m1 = [p[0] - eps, ((c - a * (p[0] - eps)) / b)]
                m2 = [p[0] + eps, ((c - a * (p[0] + eps)) / b)]

        if n == 0:
            if pointin(points, m1, poly) == False or pointin(points, m2, poly) == False:
                int2 = True
        if n == 1:
            if ((pointin(points, m1, poly) == True and alignedpol(points, m1, poly) == False) or (
                    pointin(points, m2, poly) == True and alignedpol(points, m2, poly) == False)):
                int2 = True
    return int2

def intersectionpol(l):
    lados = sidespol(points, polygon)
    int2 = False
    for i in range(0, len(lados)):
        int2 = intersectionpoly(l, lados[i], polygon, 0)
        if int2:
            break

    if not int2:
        if holes:
            for i in range(0, len(holes)):
                aguj = sidespol(points, holes[i])
                if int2:
                    break
                for j in range(0, len(holes[i])):
                    int2 = intersectionpoly(l, aguj[j], holes[i], 1)
                    if int2:
                        break
    return int2

def matrix(punt):
    matrix = []
    for i in range(0, len(punt)):
        matrix.append([])
        for j in range(0, len(punt)):
            matrix[i].append(0)

    lados = sidespol(punt, polygon)
    for i in range(0, len(punt) - 1):
        for j in range(i + 1, len(punt)):
            l = [punt[i], punt[j]]
            aux2 = lados + [val for val in [l] if val not in lados]
            if len(aux2) == len(lados):
                matrix[i][j] = 1
            else:
                if not intersectionpol(l):
                    m = medio(punt[i], punt[j])
                    if pointin(punt, m, polygon):
                        matrix[i][j] = 1
    if holes:
        for i in range(0, len(holes)):
            for j in range(0, len(holes[i]) - 1):
                for k in range(0, len(holes[i])):
                    m = medio(points[holes[i][j] - 1], points[holes[i][k] - 1])
                    if not alignedpol(points, m, holes[i]):
                        matrix[holes[i][j] - 1][holes[i][k] - 1] = 0
    for i in range(0, len(punt)):
        for j in range(0, len(punt)):
            matrix[j][i] = matrix[i][j]
    return matrix

def aligned(punt, camino):
    cam = camino.copy()
    cam.append(camino[0])
    cam.append(camino[1])
    for i in range(0, len(camino)):
        alig = punt[cam[i] - 1][0] * (punt[cam[i + 1] - 1][1] - punt[cam[i + 2] - 1][1]) + punt[cam[i + 1] - 1][0] * (
                punt[cam[i + 2] - 1][1] - punt[cam[i] - 1][1]) + punt[cam[i + 2] - 1][0] * (
                       punt[cam[i] - 1][1] - punt[cam[i + 1] - 1][1])
        if alig == 0:
            break
    return alig

def segments(punt, camino):
    n = len(camino)
    lad = []
    for i in range(0, len(camino) - 1):
        lad.append([punt[camino[i] - 1], punt[camino[i + 1] - 1]])
    lad.append([punt[camino[n - 1] - 1], punt[camino[0] - 1]])
    seg = False
    m = len(lad)
    for i in range(0, m - 1):
        if seg:
            break
        for j in range(i, m):
            seg = intersection(lad[i], lad[j])
            if seg:
                break
    return seg

def Holes(punt, polygon, hole):
    int2 = False
    n = len(hole)
    for i in range(0, n - 1):
        m = medio(punt[hole[i] - 1], punt[hole[i + 1] - 1])
        if pointin(punt, m, polygon):
            if not alignedpol(punt, m, polygon):
                int2 = True
                break
    m = medio(punt[hole[n - 1] - 1], punt[hole[0] - 1])
    if pointin(punt, m, polygon):
        if not alignedpol(punt, m, polygon):
            int2 = True
    return int2

def lados(nodo1, nodo2, distancia, matrix):
    aristas = []
    polygon = []
    aristas.append([nodo1])
    if matrix[nodo1 - 1][nodo2 - 1] == 1:
        for i in range(0, distancia):
            temp = []
            for k in range(0, len(aristas)):
                ultimo = aristas[k][i]
                if ultimo != nodo2:
                    for j in range(1, len(matrix) + 1):
                        if matrix[ultimo - 1][j - 1] == 1:
                            if j not in aristas[k]:
                                aristas[k].append(j)
                                temp.extend([aristas[k].copy()])
                                aristas[k].pop()

            aristas = temp.copy()
        temp = []
        for k in range(0, len(aristas)):
            if aristas[k][distancia] == nodo2:
                if not segments(points, aristas[k]):
                    if aligned(points, aristas[k]) != 0:
                        temp.append(aristas[k])

        polygon = temp
        if holes:
            polygon = []
            for i in range(0, len(temp)):
                aguj = 0
                for j in range(0, len(holes)):
                    if not Holes(points, temp[i], holes[j]):
                        aguj = aguj + 1
                if aguj == len(holes):
                    polygon.append(temp[i])
    return polygon

def duplicates(polygons):
    aux = set()
    final = []
    for i in range(0, len(polygons)):
        if frozenset(polygons[i]) not in aux:
            final.append(polygons[i])
            aux.add(frozenset(polygons[i]))
    return final

def update(polygons, fun):
    upd = []
    maxi = 0
    for i in range(0, len(polygons)):
        f = function(polygons[i], fun)
        if f >= maxi:
            upd.append(polygons[i].copy())
        if f > maxi:
            maxi = f
            upd = [polygons[i].copy()]
    return upd

def function(polygon, fun):
    n = len(polygon)
    function = 0
    if fun == 0:
        for i in range(1, n):
            function = function + round(np.linalg.det(np.array([points[polygon[i - 1] - 1], points[polygon[i] - 1]])))
        function = function + round(np.linalg.det(np.array([points[polygon[n - 1] - 1], points[polygon[0] - 1]])))
        function = np.abs(0.5 * function)
    if fun == 1:
        for i in range(1, n):
            function = function + np.sqrt(((points[polygon[i - 1] - 1][1] - points[polygon[i] - 1][1]) ** 2) + (
                    (points[polygon[i - 1] - 1][0] - points[polygon[i] - 1][0]) ** 2))
        function = round(function + np.sqrt(((points[polygon[n - 1] - 1][1] - points[polygon[0] - 1][1]) ** 2) + (
                (points[polygon[n - 1] - 1][0] - points[polygon[0] - 1][0]) ** 2)), 12)
    return function

def polygons(nodos, distancia, matrix, fun):
    pol = []
    for i in range(0, nodos - 1):
        for j in range(i + 2, nodos + 1):
            aux = lados(i + 1, j, distancia, matrix).copy()
            pol = [*map(list, list(set(map(tuple, pol)) | set(map(tuple, aux))))].copy()
    polygons = duplicates(update(pol, fun))
    polygons.sort()
    return polygons

matrix = matrix(points)
print(matrix)

nodos = 26

print("----------- TRIANGLE---------------")
inicio = time()
print(polygons(nodos, 2, matrix, 0))
fin = time() - inicio
print("Tiempo:", fin)

print("----------- CUADRILÁTERO ---------------")
inicio = time()
print(polygons(nodos, 3, matrix, 0))
fin = time() - inicio
print("Tiempo:", fin)

print("----------- PENTAGONO ---------------")
inicio = time()
print(polygons(nodos, 4, matrix, 0))
fin = time() - inicio
print("Tiempo:", fin)

print("----------- HEXAGONO ---------------")
inicio = time()
print(polygons(nodos, 5, matrix, 0))
fin = time() - inicio
print("Tiempo:", fin)

print("----------- HEPTAGONO ---------------")
inicio = time()
print(polygons(nodos, 6, matrix, 0))
fin = time() - inicio
print("Tiempo:", fin)

print("----------- OCTOGONO ---------------")
inicio = time()
print(polygons(nodos, 7, matrix, 0))
fin = time() - inicio
print("Tiempo:", fin)
