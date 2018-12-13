package day13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Day13 {
    static class Cart implements Comparable<Cart> {
        int x, y;
        int dx, dy;
        int turns=0;

        Cart(int y, int x, char c) {
            this.x=x;
            this.y=y;
            if (c == '<'){
                dx=-1;
                dy=0;
            }
            if (c == '^') {
                dx = 0;
                dy = -1;
            }
            if (c == '>') {
                dx = 1;
                dy = 0;
            }
            if (c == 'v') {
                dy = 1;
                dx = 0;
            }
            System.out.println("New cart "+y+","+x+", "+c+" dx:"+dx+" dy:"+dy);

        }

        int getX() {
            return x;
        }
        int getY() {
            return y;
        }


        @Override
        public int compareTo(Cart other){
            if (this.getY() < other.getY())
                return -1;
            else if (this.getY() > other.getY())
                return 1;
            return other.getX()-this.getX();
        }

        boolean move(char[][] track, List<Cart> carts) {

            if (!isFree(y+dy, x+dx, track, carts)) {
                x=x+dx;
                y=y+dy;

                track[y][x] = 'X';
        return false;
            }
            x=x+dx;
            y=y+dy;

            char nextPlace= track[y][x];
            //System.out.print("x:"+x+" y:"+y+" "+"Next: "+nextPlace +" dx:"+dx+" dy:"+dy);

            int tmp=dx;
            if (nextPlace == '\\') {
                 dx=dy;
                 dy=tmp;

            } else if (nextPlace == '/') {
                dx=-dy;
                dy=-tmp;

            } else if (nextPlace == '+') {
                if (turns%3 == 0) {
                    dx=dy;
                    dy=tmp;
                } else if (turns%3 == 2) {
                    dx=-dy;
                    dy=-tmp;
                }
                turns++;
            }

            return true;
        }
        private boolean isFree(int y, int x, char[][] track, List<Cart> carts) {
            for (Cart cart: carts) {
                if (cart.getX()==x && cart.getY()==y) {
                    return false;
                }
            }
            if (track[y][x] == 'X') {
                return false;
            }
            return true;
        }

    }


    static String solveA(String input) {
        String[] rows = input.split("\r?\n");
        char[][] track = new char[rows.length][rows[0].length()];
        ArrayList<Cart> carts = new ArrayList<>();

        for (int row=0; row<rows.length; row++) {
            for (int col=0; col< rows[row].length(); col++) {
                char curr= rows[row].charAt(col);
                if (curr=='<') {
                    carts.add(new Cart(row, col, curr));
                    track[row][col] = '-';
                } else if (curr=='>') {
                    carts.add(new Cart(row, col, curr));
                    track[row][col] = '-';
                } else if (curr=='v') {
                    carts.add(new Cart(row, col, curr));
                    track[row][col] = '|';
                } else if (curr=='^') {
                    carts.add(new Cart(row, col, curr));
                    track[row][col] = '|';
                } else {
                    track[row][col] = curr;
                }
            }
        }

        //Move da cars!



        for (int iteration = 0; ; iteration++) {
            Collections.sort(carts);
            for (Cart cart : carts) {
                if (!cart.move(track, carts)) {
                    System.out.println(" KABLOOM @ "+cart.getX()+", "+cart.getY());
                    return cart.getX()+","+cart.getY();
                }
            }

        }
    }

    static String solveB(String input) {
        return "";
    }
}
