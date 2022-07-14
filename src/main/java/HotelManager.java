import java.util.ArrayList;
import java.util.List;

public class HotelManager {
    public static void main(String[] args) {
        Logger.getInstance().log("Managing hotel...");

        // create hotel floors
        HotelFloor fl1 = new HotelFloor();
        HotelFloor fl2 = new HotelFloor();
        HotelFloor fl3 = new HotelFloor();

        // create hotel rooms
        List<HotelRoom> hotelRooms = new ArrayList<HotelRoom>();
        HotelRoom rm101 = new HotelRoom(101);
        HotelRoom rm102 = new HotelRoom(102);
        HotelRoom rm103 = new HotelRoom(103);
        HotelRoom rm104 = new HotelRoom(104);
        HotelRoom rm105 = new HotelRoom(105);

        HotelRoom rm201 = new HotelRoom(201);
        HotelRoom rm202 = new HotelRoom(202);
        HotelRoom rm203 = new HotelRoom(203);
        HotelRoom rm204 = new HotelRoom(204);
        HotelRoom rm205 = new HotelRoom(205);

        HotelRoom rm301 = new HotelRoom(201);
        HotelRoom rm302 = new HotelRoom(202);
        HotelRoom rm303 = new HotelRoom(203);
        HotelRoom rm304 = new HotelRoom(204);
        HotelRoom rm305 = new HotelRoom(205);

        hotelRooms.add(rm101);
        hotelRooms.add(rm102);
        hotelRooms.add(rm103);
        hotelRooms.add(rm104);
        hotelRooms.add(rm105);

        hotelRooms.add(rm201);
        hotelRooms.add(rm202);
        hotelRooms.add(rm203);
        hotelRooms.add(rm204);
        hotelRooms.add(rm205);

        hotelRooms.add(rm301);
        hotelRooms.add(rm302);
        hotelRooms.add(rm303);
        hotelRooms.add(rm304);
        hotelRooms.add(rm305);

        // add hotel rooms to hotel floors
        fl1.addHotelRoom(rm101);
        fl1.addHotelRoom(rm102);
        fl1.addHotelRoom(rm103);
        fl1.addHotelRoom(rm104);
        fl1.addHotelRoom(rm105);

        fl2.addHotelRoom(rm201);
        fl2.addHotelRoom(rm202);
        fl2.addHotelRoom(rm203);
        fl2.addHotelRoom(rm204);
        fl1.addHotelRoom(rm205);

        fl2.addHotelRoom(rm301);
        fl2.addHotelRoom(rm302);
        fl2.addHotelRoom(rm303);
        fl2.addHotelRoom(rm304);
        fl1.addHotelRoom(rm305);
        // take actions on rooms and floors and examine your output to ensure you implemented the desired
        // behaviors

        HotelEmailService emailService = new HotelEmailService();
        HotelPushNotificationService notificationService = new HotelPushNotificationService();
        // initialize hotel email and notification services
        // ...
        hotelRooms.forEach((hotelRoom) -> {
            hotelRoom.addCheckinObserver(emailService);
            hotelRoom.addCheckinObserver(notificationService);
        });

        rm101.book("James");
        rm102.book("Jason");
        rm203.book("Joy");
        rm304.book("Mark");

        rm101.checkIn("James");
        rm102.checkIn("Jason");
        rm203.checkIn("Joy");
        rm304.checkIn("Mark");


    }
}

interface HotelRoomInterface {
    void book(String guestName);
    void clean();
}

class HotelRoom implements HotelRoomInterface {
    private int roomNumber;
    private List<RoomCheckinObserver> checkinObservers = new ArrayList<RoomCheckinObserver>();

    public HotelRoom(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void book(String guestName) {
        Logger.getInstance().log("Booked a room for " + guestName + " in room: " + roomNumber);
    }

    public void clean() {
        Logger.getInstance().log("Cleaned room: " + roomNumber);
    }

    public void addCheckinObserver(RoomCheckinObserver checkinObserver) {
        checkinObservers.add(checkinObserver);
    }

    public void removeCheckinObserver(RoomCheckinObserver checkinObserver) {
        checkinObservers.remove(checkinObserver);
    }

    public void checkIn(String guestName) {
        Logger.getInstance().log(guestName + "checked in room: " + roomNumber);
        checkinObservers.forEach((checkinObserver -> checkinObserver.update(guestName)));
    }
}

class HotelFloor implements HotelRoomInterface {
    private List<HotelRoomInterface> hotelRooms = new ArrayList<HotelRoomInterface>();

    public void book(String guestName) {
        hotelRooms.forEach(child -> {
            child.book(guestName);
        });
    }
    public void clean() {
        Logger.getInstance().log("Cleaning Entire Floor...");
        hotelRooms.forEach(child -> child.clean());
    }

    public void addHotelRoom(HotelRoomInterface hotelRoom) {
        hotelRooms.add(hotelRoom);
    }

}
