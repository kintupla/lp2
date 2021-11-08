#include <stdio.h>
#include <stdlib.h>
#include "rect.h"

struct Figure {
    int x, y;
    int width, height;
    void (*print) (struct Figure*);
};

typedef void (*Figure_Print) (struct Figure*);

struct rect {
    Figure super;
    int width, height;
};

rect* rect_New(int x, int y, int width, int height) {
    rect* this = malloc(sizeof(rect));
    Figure* super = (Figure*) this;
    super->print = (Figure_Print) rect_Print;
    super->x = x;
    super->y = y;
    super->width = width;
    super->height = height;
    
}
void rect_Drag(rect* this, int dx, int dy) {
    Figure* super = (Figure*) this;

    super->x += dx;
    super->y += dy;
}
void rect_Print(rect* this) {
    Figure* super = (Figure*) this;

    printf("Retangulo de tamanho (%d, %d) na posicao (%d, %d).\n",
           super->width, super->height, super->x, super->y);}

