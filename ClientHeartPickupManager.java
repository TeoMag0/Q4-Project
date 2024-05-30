public class ClientHeartPickupManager {
    private static MyHashTable<Vector2, HeartPickup> hearts = new MyHashTable<>(20);

    public static void removeHeart(Vector2 position){
        if(hearts.get(position) != null){
            HeartPickup h = hearts.remove(position);
            h.destroySelf();
        }
    }

    public static void addHeart(Vector2 position){
        hearts.put(position.clone(), new HeartPickup(position));
    }

    public static void deleteHearts(){
        while(hearts.keySet().toDLList().size() != 0){
            Vector2 loc = hearts.keySet().toDLList().get(0);
            hearts.remove(loc);
        }
    }
}
