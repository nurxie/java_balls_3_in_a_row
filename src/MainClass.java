import java.util.Random;
import java.util.Scanner;

public class MainClass {
    public MainClass gameClass;
    final public int  x_define = 6;
    final public int  y_define = 6;
    final public char UNFILLED = '*';
    public int score = 0;
    final Random random = new Random();

    /*char[][] mass = {
            {'7', '4', '8', '4'},
            {'5', '9', '3', '4'},
            {'7', '1', '8', '7'},
            {'2', '2', '1', '2'},
    };

    char[][] duplicate = {
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED},
    };

    char[][] mass_after_string_search = {
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED},
    };

    char[][] mass_after_column_search = {
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED},
    };*/

    char[][] mass = {
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
    };
    char[][] duplicate = {
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
    };
    char[][] mass_after_string_search = {
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
    };
    char[][] mass_after_column_search = {
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
            {UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED, UNFILLED},
    };

    String del_repeat(String buffer, int curr_pos, char templ) {
        StringBuffer sb = new StringBuffer(buffer);
        while ((curr_pos < buffer.length()) && (buffer.charAt(curr_pos) == templ)) {
            //sb.replace(curr_pos, curr_pos, Character.toString(UNFILLED));
            sb.setCharAt(curr_pos, UNFILLED);
            curr_pos++;
        }
        return sb.toString();
    }


    String check_repeat(String buffer) {
        for(int curr_pos = 0;(curr_pos + 2) < buffer.length();){
            if (((buffer.charAt(curr_pos) == buffer.charAt(curr_pos + 1)) && (buffer.charAt(curr_pos) == buffer.charAt(curr_pos + 2))) && ((buffer.charAt(curr_pos)  != UNFILLED && buffer.charAt(curr_pos + 1)  != UNFILLED && buffer.charAt(curr_pos + 2)  != UNFILLED))) {
                buffer = del_repeat(buffer, curr_pos, buffer.charAt(curr_pos));
                score++;
            } else {
                curr_pos++;
            }
        }
        return buffer;
    }

    void gravitation() {
        for (int countner_of_faling = x_define - 1; countner_of_faling > 0; countner_of_faling--) {
            for (int y = y_define - 1; y >= 0; y--) {
                for (int x = 0; x < x_define; x++) {
                    if (mass[y][x] == '*') {
                        boolean enable = true;
                        int countner = 0;
                        while (enable) {
                            if ((y - countner - 1) >= 0) {
                                mass[y - countner][x] = mass[y - countner - 1][x];
                                mass[y - countner - 1][x] = '*';
                                countner++;
                            } else {
                                enable = false;
                            }
                        }
                    }
                }
            }
        }
    }

    boolean delete_column(char[][] receiving_array) { //mass_after_column_search
        String buffer = "";
        boolean buff = false;

        for (int x = 0; x < x_define; x++) {
            for (int y = 0; y < y_define; y++) {
                buffer = buffer + receiving_array[y][x];
            }
            buffer = check_repeat(buffer);
            for (int y = 0; y < y_define; y++) {
                mass_after_column_search[y][x] = buffer.charAt(y);
            }
            for (int y = 0; y < y_define; y++)
            if (buffer.charAt(y) == UNFILLED) {
                buff = true;
            }
            buffer = "";
        }

        return buff;
    }

    boolean delete_string(char[][] receiving_array) {
        boolean buff = false;
        String buffer = "";

        for (int y = 0; y < y_define; y++) {
            for (int x = 0; x < x_define; x++) {
                buffer = buffer + receiving_array[y][x];
            }
            buffer = check_repeat(buffer);
            for (int x = 0; x < x_define; x++) {
                mass_after_string_search[y][x] = buffer.charAt(x);
            }
            for (int x = 0; x < x_define; x++)
            if (buffer.charAt(x) == UNFILLED) {
                buff = true;
            }
            buffer="";
        }

        return buff;
    }

    void comparison() {
        for (int x = 0; x < x_define; x++) {
            for (int y = 0; y < y_define; y++) {
                if ((mass_after_column_search[y][x] != mass_after_string_search[y][x]) || ((mass_after_string_search[y][x] == UNFILLED) && (mass_after_column_search[y][x] == UNFILLED))) {
                    mass[y][x] = UNFILLED;
                }
            }
        }
    }

    boolean filling() {
        boolean edit = false;
        for (int x = 0; x < x_define; x++) {
            for (int y = 0; y < y_define; y++) {
                if (mass[y][x] == UNFILLED) {
                    mass[y][x] = ((char) ((random.nextInt(9) + 1) + '0')); //ot 1 do 9
                    edit = true;
                }
            }
        }
        return edit;
    }

    void swap(int y1, int x1, int y2, int x2, char[][] receiving_array) {
        char buff = ' ';
        buff = receiving_array[y1][x1];
        receiving_array[y1][x1] = receiving_array[y2][x2];
        receiving_array[y2][x2] = buff;
    }

