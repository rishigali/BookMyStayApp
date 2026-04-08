public class BookMyStayApp {
    import java.util.*;

    class Room {
        int roomId;
        String type;
        boolean isAvailable;

        public Room(int roomId, String type) {
            this.roomId = roomId;
            this.type = type;
            this.isAvailable = true;
        }

        @Override
        public String toString() {
            return "Room ID: " + roomId + ", Type: " + type + ", Available: " + isAvailable;
        }
    }
    class Booking {
        String userName;
        int roomId;

        public Booking(String userName, int roomId) {
            this.userName = userName;
            this.roomId = roomId;
        }

        @Override
        public String toString() {
            return "User: " + userName + " -> Room: " + roomId;
        }
    }
    class HotelManagementSystem {
        private Map<Integer, Room> rooms = new HashMap<>();
        private Queue<String> bookingQueue = new LinkedList<>();
        private Set<Integer> bookedRooms = new HashSet<>();
        private List<Booking> bookingHistory = new ArrayList<>();
        public void addRoom(int roomId, String type) {
            rooms.put(roomId, new Room(roomId, type));
        }
        public void requestBooking(String userName) {
            bookingQueue.add(userName);
            System.out.println(userName + " added to booking queue.");
        }
        public void processBooking() {
            if (bookingQueue.isEmpty()) {
                System.out.println("No booking requests.");
                return;
            }

            String user = bookingQueue.poll();

            for (Room room : rooms.values()) {
                if (room.isAvailable && !bookedRooms.contains(room.roomId)) {
                    room.isAvailable = false;
                    bookedRooms.add(room.roomId);

                    Booking booking = new Booking(user, room.roomId);
                    bookingHistory.add(booking);

                    System.out.println("Booking successful: " + booking);
                    return;
                }
            }

            System.out.println("No rooms available for " + user);
        }
        public void cancelBooking(int roomId) {
            if (!bookedRooms.contains(roomId)) {
                System.out.println("Room not booked.");
                return;
            }

            bookedRooms.remove(roomId);
            rooms.get(roomId).isAvailable = true;

            System.out.println("Booking cancelled for Room " + roomId);
        }
        public void displayRooms() {
            for (Room room : rooms.values()) {
                System.out.println(room);
            }
        }
        public void displayBookings() {
            for (Booking b : bookingHistory) {
                System.out.println(b);
            }
        }
    }
    public class Main {
        public static void main(String[] args) {

            HotelManagementSystem system = new HotelManagementSystem();
            system.addRoom(101, "Single");
            system.addRoom(102, "Double");
            system.addRoom(103, "Deluxe");
            system.requestBooking("Alice");
            system.requestBooking("Bob");
            system.requestBooking("Charlie");
            system.processBooking();
            system.processBooking();
            system.processBooking();

            System.out.println("\n--- Rooms ---");
            system.displayRooms();

            System.out.println("\n--- Booking History ---");
            system.displayBookings();
            System.out.println("\n--- Cancel Booking ---");
            system.cancelBooking(102);

            System.out.println("\n--- Rooms After Cancellation ---");
            system.displayRooms();
        }
    }
}
