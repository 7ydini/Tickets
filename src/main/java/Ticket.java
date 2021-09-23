public class Ticket {
    private String ticketStartPoint;
    private String ticketEndPoint;
    private int minutes;

    public Ticket(String ticketStartPoint, String ticketEndPoint, int minutes) {
        this.ticketStartPoint = ticketStartPoint;
        this.ticketEndPoint = ticketEndPoint;
        this.minutes = minutes;
    }
    public Ticket(){

    }

    public String getTicketStartPoint() {
        return ticketStartPoint;
    }

    public void setTicketStartPoint(String ticketStartPoint) {
        this.ticketStartPoint = ticketStartPoint;
    }

    public String getTicketEndPoint() {
        return ticketEndPoint;
    }

    public void setTicketEndPoint(String ticketEndPoint) {
        this.ticketEndPoint = ticketEndPoint;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    public String toString(){
        return "Ticket StartPoint: " + ticketStartPoint + ", EndPoint: " + ticketEndPoint + ", time in flight: " + minutes + " min.";
    }
}
