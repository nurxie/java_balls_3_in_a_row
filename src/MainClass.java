import java.util.Random;
import java.util.Scanner;

public class MainClass {
    public MainClass gameClass;
    final int  x_define = 5;  //5=6it   //7=8it
    final int  y_define = 5;  //5=6it   //7=8it
    final public char FILL = '#';
    final public char UNFILLED = '*';
    public int score = 0;
    final Random random = new Random();

    char[][] mass = new char[y_define][x_define];
    char[][] mass_after_string_search = new char[y_define][x_define];
    char[][] mass_after_column_search = new char[y_define][x_define];

    String del_repeat(String line, int curr_pos, char templ)
    {
        while (curr_pos < line.length() && line.charAt(curr_pos) == templ)
        {
            StringBuilder sb = new StringBuilder(line);
            sb.insert(curr_pos, UNFILLED);
            curr_pos++;
        }

        return line;
    }


    String check_repeat(String line)
    {
        int curr_pos = 0;
        while (curr_pos + 2 < line.length())
        {
            if (line.charAt(curr_pos) == line.charAt(curr_pos + 1) && line.charAt(curr_pos) == line.charAt(curr_pos + 2))
            {
                line = del_repeat(line, curr_pos, line.charAt(curr_pos));
                score++;
            }
            else
            {
                curr_pos++;
            }
        }

        return line;
    }

    void gravitation(char[][] answer) {
        for (int countner_of_faling = y_define - 1; countner_of_faling > 0; countner_of_faling--) {
            for (int y = y_define; y > 0; y--) {
                for (int x = 0; x < x_define; x++) {
                    if (answer[y][x] == '*') {
                        boolean enable = true;
                        int countner = 0;
                        while (enable == true) {
                            if ((y - countner - 1) >= 0) {
                                answer[y - countner][x] = answer[y - countner - 1][x];
                                answer[y - countner - 1][x] = '*';
                                countner++;
                            }
                            else {
                                enable = false;
                            }
                        }
                    }
                }
            }
        }
    }

