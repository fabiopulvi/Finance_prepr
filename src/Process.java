import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * Created by Fabio on 18/06/2016.
 */
public class Process {
    public static void main(String[] args) {
        String test = "10:2 21:3;10:2 34:2 56:4";
        System.out.println(test);
        String[] seq =test.split(";");

        ArrayList<ArrayList<String>>  row_in_Seq = new ArrayList<ArrayList<String>>();
        int initial_min=999999999;
        for (String s: seq) {
            String[] items = s.split(" ");

            ArrayList<String> temp = new ArrayList<String>();
            for (String item: items) {
                temp.add(item);
                int value = Integer.parseInt(item.split(":")[1]);
                if (value<initial_min) initial_min=value;
            }
            row_in_Seq.add(temp);

        }

        print_and_scale(row_in_Seq,initial_min);
    }

    private static void print_and_scale(ArrayList<ArrayList<String>> row_in_seq, int initial_min) {
        String to_print="";
        ArrayList<ArrayList<String>>  row_in_Seq_inner = new ArrayList<ArrayList<String>>();
        int initial_min_inner=99999999;
        boolean proceed = false;
        for (ArrayList<String> list : row_in_seq) {
            String seq="";
            ArrayList<String> temp = new ArrayList<String>();
            for (String s: list) {
                String[] temp_item=s.split(":");
                seq+=temp_item[0]+" ";
                temp_item[1]=Integer.toString(Integer.parseInt(temp_item[1])-initial_min);
                if (Integer.parseInt(temp_item[1])>0) {
                    temp.add(temp_item[0]+":"+temp_item[1]);
                    proceed=true;
                }
                if (Integer.parseInt(temp_item[1])<initial_min_inner) initial_min_inner=Integer.parseInt(temp_item[1]);
            }
            if (seq.length()>0) {to_print+=seq.substring(0,seq.length()-1)+";";

            if (temp.size()>0) row_in_Seq_inner.add(temp);}
        }
        for (int i=0;i<initial_min;i++) System.out.println("Final print:"+to_print);

        if (proceed) print_and_scale(row_in_Seq_inner,initial_min_inner);

    }
}
