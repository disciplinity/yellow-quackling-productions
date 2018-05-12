package network.manager;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import lombok.Data;
import network.database.entity.HeroSetupEntity;

import java.util.ArrayList;

/**
 * This class is a convenient place to keep things common(e.g. protocols) to both the client and server.
 */
public class NetworkManager {
    static public final int port = 54555;

    /**
     * This registers objects that are going to be sent over the network.
     * @param endPoint Client or Server (network Node)
     */
    static public void register(EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(CheckCredentialRequest.class);
        kryo.register(SessionTokenResponse.class);
        kryo.register(JoinBattleRequest.class);
        kryo.register(PlayerCombatInfo.class);
        kryo.register(HeroSetupEntity.class);
        kryo.register(ArrayList.class);
        kryo.register(BeginBattleResponse.class);
    }

    /* Protocols: */

    /* Authorization */
    /**
     * Send player credential to the server to check authorization validity.
     */
    @Data
    static public class CheckCredentialRequest {
        private String username;
        private String password;
    }

    /**
     * Return to player Session token after successful authorization.
     */
    @Data
    static public class SessionTokenResponse {
        private String userToken;
    }

    /* Battle connection */
    /**
     * Join room to start battle.
     */
    @Data
    static public class JoinBattleRequest {
        private String userToken;
    }

    /**
     * Room is filled, two players are found. Now screens with appropriate data could be drawn.
     */
    @Data
    static public class BeginBattleResponse {
        private PlayerCombatInfo playerCombatInfo;
        private PlayerCombatInfo opponentCombatInfo;
    }

    /* In battle actions */
    /**
     * Request to perform an action of dealing damage.
     */
    @Data
    static public class DealDamageRequest {
        private String dealerUserToken;
        private int dealerSlotId;
        private int targetSlotId;
        private int castedSpellId;
    }

    /**
     * Return Deal Damage action processed result.
     */
    @Data
    static public class DealDamageResponse {
        private int dealerSlotId;
        private int targetSlotId;
        private int castedSpellId;
        private double dealtDamage;
    }

    /**
     * When player finishes turn.
     */
    @Data
    static public class TurnEndRequest {
        private String endTurnPlayerToken;
    }

    /**
     * When either player finished his/her turn this is sent from the server.
     */
    @Data
    static public class TurnBeginResponse {
        private String whosTurnPlayerToken;
    }
}