    boolean delete_column(char[][] mass, char[][] mass_after_column_search) { //mass_after_column_search
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

    boolean delete_string(char[][] mass, char[][] mass_after_string_search) {
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

    void comparison(char[][] mass, char[][] mass_after_string_search, char[][] mass_after_column_search) {
        for (int x = 0; x < x_define; x++) {
            for (int y = 0; y < y_define; y++) {
                if ((mass_after_column_search[y][x] != mass_after_string_search[y][x]) || ((mass_after_string_search[y][x] == '*') && (mass_after_column_search[y][x] == '*'))) {
                    mass[y][x] = '*';
                }
            }
        }
    }

    boolean filling(char[][] answer) {
        boolean edit = false;
        for (int x = 0; x <= x_define; x++) {
            for (int y = 0; y <= y_define; y++) {
                if (answer[y][x] == '*') {
                    answer[y][x] = (char) ((random.nextInt(9) + 1) + '0'); //ot 1 do 9
                    edit = true;
                }
            }
        }
        return edit;
    }

    void swap(int y1, int x1, int y2, int x2, char[][] mass) {
        char buff = ' ';
        buff = mass[y1 - 1][x1 - 1];
        mass[y1 - 1][x1 - 1] = mass[y2 - 1][x2 - 1];
        mass[y2 - 1][x2 - 1] = buff;
    }

    boolean findcomdinations(char[][] mass) {
        char[][] duplicate = new char[y_define][x_define];
        char[][] mass_after_string_search = new char[y_define][x_define];
        char[][] mass_after_column_search = new char[y_define][x_define];
        duplicate = mass;
        boolean boolbuffstring = false;
        boolean boolbuffcolumn = false;
        for (int x = 0; x < x_define; x++) {
            for (int y = 0; y < y_define; y++) {
                duplicate[y][x] = mass[y][x];
            }
        }
        for (int x = 1; x <= x_define; x++) {
            for (int y = 1; y <= y_define; y++) {
                if (y + 1 <= y_define) {
                    swap(y, x, y + 1, x, duplicate);
                    boolbuffstring = delete_string(duplicate, mass_after_string_search);
                    boolbuffcolumn = delete_column(duplicate, mass_after_column_search);
                }
                if (boolbuffstring == true || boolbuffcolumn == true) {
                    return true;
                }
                else {
                    if (y + 1 <= y_define)
                        swap(y + 1, x, y, x, duplicate);
                }
            }
        }
        for (int y = 1; y <= y_define; y++) {
            for (int x = 1; x <= x_define; x++) {
                if (x + 1 <= x_define) {
                    swap(y, x, y, x + 1, duplicate);
                    boolbuffstring = delete_string(duplicate, mass_after_string_search);
                    boolbuffcolumn = delete_column(duplicate, mass_after_column_search);
                }
                if (boolbuffstring == true || boolbuffcolumn == true) {
                    return true;
                }
                else {
                    if (x + 1 <= x_define) {
                        swap(y, x + 1, y, x, duplicate);
                    }
                }
            }
        }
        return false;
    }

    void magicshake(char[][] mass) {
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

    boolean end_of_the_game() {
        int answer;
        Scanner in = new Scanner(System.in);
        System.out.println("YOU LOSE!   YOU SCORE: " + score);
        System.out.println("DO YOU WANT TO START THE GAME?    YES - y    NO - n (press any key)");
        answer = in.nextInt();

        if (answer == 'y')
        {
            return true;
        }
        else {
            return false;
        }
    }

    void cout_mass(char[][] mass) {
        String buffer = "";
        for (int y = 0; y <= y_define; y++) {
            for (int x = 0; x <= x_define; x++) {
                buffer = buffer + mass[y][x];
            }
            System.out.println(buffer);
            buffer = "";
        }
    }

    boolean gamecore(char[][] mass, char[][] mass_after_string_search, char[][] mass_after_column_search) {
        delete_string(mass, mass_after_string_search);
        delete_column(mass, mass_after_column_search);
        comparison(mass, mass_after_string_search, mass_after_column_search);
        gravitation(mass);
        return filling(mass);
    }

    public static void main(String[] args) {
        MainClass Game = new MainClass();
        Scanner in = new Scanner(System.in);
        char[][] mass = {
                {Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED},
                {Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED},
                {Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED},
                {Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED},
                {Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED},
                {Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED},
        };
        char[][] mass_after_string_search = {
                {Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED},
                {Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED},
                {Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED},
                {Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED},
                {Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED},
                {Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED},
        };
        char[][] mass_after_column_search = {
                {Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED},
                {Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED},
                {Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED},
                {Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED},
                {Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED},
                {Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED, Game.UNFILLED},
        };
        String buffer = "";
        int y1, x1, y2, x2;
        Game.filling(mass);
       // Game.cout_mass(mass);
        boolean i = false;
        do {
            i = Game.gamecore(mass, mass_after_string_search, mass_after_column_search);
        } while (i);
        if (!(Game.findcomdinations(mass))) {
            int countner = 0;
            do {
                Game.magicshake(mass);
                do {
                    if (!Game.gamecore(mass, mass_after_string_search, mass_after_column_search))
                    {
                        break;
                    }
                } while (true);
                countner++;
                if (countner == 100) {
                    if (Game.end_of_the_game())
                    {
                        Game.score = 0;
                    }
                    else {
                        System.exit(0);
                    }
                }
            } while (!Game.findcomdinations(mass));
        }
        Game.cout_mass(mass);
        while (true) {
            System.out.println();
            System.out.println(Game.score + ":Score");
            System.out.println("it was not automatically possible to remove the balls, please enter the coordinates for the swap 1 -  y x   2 -  y x");
            System.out.print("1 - ");
            y1 = in.nextInt();
            x1 = in.nextInt();
            System.out.print("2 - ");
            y2 = in.nextInt();
            x2 = in.nextInt();

            if ((y1 > 0 && y1 <= Game.y_define && x1 > 0 && x1 <= Game.x_define && y2 > 0 && y2 <= Game.y_define && x2 > 0 && x2 <= Game.x_define) && (x1 == x2 || y1 == y2)) {
                if (Math.abs(y1 - y2) <= 1 && Math.abs(x1 - x2) <= 1) {
                    Game.swap(y1, x1, y2, x2, mass);
                }
                else {
                    if (Game.end_of_the_game())
                    {
                        Game.score = 0;
                    }
                    else {
                        System.exit(0);
                    }
                }
            }
            else {
                if (Game.end_of_the_game())
                {
                    Game.score = 0;
                }
                else {
                    System.exit(0);
                }
            }
            if (!Game.gamecore(mass, mass_after_string_search, mass_after_column_search)) {
                Game.swap(y2, x2, y1, x1, mass);
                Game.score--;
            }
            if (!Game.findcomdinations(mass)) {
                int countner = 0;
                do {
                    Game.magicshake(mass);
                    do {
                        if (!Game.gamecore(mass, mass_after_string_search, mass_after_column_search)) break;
                    } while (true);
                    countner++;
                    if (countner == 100) {
                        if (Game.end_of_the_game())
                        {
                            Game.score = 0;
                            break;
                        }
                        else {
                            System.exit(0);
                        }
                    }
                } while (!Game.findcomdinations(mass));
            }
            Game.cout_mass(mass);
            System.exit(0);
        }
    }
}
