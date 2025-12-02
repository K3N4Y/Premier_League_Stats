package proyecto.plstats.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // Regresa un alista con todos los jugadores
    public List<Player> getPlayers(){
        return playerRepository.findAll();
    }

    // Regresa una lista de jugadores por su equipo
    public List<Player> getPlayersByTeam(String teamName){
        return playerRepository.findAll().stream()
                .filter(player -> teamName.equals(player.getTeam()))
                .collect(Collectors.toList());
    }

    // Regresa una lista de jugadores por su nombre
    public List<Player> getPlayersByName(String searchText){
        return playerRepository.findAll().stream()
                .filter(player -> player.getName().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Regresa una lista de jugadores por su posición
    public List<Player> getPlayersByPosition(String searchText){
        return playerRepository.findAll().stream()
                .filter(player -> player.getPos().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Regresa una lista de jugadores por su nacionalidad
    public List<Player> getPlayerByNation(String searchText){
        return playerRepository.findAll().stream()
                .filter(player -> player.getNation().toLowerCase().contains(searchText.toLowerCase()))
                .collect(Collectors.toList());
    }

    // Regresa una lista de jugadores por su posición y su equipo
    public List<Player> getPlayersByPositionAndTeam(String team, String position){
        return playerRepository.findAll().stream()
                .filter(player -> team.equals(player.getTeam()) && position.equals(player.getPos()))
                .collect(Collectors.toList());
    }

    // Agrega un nuevo jugador
    public Player addPlayer(Player player){
        playerRepository.save(player);
        return player;
    }


}
