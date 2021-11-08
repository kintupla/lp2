#include <stdlib.h>
#include "rect.h"

void main (void) {

    rect* r1 = rect_New(40, 40, 60, 60);
    rect_Print(r1);

    rect* r2 = rect_New(20, 20, 70, 70);
    rect_Drag(r2, 60, 60);
    rect_Print(r2);

    free(r1);
    free(r2);
}
