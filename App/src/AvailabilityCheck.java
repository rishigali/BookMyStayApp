import java.util.*;

class AvailabilityCheck  {
    private String type;
    private double price;
    private String amenities;

    public AvailabilityCheck (String type, double price, String amenities) {
        this.type = type;
        this.price = price;
        this.amenities = amenities;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getAmenities() {
        return amenities;
    }

    @Override
    public String toString() {
        return "Type: " + type +
                ", Price: ₹" + price +
                ", Amenities: " + amenities;
    }
}

class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Deluxe", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int count) {
        if (count < 0) {
            System.out.println("Invalid update.");
            return;
        }
        inventory.put(roomType, count);
    }

    public boolean bookRoom(String roomType) {
        int available = getAvailability(roomType);

        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        } else {
            return false;
        }
    }
}

class SearchService {
    private RoomInventory inventory;
    private Map<String, Room> roomCatalog;

    public SearchService(RoomInventory inventory, Map<String, Room> roomCatalog) {
        this.inventory = inventory;
        this.roomCatalog = roomCatalog;
    }

    public void searchAvailableRooms() {
        System.out.println("\n--- Available Rooms ---");

        boolean found = false;

        for (String roomType : roomCatalog.keySet()) {
            int available = inventory.getAvailability(roomType);

            if (available > 0) {
                Room room = roomCatalog.get(roomType);
                System.out.println(room + ", Available: " + available);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms available.");
        }
    }
}

public class Main {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        Map<String, Room> roomCatalog = new HashMap<>();
        roomCatalog.put("Single", new Room("Single", 2000, "WiFi, AC"));
        roomCatalog.put("Double", new Room("Double", 3500, "WiFi, AC, TV"));
        roomCatalog.put("Deluxe", new Room("Deluxe", 5000, "WiFi, AC, TV, Mini Bar"));

        SearchService searchService = new SearchService(inventory, roomCatalog);

        searchService.searchAvailableRooms();

        inventory.bookRoom("Single");
        inventory.bookRoom("Deluxe");
        inventory.updateAvailability("Double", 0);

        searchService.searchAvailableRooms();
    }
}