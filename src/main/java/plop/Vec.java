package plop;

import static java.lang.Math.*;

/*
Cette classe a été importée depuis https://rosettacode.org/wiki/Boids/Java
 */

class Vec {
    double x, y;

    Vec() {
    }

    Vec(double x, double y) {       //setter
        this.x = x;
        this.y = y;
    }

    void add(Vec v) {       //ajoute le vecteur à un autre
        x += v.x;
        y += v.y;
    }

    void sub(Vec v) {           //Soustrait le vecteur à un autre
        x -= v.x;
        y -= v.y;
    }

    void div(double val) {      //Divise le vecteur par une valeur
        x /= val;
        y /= val;
    }

    void mult(double val) {     //Multiplie le vecteur par une valeur
        x *= val;
        y *= val;
    }

    double mag() {          //longueur du vecteur
        return sqrt(pow(x, 2) + pow(y, 2));
    }

    double dot(Vec v) {     //produit scalaire
        return x * v.x + y * v.y;
    }

    void normalize() {      //norme du vecteur
        double mag = mag();
        if (mag != 0) {
            x /= mag;
            y /= mag;
        }
    }

    void limit(double lim) {
        double mag = mag();
        if (mag != 0 && mag > lim) {
            x *= lim / mag;
            y *= lim / mag;
        }
    }

    double heading() {      //calcule l'arc tangeante
        return atan2(y, x);
    }

    static Vec sub(Vec v, Vec v2) {     //Soustrait un vecteur a à un vecteur b
        return new Vec(v.x - v2.x, v.y - v2.y);
    }

    static double dist(Vec v, Vec v2) {     //Différence entre les deux vecteurs
        return sqrt(pow(v.x - v2.x, 2) + pow(v.y - v2.y, 2));
    }

    static double angleBetween(Vec v, Vec v2) {     //angle entre deux vecteurs
        return acos(v.dot(v2) / (v.mag() * v2.mag()));
    }
}