import java.util.Random;
import java.util.Scanner;

public class MainClass {
    public MainClass gameClass;
    final public int  x_define = 5;  //5=6it   //7=8it
    final public int  y_define = 5;  //5=6it   //7=8it
    final public char UNFILLED = '*';
    public int score = 0;
    final Random random = new Random();

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
            if (((buffer.charAt(curr_pos) == buffer.charAt(curr_pos + 1)) && (buffer.charAt(curr_pos) == buffer.charAt(curr_pos + 2))) && (buffer.charAt(curr_pos)  != UNFILLED && buffer.charAt(curr_pos + 1)  != UNFILLED && buffer.charAt(curr_pos + 2)  != UNFILLED)) {
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
            for (int y = y_define; y > 0; y--) {
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

    boolean delete_column() { //mass_after_column_search
        String buffer = "";
        boolean buff = false;

        for (int x = 0; x < x_define; x++) {
            for (int y = 0; y < y_define; y++) {
                buffer = buffer + mass[y][x];
            }
            buffer = check_repeat(buffer);
            for (int y = 0; y < y_define; y++) {
                mass_after_column_search[y][x] = buffer.charAt(y);
            }
            if (buffer.charAt(x) == '*') {
                buff = true;
            }
            buffer = "";
        }
        return buff;
    }

    boolean delete_string() {
        boolean buff = false;
        String buffer = "";

        for (int y = 0; y < y_define; y++) {
            for (int x = 0; x < x_define; x++) {
                buffer = buffer + mass[y][x];
            }
            buffer = check_repeat(buffer);
            for (int x = 0; x < x_define; x++) {
                mass_after_string_search[y][x] = buffer.charAt(x);
            }
            if (buffer.charAt(y) == '*') {
                buff = true;
            }
            buffer="";
        }
        return buff;
    }

    void comparison() {
        for (int x = 0; x < x_define; x++) {
            for (int y = 0; y < y_define; y++) {
                if ((mass_after_column_search[y][x] != mass_after_string_search[y][x]) || ((mass_after_string_search[y][x] == '*') && (mass_after_column_search[y][x] == '*'))) {
                    mass[y][x] = '*';
                }
            }
        }
    }

    boolean filling() {
        boolean edit = false;
        for (int x = 0; x <= x_define; x++) {
            for (int y = 0; y <= y_define; y++) {
                if (mass[y][x] == UNFILLED) {
                    mass[y][x] = ((char) ((random.nextInt(9) + 1) + '0')); //ot 1 do 9
                    edit = true;
                }
            }
        }
        return edit;
    }

    void swap(int y1, int x1, int y2, int x2) {
        char buff = ' ';
        buff = mass[y1 - 1][x1 - 1];
        mass[y1 - 1][x1 - 1] = mass[y2 - 1][x2 - 1];
        mass[y2 - 1][x2 - 1] = buff;
    }

    boolean findcomdinations() {
        duplicate = mass;
        boolean boolbuffstring = false;
        boolean boolbuffcolumn = false;
        /*for (int x = 0; x < x_define; x++) {
            for (int y = 0; y < y_define; y++) {
                duplicate[y][x] = mass[y][x];
            }
        }*/
        for (int x = 1; x <= x_define; x++) {
            for (int y = 1; y <= y_define; y++) {
                if (y + 1 <= y_define) {
                    swap(y, x, y + 1, x);
                    boolbuffstring = delete_string();
                    boolbuffcolumn = delete_column();
                }
                if (boolbuffstring || boolbuffcolumn ) {
                    return true;
                }
                else {
                    if (y + 1 <= y_define)
                        swap(y + 1, x, y, x);
                }
            }
        }
        for (int y = 1; y <= y_define; y++) {
            for (int x = 1; x <= x_define; x++) {
                if (x + 1 <= x_define) {
                    swap(y, x, y, x + 1);
                    boolbuffstring = delete_string();
                    boolbuffcolumn = delete_column();
                }
                if (boolbuffstring || boolbuffcolumn) {
                    return true;
                }
                else {
                    if (x + 1 <= x_define) {
                        swap(y, x + 1, y, x);
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
            swap(y1, x1, y2, x2);
            end--;
            if (end == 0) {
                break;
            }
        }
    }

    boolean end_of_the_game() {
        int answer;
        Scanner in = new Scanner(System.in);
        System.out.println("YOU LOSE!   YOU SCORE: " + score);
        System.out.println("DO YOU WANT TO START THE GAME?    YES - 1    NO - 0 (press any key)");
        answer = in.nextInt();

        if (answer == 1)
        {
            return true;
        }
        else {
            return false;
        }
    }

    void cout_mass() {
        String buffer = "";
        for (int y = 0; y <= y_define; y++) {
            for (int x = 0; x <= x_define; x++) {
                buffer = buffer + mass[y][x];
            }
            System.out.println(buffer);
            buffer = "";
        }
    }

    boolean gamecore() {
        delete_string();
        delete_column();
        comparison();
        gravitation();
        return filling();
    }

    public void startGame(){
        Scanner in = new Scanner(System.in);


        String buffer = "";
        int y1, x1, y2, x2;
        filling();
        // Game.cout_mass(mass);
        boolean i = false;
        do {
            i = gamecore();
        } while (i);
        if (!findcomdinations()) {
            int countner = 0;
            do {
                magicshake();
                do {
                    if (!gamecore())
                    {
                        break;
                    }
                } while (true);
                countner++;
                if (countner == 100) {
                    if (end_of_the_game())
                    {
                        score = 0;
                    }
                    else {
                        System.exit(0);
                    }
                }
            } while (!findcomdinations());
        }
        cout_mass();
        while (true) {
            System.out.println();
            System.out.println(score + ":Score");
            System.out.println("it was not automatically possible to remove the balls, please enter the coordinates for the swap 1 -  y x   2 -  y x");
            System.out.print("1 - ");
            y1 = in.nextInt();
            x1 = in.nextInt();
            System.out.print("2 - ");
            y2 = in.nextInt();
            x2 = in.nextInt();

            if ((y1 > 0 && y1 <= y_define && x1 > 0 && x1 <= x_define && y2 > 0 && y2 <= y_define && x2 > 0 && x2 <= x_define) && (x1 == x2 || y1 == y2)) {
                if (Math.abs(y1 - y2) <= 1 && Math.abs(x1 - x2) <= 1) {
                    swap(y1, x1, y2, x2);
                }
                else {
                    if (end_of_the_game())
                    {
                        score = 0;
                    }
                    else {
                        System.exit(0);
                    }
                }
            }
            else {
                if (end_of_the_game())
                {
                    score = 0;
                }
                else {
                    System.exit(0);
                }
            }
            if (!gamecore()) {
                swap(y2, x2, y1, x1);
                score--;
            }
            if (findcomdinations()) {
                int countner = 0;
                do {
                    magicshake();
                    do {
                        if (gamecore()) break;
                    } while (true);
                    countner++;
                    if (countner == 100) {
                        if (end_of_the_game())
                        {
                            score = 0;
                            break;
                        }
                        else {
                            System.exit(0);
                        }
                    }
                } while (!findcomdinations());
            }
            cout_mass();
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        MainClass game = new MainClass();
        game.startGame();
    }
}