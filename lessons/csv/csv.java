package csv;

import java.io.*;
import java.util.*;

public class csv {
    public static void readCSVFile(){
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("results.csv"));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(records);
    }
    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(";");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }


    public static void writeToCSV(List<Map> objectList) {
        String CSV_SEPARATOR = ";";
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("results.csv"), "UTF-8"));
            for (Map objectDetails : objectList) {
                StringBuffer oneLine = new StringBuffer();
                Iterator it = objectDetails.values().iterator();

                while (it.hasNext()) {
                    Object value = it.next();

                    if(value !=null){
                        oneLine.append(value);
                    }

                    if (it.hasNext()) {
                        oneLine.append(CSV_SEPARATOR);
                    }
                }
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (UnsupportedEncodingException e) {
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }
    }

    public static void main(String[] args) {
        Map<String, String> map1 = new HashMap<String, String>() {
            {
                put("1.0", "Value 1");
                put("2.0", "Value 2");
                put("3.0", "Value 3");
            }
        };
        Map<String, Integer> map2 = new HashMap<String, Integer>() {
            {
                put("1.0", 100);
                put("2.0", 200);
                put("3.0", 123);
            }
        };
        Map<String, Integer> map3 = new HashMap<String, Integer>() {
            {
                put("1.0", 300);
                put("2.0", 400);
                put("3.0", 500);
            }
        };

        List<Map> list = new ArrayList();
        list.add(map1);
        list.add(map2);
        list.add(map3);
        csv.writeToCSV(list);
        csv.readCSVFile();
    }
}
