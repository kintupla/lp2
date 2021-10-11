#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int r, g, b;
} Color;

struct Figure;
typedef void (*Figure_Print) (struct Figure*);

typedef struct Figure {
    int x, y;
    int width, height;
    Color borderColor, fillColor;
    void (*print) (struct Figure*);
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

Ellipse* Ellipse_New(int x, int y, int width, int height, Color borderColor, Color fillColor) {
    Ellipse* this = malloc(sizeof(Ellipse));
    Figure* super = (Figure*) this;

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

Triangle* Triangle_New(int x, int y, int width, int height, Color borderColor, Color fillColor) {
    Triangle* this = malloc(sizeof(Triangle));
    Figure* super = (Figure*) this;

    super->x = x;
    super->y = y;
    super->width = width;
    super->height = height;
    super->borderColor = borderColor;
    super->fillColor = fillColor;
    super->print = (Figure_Print) Triangle_Print;
}
///////////////////////////////////////////////////////////////////////////////

int main() {
    Color red = {255, 0, 0};
    Color green = {0, 255, 0};
    Color blue = {0, 0, 255};
    Color black = {0, 0, 0};

    Figure* figures[2] = {
        (Figure*) Ellipse_New(20, 30, 40, 50, red, green),
        (Figure*) Triangle_New(20, 30, 40, 50, blue, black)
    };

    for (int i = 0; i < 2; i++) {
        figures[i]->print(figures[i]);
    }

    for (int i = 0; i < 2; i++) {
        free(figures[i]);
    }

    return 0;
}