    boolean findcomdinations() {
        duplicate = mass;
        boolean boolbuffstring = false;
        boolean boolbuffcolumn = false;
        for (int x = 0; x < x_define; x++) {
            for (int y = 0; y < y_define; y++) {
                if (y + 1 < y_define) {
                    swap(y, x, y + 1, x, duplicate);
                    boolbuffstring = delete_string(duplicate);
                    boolbuffcolumn = delete_column(duplicate);
                }
                if ((boolbuffstring || boolbuffcolumn) || boolbuffcolumn || boolbuffstring ) {
                    swap(y + 1, x, y, x, duplicate);
                    System.out.println(boolbuffcolumn + " column");
                    System.out.println(boolbuffstring + " string");
                    return true;
                }
                else {
                    if (y + 1 < y_define)
                        swap(y + 1, x, y, x, duplicate);
                }
            }
        }
        for (int y = 0; y < y_define; y++) {
            for (int x = 0; x < x_define; x++) {
                if (x + 1 < x_define) {
                    swap(y, x, y, x + 1, duplicate);
                    boolbuffstring = delete_string(duplicate);
                    boolbuffcolumn = delete_column(duplicate);
                }
                if (boolbuffstring || boolbuffcolumn) {
                    System.out.println(boolbuffcolumn + " column");
                    System.out.println(boolbuffstring + " string");
                    swap(y, x + 1, y, x, duplicate);
                    return true;
                }
                else {
                    if (x + 1 < x_define) {
                        swap(y, x + 1, y, x, duplicate);
                    }
                }
            }
        }
        return false;
    }

    void magicshake() {
        int end = (random.nextInt(99) + 1);
        while (true) {
            int x1 = (random.nextInt(x_define) + 1); //ot 1 do x_def
            int y1 = (random.nextInt(y_define) + 1); //ot 1 do y_def
            int x2 = (random.nextInt(x_define) + 1); //ot 1 do x_def
            int y2 = (random.nextInt(y_define) + 1); //ot 1 do y_def
            swap(y1, x1, y2, x2, mass);
            end--;
            if (end == 0) {
                break;
            }
        }
    }

    void cout_mass(char[][] resive_mass) {
        String buffer = "";
        for (int y = 0; y < y_define; y++) {
            for (int x = 0; x < x_define; x++) {
                buffer = buffer + resive_mass[y][x];
            }
            System.out.println(buffer);
            buffer = "";
        }
    }

    boolean gamecore() {
        delete_string(mass);
        delete_column(mass);
        comparison();
        gravitation();
        return filling();
    }

    void refilling(){
        for (int y = 0; y <= y_define; y++) {
            for (int x = 0; x <= x_define; x++) {
                mass[y][x] = ((char) ((random.nextInt(9) + 1) + '0'));
            }
        }
    }

    void make_a_good_mass(boolean resume_game){
        int i = 0;
        while (true) {
            magicshake();
            while(gamecore());
            if((findcomdinations() && !gamecore())) break;
            i++;
            if(i == 100) refilling();
        }
    }

    public boolean startGame(){
        boolean resume_game = true;
        Scanner in = new Scanner(System.in);
        String buffer = "";
        int y1, x1, y2, x2;
        //filling();
        while (true) {
            System.out.println(gamecore());
            cout_mass(mass);
            System.out.println(findcomdinations());
            resume_game = false;
            System.out.println();
            System.out.println(score + ":Score");
            System.out.println("it was not automatically possible to remove the balls, please enter the coordinates for the swap 1 -  y x   2 -  y x");
            System.out.print("1 - ");
            y1 = in.nextInt() - 1;
            x1 = in.nextInt() - 1;
            System.out.print("2 - ");
            y2 = in.nextInt() - 1;
            x2 = in.nextInt() - 1;

            if ((y1 > 0 && y1 <= y_define && x1 > 0 && x1 <= x_define && y2 > 0 && y2 <= y_define && x2 > 0 && x2 <= x_define) && (x1 == x2 || y1 == y2)) {
                if (Math.abs(y1 - y2) <= 1 && Math.abs(x1 - x2) <= 1) {
                    System.out.println("WOW YOU RIGHT!");
                    swap(y1, x1, y2, x2, mass);
                    resume_game = true;
                    cout_mass(mass);
                    boolean nextstep = gamecore();
                    if (!nextstep){
                        System.out.println("NOTHING CHANGE");
                        swap(y1, x1, y2, x2, mass);
                        resume_game = false;
                    } else {
                        System.out.println("CHANGE!!");
                    }
                }
            } else {
                System.out.println("ne proshel perviy if");
            }
        }
    }

    public static void main(String[] args) {
        MainClass game = new MainClass();
        game.startGame();
    }
}