public class BookMyStayApp {
    import java.util.*;

    // Room Class
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

    // Booking Class
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

    // Hotel Management System
    class HotelManagementSystem {

        // Data Structures
        private Map<Integer, Room> rooms = new HashMap<>();
        private Queue<String> bookingQueue = new LinkedList<>();
        private Set<Integer> bookedRooms = new HashSet<>();
        private List<Booking> bookingHistory = new ArrayList<>();

        // Add Room
        public void addRoom(int roomId, String type) {
            rooms.put(roomId, new Room(roomId, type));
        }

        // Add booking request (FIFO)
        public void requestBooking(String userName) {
            bookingQueue.add(userName);
            System.out.println(userName + " added to booking queue.");
        }

        // Process booking request
        public void processBooking() {
            if (bookingQueue.isEmpty()) {
                System.out.println("No booking requests.");
                return;
            }

            String user = bookingQueue.poll();

            for (Room room : rooms.values()) {
                if (room.isAvailable && !bookedRooms.contains(room.roomId)) {

                    // Allocate room
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

        // Cancel booking
        public void cancelBooking(int roomId) {
            if (!bookedRooms.contains(roomId)) {
                System.out.println("Room not booked.");
                return;
            }

            bookedRooms.remove(roomId);
            rooms.get(roomId).isAvailable = true;

            System.out.println("Booking cancelled for Room " + roomId);
        }

        // Display rooms
        public void displayRooms() {
            for (Room room : rooms.values()) {
                System.out.println(room);
            }
        }

        // Display booking history
        public void displayBookings() {
            for (Booking b : bookingHistory) {
                System.out.println(b);
            }
        }
    }

    // Main Class
    public class Main {
        public static void main(String[] args) {

            HotelManagementSystem system = new HotelManagementSystem();

            // Add rooms
            system.addRoom(101, "Single");
            system.addRoom(102, "Double");
            system.addRoom(103, "Deluxe");

            // Booking requests (FIFO)
            system.requestBooking("Alice");
            system.requestBooking("Bob");
            system.requestBooking("Charlie");

            // Process bookings
            system.processBooking();
            system.processBooking();
            system.processBooking();

            System.out.println("\n--- Rooms ---");
            system.displayRooms();

            System.out.println("\n--- Booking History ---");
            system.displayBookings();

            // Cancel booking
            System.out.println("\n--- Cancel Booking ---");
            system.cancelBooking(102);

            System.out.println("\n--- Rooms After Cancellation ---");
            system.displayRooms();
        }
    }
}
