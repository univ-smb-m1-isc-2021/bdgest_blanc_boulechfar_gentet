package com.example.bdgesttest.Api;

import com.example.bdgesttest.Service.BDGestService;
import com.example.bdgesttest.persistence.Album;
import com.example.bdgesttest.persistence.BDGestUser;
import com.example.bdgesttest.persistence.Contributor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
public class BDGestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final BDGestService bdGestService;

    public BDGestController(BDGestService albumService) {
        this.bdGestService = albumService;
    }

    @CrossOrigin
    @GetMapping(value = "/api/getAllAlbums")
    public List<Album> getAllAlbums() {
        logger.info("Service getAlllbums");
        return bdGestService.getAllAlbums();
    }

    @GetMapping(value = "/api/getAlbum")
    @ResponseBody
    public Optional<Album> getAlbum(@RequestParam Long id){
        logger.info("Service getAlbum");
        return bdGestService.getAlbum(id);
    }

    @GetMapping(value = "/api/getThreeAlbums")
    @ResponseBody
    public List<Album> getThreeAlbums(){
        logger.info("Service getThreeAlbums");
        List<Album> albums = bdGestService.getAllAlbums();
        int size = albums.size();
        if (size <= 3){
            return albums;
        } else {
            List<Album> albums_res = new ArrayList<>();
            Random r = new Random();
            while (albums_res.size() < 3){
                Album album = albums.get(r.nextInt(albums.size()));
                if (!albums_res.contains(album)){
                    albums_res.add(album);
                }
            }
            return albums_res;
        }
    }

    @GetMapping(value = "/api/addAlbum")
    public void addAlbum(String isbn, String title, String img, String serie, String num_serie, ArrayList<Contributor> contributorsList) {
        logger.info("Service addAlbum");
        bdGestService.addAlbum(isbn, title, img, serie, num_serie);
    }

    @GetMapping(value = "/api/scrapAlbum")
    public Album scrapAlbum(@RequestParam String url) throws IOException {
        logger.info("Service scrapAlbum");
        Album album = bdGestService.scrapAlbum(url);
        bdGestService.addAlbum(album.getIsbn(), album.getTitle(), album.getImg(), album.getSerie(), album.getNum_serie());
        return album;
    }

    @GetMapping(value = "/api/scrapSerie")
    public int scrapSerie(@RequestParam String url) throws IOException {
        logger.info("Service scrapSerie");
        int nbScraps = bdGestService.scrapSerie(url);
        return nbScraps;
    }

    @GetMapping(value = "/api/scrapNbAlbums")
    public int scrapNbAlbums(@RequestParam int nb) throws IOException, InterruptedException {
        logger.info("Service scrapNbAlbums");
        int nbScraps = bdGestService.scrapNbAlbums(nb);
        return nbScraps;
    }

    @GetMapping(value = "/api/addUser")
    public void addUser(@RequestParam String login, @RequestParam String password, @RequestParam String role) {
        logger.info("Service addUser");
        bdGestService.addUser(login, password, role);
    }

    @GetMapping(value = "/api/remUser")
    public boolean remUser(@RequestParam Long id_user) {
        logger.info("Service remUser");
        return bdGestService.remUser(id_user);
    }

    @GetMapping(value = "/api/check")
    public BDGestUser getUser(@RequestParam String login, @RequestParam String password) {
        logger.info("Service checkUser");
        BDGestUser bdGestUser = bdGestService.getUser(login);
        if (bdGestUser != null && bdGestUser.getPassword().equals(password)){
            return bdGestUser;
        } else {
            return new BDGestUser((long) -1);
        }
    }

    @GetMapping(value = "/api/addAlbumToUser")
    public boolean addAlbumToUser(@RequestParam Long id_user, @RequestParam Long id_album){
        logger.info("Service addAlbumToUser");
        return bdGestService.addAlbumToUser(id_user, id_album);
    }

    @GetMapping(value = "/api/remAlbumFromUser")
    public boolean remAlbumFromUser(@RequestParam Long id_user, @RequestParam Long id_album){
        logger.info("Service remAlbumFromUser");
        return bdGestService.remAlbumFromUser(id_user, id_album);
    }

    @GetMapping(value = "api/albumsByUserId")
    public Set<Album> albumsByUserId(@RequestParam Long id_user){
        logger.info("Service albumsByUserId");
        return bdGestService.getAlbumsById(id_user);
    }

}
