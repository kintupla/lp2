#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int r, g, b;
} Color;

struct Figure;
typedef void (*Figure_Print) (struct Figure*);
typedef void (*Figure_Area) (struct Figure*);

typedef struct {
    void (* print) (struct Figure*);
    int  (* area)  (struct Figure*);
} Figure_vtable;

typedef struct Figure {
    int x, y;
    int width, height;
    Color borderColor, fillColor;
    void (*print) (struct Figure*);
    Figure_vtable* vtable;
} Figure;

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
} Ellipse;

void Ellipse_Print(Ellipse* this) {
    Figure* super = (Figure*) this;

    printf("Elipse de tamanho (%d, %d) na posicao (%d, %d).\n",
           super->width, super->height, super->x, super->y);

    printf("Cor de borda: (%d, %d, %d). Cor de fundo: (%d, %d, %d).\n",
        super->borderColor.r, super->borderColor.g, super->borderColor.b,
        super->fillColor.r, super->fillColor.g, super->fillColor.b);
}

int ellipse_area (Rect* this) {
    Figure* super = (Figure*) this;
    return this->width * this->height;
}

Figure_vtable ellipse_vtable = {
    (Figure_Print) Ellipse_Print,
    (Figure_Area)  ellipse_area
};

Ellipse* Ellipse_New(int x, int y, int width, int height, Color borderColor, Color fillColor) {
    Ellipse* this = malloc(sizeof(Ellipse));
    Figure* super = (Figure*) this;
    super->vtable = &ellipse_vtable;
    super->x = x;
    super->y = y;
    super->width = width;
    super->height = height;
    super->borderColor = borderColor;
    super->fillColor = fillColor;
    super->print = (Figure_Print) Ellipse_Print;
}

///////////////////////////////////////////////////////////////////////////////

typedef struct {
    Figure super;
} Triangle;

void Triangle_Print(Triangle* this) {
    Figure* super = (Figure*) this;

    printf("Triangulo de vertices (%d, %d), (%d, %d) e (%d, %d).\n",
        super->x, super->y, super->x, super->y + super->height, super->x + super->width, super->y + super->height);

    printf("Cor de borda: (%d, %d, %d). Cor de fundo: (%d, %d, %d).\n",
        super->borderColor.r, super->borderColor.g, super->borderColor.b,
        super->fillColor.r, super->fillColor.g, super->fillColor.b);
}

int triangle_area (Rect* this) {
    Figure* super = (Figure*) this;
    return this->width * this->height;
}

Figure_vtable triangle_vtable = {
    (Figure_Print) Triangle_Print,
    (Figure_Area)  triangle_area
};

Triangle* Triangle_New(int x, int y, int width, int height, Color borderColor, Color fillColor) {
    Triangle* this = malloc(sizeof(Triangle));
    Figure* super = (Figure*) this;
    sup->vtable = &triangle_vtable;
    super->x = x;
    super->y = y;
    super->width = width;
    super->height = height;
    super->borderColor = borderColor;
    super->fillColor = fillColor;
    super->print = (Figure_Print) Triangle_Print;
}
///////////////////////////////////////////////////////////////////////////////

void main (void) {
    Color red = {255, 0, 0};
    Color green = {0, 255, 0};
    Color blue = {0, 0, 255};
    Color black = {0, 0, 0};
  
    Figure* figs[4] = {
        (Figure*) Triangle_Ne(10,10,100,100, blue, black),
        (Figure*) Ellipse_New(40,10,140,300, red, green),
        (Figure*) Triangle_Ne(10,10,100,100, blue, black),
        (Figure*) Ellipse_New(210,110,305,130, red, green)
    };

    ///

    for (int i=0; i<4; i++) {
        figs[i]->vtable->print(figs[i]);
    }

    ///

    for (int i=0; i<4; i++) {
        free(figs[i]);
    }
}
