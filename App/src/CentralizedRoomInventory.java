
import java.util.HashMap;
import java.util.Map;

    public class CentralizedRoomInventory {
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
                System.out.println("Invalid update. Count cannot be negative.");
                return;
            }

            inventory.put(roomType, count);
            System.out.println("Updated " + roomType + " rooms to " + count);
        }
        public boolean bookRoom(String roomType) {
            int available = getAvailability(roomType);

            if (available > 0) {
                inventory.put(roomType, available - 1);
                System.out.println(roomType + " room booked successfully.");
                return true;
            } else {
                System.out.println("No " + roomType + " rooms available.");
                return false;
            }
        }
        public void displayInventory() {
            System.out.println("\n--- Room Inventory ---");
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }
}
