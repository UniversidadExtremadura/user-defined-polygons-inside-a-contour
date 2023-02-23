# user-defined-polygons-inside-a-contour
# Obtaining the user-defined polygons inside a closed contour with holes

In image processing, computer vision algorithms are applied to regions bounded by closed contours. These contours are often irregular, poorly defined, and contain holes or unavailable areas inside. A common problem in computational geometry includes finding the k-sided polygon (k-gon) of maximum area or maximum perimeter inscribed within a contour. This code presents a generic method to obtain user-defined polygons within a region. Users can specify the number k of sides of the polygon to obtain. Additionally, users can also decide whether the calculated polygon should be the largest in area or perimeter. This algorithm
produces a polygon or set of polygons that can be used to segment an image, allowing only relevant areas to be processed.

Source code available in:

- Java:   holes.java
- Python: holes.py

Contact:
- Media Engineering Group - University of Extremadura
- Andres Caro - andresc@unex.es
