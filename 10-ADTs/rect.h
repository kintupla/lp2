typedef struct Figure Figure;

typedef void (*Figure_Print) (struct Figure*);

typedef struct rect rect;

rect* rect_New(int, int, int, int);
void rect_Print(rect*);
void rect_Drag(rect*, int dx, int dy);
