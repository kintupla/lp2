public class RectApp {
	public static void main (String[] args) {
		Rect r1 = new Rect(1,1, 10,10);
		r1.print();
		r1.drag(15,35);
		r1.printdrag();
		
    }
}
class Rect {
   	int x, y;
   	int w, h;
   	int areaRet;
	Rect (int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}

	int Area (){
        this.areaRet= this.w*this.h;
		return areaRet;
	}

	void drag(int dx,int dy){
		this.x = this.x + dx;
		this.y = this.y + dy;
		
	}

	void print () {
		System.out.format("Retangulo de tamanho (%d,%d) na posicao (%d,%d).\n Area do retangulo: %d\n",
		this.w, this.h, this.x, this.y, Area());
	}
	void printdrag (){
		System.out.format("soma de dx e dy a sua posição atual (%d,%d)\n",this.x,this.y);
	}

}
