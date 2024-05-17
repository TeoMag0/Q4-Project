import java.io.*;
import java.net.*;

/*
 * purpose: manage sending/receiving packets
 * things to update: switch in while loop must be able to receive any type of data
 */

public class ConnectionManager {
    public static final ConnectionManager Singleton = new ConnectionManager();
    private ObjectOutputStream out;
	private int clientID;

    @SuppressWarnings("rawtypes")
    public void connect() throws IOException{
		String hostName = "localhost"; 
		int portNumber = 1024;
		Socket serverSocket = new Socket(hostName, portNumber);
        out = new ObjectOutputStream(serverSocket.getOutputStream());
		ObjectInputStream in = new ObjectInputStream(serverSocket.getInputStream());

		StartManager.Singleton.start();

		try {
			while (true) {
				Object receivedObject = in.readObject();
				NetworkObject received = (NetworkObject)receivedObject;

				switch(received.packet){
					case PLAYER_POS:
						//{int clientID, Vector2 pos}
						clientID = (int)((Object[])received.data)[0];
						Vector2 pos = (Vector2)((Object[])received.data)[1];
						DummyPlayerManager.Singleton.updatePosition(clientID, pos);
                        break;
					case PLAYER_STATUS:
						//receives {int clientID, boolean alive}
						clientID = (int)((Object[]) received.data)[0];
						boolean alive = (boolean)((Object[]) received.data)[1];
						DummyPlayerManager.Singleton.setAlive(clientID, alive);
						break;
					case DISCONNECTED_PLAYER:
						//receives int clientID
						DummyPlayerManager.Singleton.remove((int)received.data);
						break;
					case WAITING_PLAYERS:
						//receives {int waitingplayers, int maxplayers}
						WaitingForPlayersText waitingText = Screen.player.uiManager.waitingText();
						waitingText.setCurrentPlayerCount(((int[])received.data)[0]);
						waitingText.setMaxPlayers(((int[]) received.data)[1]);
						break;
					case GAME_STATE_CHANGE:
						//receives GameState gameState
						ClientGameManager.Singleton.changeState((GameState)received.data);
						break;
					case PLAYER_SPAWN_INDEX:
						//receives int spawnIndex
						PlayerSpawns.setSpawnIndex((int)received.data);
						break;
					case BOSS_ATTACK_START:
						//receives BossAttacks attack
						Screen.boss.attackManager.startAttack((BossAttacks)received.data);
						break;
					case BOSS_ATTACK_END:
						// receives BossAttacks attack
						Screen.boss.attackManager.stopAttack((BossAttacks) received.data);
						break;
					case BOSS_HEALTH:
						//receives in health
						Screen.boss.healthManager.setHealth((int)received.data);
						break;
					case BOSS_MAX_HEALTH:
						//receives int maxHealth
						Screen.boss.healthManager.setMaxHealth((int)received.data);
						break;
					case HASHMAP_POSITIONS:
						//receives Vector2[] positions
						Screen.boss.attackManager.setHashmapPositions((Vector2[])received.data);
						break;
					case PLAYER_COLOR:
						//receives int clientID
						Screen.player.setColor((int)received.data);
						break;
					case BOSS_INVULNERABLE:
						//receives boolean invulnerable
						Screen.boss.healthManager.setInvulnerable((boolean)received.data);
						break;
					case BOSS_QUOTE:
						//receives String quote
						Screen.boss.speechBubble.setQuote((String)received.data);
						break;
				}

				Screen.Singleton.repaint();
			}
		} catch (UnknownHostException e) {
			System.err.println("Host unkown: " + hostName);
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
        serverSocket.close();
	}

    @SuppressWarnings("rawtypes")
    public synchronized void sendObject(NetworkObject obj){
        try{
            out.writeObject(obj);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
