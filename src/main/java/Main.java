import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static List<Ticket> tickets;
    public static void main(String[] args) throws IOException {
        tickets = new LinkedList<>();//List наших рейсов
        String json = String.join("", Files.readAllLines(Paths.get("src/tickets.json"), Charset.defaultCharset()));//Читаем JSON
        //System.out.println(json);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = new JSONArray(jsonObject.getJSONArray("tickets"));
        for (Object jsonTicket :
                jsonArray) {
            Ticket ticket = new Ticket();
            ticket.setTicketStartPoint( ((JSONObject) jsonTicket).getString("ticketStartPoint"));
            ticket.setTicketEndPoint( ((JSONObject) jsonTicket).getString("ticketEndPoint"));
            ticket.setMinutes(Integer.parseInt(((JSONObject) jsonTicket).getString("minutes")));
            //System.out.println(ticket.toString());
            tickets.add(ticket);
        }//Мы заполнили наш список с рейсами
        System.out.println("Average time: " + avgTimeBetween("Tel Aviv", "Vladivostok"));//Выводим среднее время полёта между 2мя городами
        System.out.println("Percentile time: " + percentileTimeBetween(90, "Tel Aviv", "Vladivostok"));

    }

    private static String avgTimeBetween(String firstCity, String secondCity) {
        int count = 0;
        int sumMinutes = 0;
        for (Ticket ticket :
                tickets) {
            if(ticket.getTicketStartPoint().equals(firstCity) ||
                    ticket.getTicketStartPoint().equals(secondCity) &&
                            ticket.getTicketEndPoint().equals(firstCity) ||
                                    ticket.getTicketEndPoint().equals(secondCity)){
                count++;
                sumMinutes += ticket.getMinutes();
            }
        }
        if(count == 0){
            return "0";
        }
        int avg = sumMinutes / count;
        return String.valueOf(avg);//Использовал int, т.к. работал с минутами а не с часами
    }

    private static String percentileTimeBetween(double percent, String firstCity, String secondCity) {
        List<Integer> arr = new ArrayList<>();
        for (Ticket ticket :
                tickets) {
            if(ticket.getTicketStartPoint().equals(firstCity) ||
                    ticket.getTicketStartPoint().equals(secondCity) &&
                            ticket.getTicketEndPoint().equals(firstCity) ||
                    ticket.getTicketEndPoint().equals(secondCity)) {
                arr.add(ticket.getMinutes());
            }
        }
        int percentile = (int)Math.ceil(percent / 100 * arr.size());//округляем до целого(в бОльшую)
        //System.out.println(percentile +" = percentile.");
        return String.valueOf(arr.stream()
                .sorted()
                .collect(Collectors.toList())
                .get(percentile - 1));
    }

}
