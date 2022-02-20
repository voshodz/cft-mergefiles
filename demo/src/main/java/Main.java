import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Objects;
import java.util.Scanner;

class Main {
    public static ArrayList<String> reader(String fileName) throws Exception{
        FileReader fr = new FileReader(fileName);
        Scanner sc = new Scanner(fr);

        String input;
        ArrayList<String> res= new ArrayList<>();
        while(sc.hasNext() && !(input = sc.next()).equals("exit")) {
            res.add(input);
        }
        sc.close(); // Scanner is closed
        fr.close();
        return res;
    }
    public static void writeStringToFile(String output, String[] list ) throws IOException {
        File file = new File(output);
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        for(String str: list) {
            writer.write(str);
            writer.append('\n');
        }
        writer.flush();
        writer.close();
    }
    public  static void writeIntToFile(String output, int[] list) throws IOException {
        File file = new File(output);
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        for(int value: list) {
            writer.write(String.valueOf(value));
            writer.append('\n');
        }
        writer.flush();
        writer.close();
    }

    public static  int[] stringToInt(ArrayList<String> input) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (String s : input) {
            list.add(Integer.parseInt(s));
        }
        int[] res = new int[list.size()];
        for (int i=0; i < res.length; i++)
        {
            res[i] = list.get(i).intValue();
        }
        return res;
    }

    public static int[] simpleMergeInt(int[] list1, int[] list2) {
        int[] res = new int[list1.length + list2.length];
        int i = 0;
        int j= 0;
        int k = 0;
        while (i < list1.length && j < list2.length) {
            if (list1[i] < list2[j])
                res[k++] = list1[i++];
            else
                res[k++] = list2[j++];
        }
        while (i < list1.length)
            res[k++] = list1[i++];
        while (j < list2.length)
            res[k++] = list2[j++];
        return res;
    }

    public static String[] simpleMergeString(String[] list1, String[] list2) {
        String[] res = new String[list1.length + list2.length];
        int m = 0;
        int n = 0;
        for (int i = 0; i < res.length; i++) {
            if (n >= list2.length || (m < list1.length && list1[m].compareToIgnoreCase(list2[n]) < 0)) {
                res[i] = list1[m];
                m++;
            } else {
                res[i] = list2[n];
                n++;
            }
        }
        return res;
    }

    public static void main(String[] args)
            throws Exception {
        if(args.length > 0) {
            System.out.println(args[0]);
            if(Objects.equals(args[0], "-i")) {
                ArrayList<String> file1 = reader(args[2]);
                ArrayList<String> file2 = reader(args[3]);
                int[] resultInt =  simpleMergeInt(stringToInt(file1),stringToInt(file2));;
                writeIntToFile(args[1], resultInt);
            }
            else if(Objects.equals(args[0], "-s")) {
                ArrayList<String> file1 = reader(args[2]);
                ArrayList<String> file2 = reader((args[3]));
                String[] resultString = simpleMergeString( file1.toArray(new String[0]), file2.toArray(new String[0]));
                writeStringToFile(args[1], resultString);
            }
        }

    }
}
