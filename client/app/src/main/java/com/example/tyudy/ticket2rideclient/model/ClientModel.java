package com.example.tyudy.ticket2rideclient.model;

import com.example.tyudy.ticket2rideclient.common.ColorENUM;
import com.example.tyudy.ticket2rideclient.common.cards.TrainCard;
import com.example.tyudy.ticket2rideclient.common.cities.City;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.interfaces.iObservable;
import com.example.tyudy.ticket2rideclient.interfaces.iObserver;
import com.example.tyudy.ticket2rideclient.common.TTRGame;
import com.example.tyudy.ticket2rideclient.common.User;

import java.util.ArrayList;

import java.util.TreeMap;

/**
 * Created by tyudy on 2/13/17.
 */

public class ClientModel implements iObservable {

    public static final ClientModel SINGLETON = new ClientModel();
    private ArrayList<TTRGame> mTTRGameList;
    private ArrayList<iObserver> obsList;
    private ArrayList<String> chatMsgs;
    private TreeMap<String, City> mCities;
    private String ipAddress;
    private User currentUser;
    private TTRGame mCurrentTTRGame;
    private ArrayList<City> allCities;
    private ArrayList<Path> allPaths;


    private ClientModel(){
        mTTRGameList = new ArrayList<>();
        obsList = new ArrayList<>();
        chatMsgs = new ArrayList<>();
        ipAddress = null;
        currentUser = null;
        mCurrentTTRGame = null;
        allCities = new ArrayList<>();
        allPaths = new ArrayList<>();
        initCitiesAndPaths();
    }

    /**
     * Add an observer to the list of observers stored inside this observable
     */
    public void addObserver(iObserver observer){

        obsList.add(observer);
    }

    /**
     * Tell all the Observers that changes were made.
     * i.e. call each ones .observe() method
     */
    @Override
    public void notifyObservers(){
        for (iObserver obs : obsList){
          obs.observe();
        }
        return;
    }

    /**
     * replace the gameList with the new one from the server.
     * Also update the currentGame becuase it could have changed.
     * @param TTRGameList - new games from the server
     */
    public void replaceGames(ArrayList<TTRGame> TTRGameList) {
        this.mTTRGameList = TTRGameList;
        if(getCurrentTTRGame() != null){
            this.mCurrentTTRGame = getTTRGameWithID(getCurrentTTRGame().getGameID());
        }
        this.notifyObservers();
    }

    /**
     * return a game at the given index
     * @param index - location that the game is stored in the chosen data structure
     */
    public TTRGame getGameAtIndex(int index){
        return mTTRGameList.get(index);
    }

    /**
     * @return - a list of all the games that the ClientModel is currently aware of.
     */
    public ArrayList<TTRGame> getTTRGameList(){
        return mTTRGameList;
    }


    public void setIpAddress(String addressParam){
        ipAddress = addressParam;
    }

    public void setCurrentUser(User u){
        currentUser = u;
    }

    public void setCurrentTTRGame(TTRGame g){
        mCurrentTTRGame = g;
    }

    public User getCurrentUser(){
        return currentUser;
    }


    public String getIpAddress(){
        return ipAddress;
    }

    public TTRGame getCurrentTTRGame(){
        return mCurrentTTRGame;
    }

    public void setObsList(ArrayList<iObserver> list) {
        this.obsList = list;
    }

    public ArrayList<String> getChatMsgs() { return chatMsgs; }

    public String getMostRecentMessage() { return chatMsgs.get(chatMsgs.size() - 1); }

    public void receiveNewChat(String chat){
        chatMsgs.add(chat);
        this.notifyObservers();
    }

    /**
     * @param ID - the unique game ID that we are a part of
     * @return - the game that has the given ID, else mCurrentTTRGame
     */
    public TTRGame getTTRGameWithID(int ID){

        if(ID == 0){
            return null;
        }

        for(TTRGame game: mTTRGameList){
            if(game.getGameID() == ID){
                return game;
            }
        }
        return mCurrentTTRGame;
    }


    public void removeObserver(iObserver observer) {
        obsList.remove(observer);
    }

    /**
     * Initialize all cities in the map with a name and location
     */
    private void initCitiesAndPaths(){
        /*
         * List of cities:
         * Atlanta
         * Boston
         * Calgary
         * Charleston
         * Chicago
         * Dallas
         * Denver
         * Duluth
         * El Paso
         * Helena
         * Houston
         * Kansas City
         * Las Vegas
         * Little Rock
         * LA
         * Miami
         * Monreal
         * Nashville
         * New Orleans
         * New York
         * Oklahoma City
         * Omaha
         * Phoenix
         * Pittsburgh
         * Portland
         * Raleigh
         * St Louis
         * Salt Lake
         * San Fran
         * Santa Fe
         * Sault St. Marie
         * Seattle
         * Toronto
         * Vancouver
         * Washington DC
         * Winnipeg
         */

        // Create All Cities
        City Atlanta = new City("Atlanta", .707f, .477f);
        City Boston = new City("Boston", .8941f, .1722f);
        City Calgary = new City("Calgary", 2314f, .0146f);
        City Charleston = new City("Charleston", .7838f, .4861f);
        City Chicago = new City("Chicago", .6402f, .2787f);
        City Dallas = new City("Dallas", .4589f, .5306f);
        City Denver = new City("Denver", .3204f, .338f);
        City Duluth = new City("Duluth", .5084f, .1796f);
        City El_Paso = new City("El Paso", .3108f, .5416f);
        City Helena = new City("Helena", .2917f, .1398f);
        City Houston = new City("Houston", .4814f, 5898f);
        City Kansas_City = new City("Kansas City", .5747f, .3454f);
        City Las_Vegas = new City("Las Vegas", .1616f, .3935f);
        City Little_Rock = new City("Little Rock", .5619f, .4666f);
        City Los_Angeles = new City("Los Angeles", .0822f, .4269f);
        City Miami = new City("Miami", .802f, .669f);
        City Montreal = new City("Montreal", .8412f, .1065f);
        City Nashville = new City("Nashville", .6644f, .4194f);
        City New_Orleans = new City("New Orleans", .5743f, .5731f);
        City New_York = new City("New York", .8648f, .2435f);
        City Oklahoma_City = new City("Oklahoma City", .4561f, .4398f);
        City Omaha = new City("Omaha", .4856f, .2744f);
        City Phoenix = new City("Phoenix", .1859f, .4755f);
        City Pittsburgh = new City("Pittsburgh", .7712f, .2809f);
        City Portland = new City("Portland", .0512f, .1113f);
        City Raleigh = new City("Raleigh", .7588f, .4162f);
        City St_Louis = new City("St Louis", .5774f, .3633f);
        City Salt_Lake = new City("Salt Lake", .2101f, .3142f);
        City Santa_Fe = new City("Santa Fe", .2963f, .4180f);
        City Sault_St_Marie = new City("Sault St Marie", .6810f, .1214f);
        City Seattle = new City("Seattle", .0822f, .0622f);
        City Toronto = new City("Toronto", .7278f, .1724f);
        City Vancouver = new City("Vancouver", .1358f, .0204f);
        City Washington_DC = new City("Washington DC", .8168f, .3235f);
        City Winnipeg = new City("Winnipeg", .4349f, .0399f);
        City San_Francisco = new City("San Francisco", .0355f, .2917f);

        // Add all cities into array
        allCities.add(Atlanta);
        allCities.add(Boston);
        allCities.add(Calgary);
        allCities.add(Charleston);
        allCities.add(Chicago);
        allCities.add(Dallas);
        allCities.add(Denver);
        allCities.add(Duluth);
        allCities.add(El_Paso);
        allCities.add(Helena);
        allCities.add(Houston);
        allCities.add(Kansas_City);
        allCities.add(Las_Vegas);
        allCities.add(Little_Rock);
        allCities.add(Los_Angeles);
        allCities.add(Miami);
        allCities.add(Montreal);
        allCities.add(Nashville);
        allCities.add(New_Orleans);
        allCities.add(San_Francisco);
        allCities.add(New_York);
        allCities.add(Oklahoma_City);
        allCities.add(Omaha);
        allCities.add(Phoenix);
        allCities.add(Pittsburgh);
        allCities.add(Portland);
        allCities.add(Raleigh);
        allCities.add(St_Louis);
        allCities.add(Salt_Lake);
        allCities.add(Santa_Fe);
        allCities.add(Sault_St_Marie);
        allCities.add(Seattle);
        allCities.add(Toronto);
        allCities.add(Vancouver);
        allCities.add(Washington_DC);
        allCities.add(Winnipeg);

        //Create all of the Paths NOTE: Their name is based on the two connected cities alphabetical order-------------------------------

        Path Calgary_to_Vancouver = new Path(ColorENUM.COLORLESS, 3, Calgary, Vancouver, "Calgary_to_Vancouver");
        Path Calgary_to_Winnipeg = new Path(ColorENUM.WHITE, 6, Calgary, Winnipeg, "Calgary_to_Winnipeg");
        Path Calgary_to_Helena = new Path(ColorENUM.COLORLESS, 4, Calgary, Helena, "Calgary_to_Helena");
        //Path Calgary_to_Seattle = new Path(Color.COLORLESS, 4, Calgary, Seattle);     NOT IMPLEMENTED IN OUR MAP
        Path Dallas_to_ElPaso = new Path(ColorENUM.RED, 4, Dallas, El_Paso, "Dallas_to_ElPaso");
        Path Dallas_to_OklahomaCity = new Path(ColorENUM.COLORLESS, 2, Dallas, Oklahoma_City, "Dallas_to_OklahomaCity");
        Path Dallas_to_Houston = new Path(ColorENUM.COLORLESS, 1, Dallas, Houston, "Dallas_to_Houston");
        Path Denver_to_Phoenix = new Path(ColorENUM.WHITE, 5, Denver, Phoenix, "Denver_to_Phoenix");
        Path Denver_to_SaltLake = new Path(ColorENUM.YELLOW, 3, Denver, Salt_Lake, "Denver_to_SaltLake");
        Path Denver_to_OklahomaCity = new Path(ColorENUM.RED, 4, Denver, Oklahoma_City, "Denver_to_OklahomaCity");
        Path Denver_to_KansasCity = new Path(ColorENUM.BLACK, 4, Denver, Kansas_City, "Denver_to_KansasCity");
        Path Denver_to_SantaFe = new Path(ColorENUM.COLORLESS, 2, Denver, Santa_Fe, "Denver_to_SantaFe");
        Path Denver_to_Omaha = new Path(ColorENUM.PURPLE, 4, Denver, Omaha, "Denver_to_Omaha");
        Path Denver_to_Helena = new Path(ColorENUM.GREEN, 4, Denver, Helena, "Denver_to_Helena");
        Path Duluth_to_Helena = new Path(ColorENUM.ORANGE, 6, Duluth, Helena, "Duluth_to_Helena");
        Path Duluth_to_Omaha = new Path(ColorENUM.COLORLESS, 2, Duluth, Omaha, "Duluth_to_Omaha");
        Path ElPaso_Houston = new Path(ColorENUM.GREEN, 6, El_Paso, Houston, "ElPaso_Houston");
        Path ElPaso_to_OklahomaCity = new Path(ColorENUM.YELLOW, 5, El_Paso, Oklahoma_City, "ElPaso_to_OklahomaCity");
        Path ElPaso_to_SantaFe = new Path(ColorENUM.COLORLESS, 2, El_Paso, Santa_Fe, "ElPaso_to_SantaFe");
        Path ElPaso_to_LosAngeles = new Path(ColorENUM.BLACK, 6, El_Paso, Los_Angeles, "ElPaso_to_LosAngeles");
        Path ElPaso_to_Phoenix = new Path(ColorENUM.COLORLESS, 3, El_Paso, Phoenix, "ElPaso_to_Phoenix");
        Path Helena_to_Seattle = new Path(ColorENUM.YELLOW, 6, Seattle, Helena, "Helena_to_Seattle");
        Path Helena_to_Winnipeg = new Path(ColorENUM.BLACK, 4, Helena, Winnipeg, "Helena_to_Winnipeg");
        Path Helena_to_SaltLake = new Path(ColorENUM.PURPLE, 3, Helena, Salt_Lake, "Helena_to_SaltLake");
        Path Helena_to_Omaha = new Path(ColorENUM.RED, 5, Helena, Omaha, "Helena_to_Omaha");
        Path KansasCity_to_Omaha = new Path(ColorENUM.COLORLESS, 1, Kansas_City, Omaha, "KansasCity_to_Omaha");
        Path KansasCity_to_OklahomaCity = new Path(ColorENUM.COLORLESS, 2, Kansas_City, Oklahoma_City, "KansasCity_to_OklahomaCity");
        Path LasVegas_to_SaltLake = new Path(ColorENUM.ORANGE, 3, Las_Vegas, Salt_Lake, "LasVegas_to_SaltLake");
        Path OklahomaCity_to_SantaFe = new Path(ColorENUM.BLUE, 3, Oklahoma_City, Santa_Fe, "OklahomaCity_to_SantaFe");
        Path Phoenix_to_SantaFe = new Path(ColorENUM.COLORLESS, 3, Phoenix, Santa_Fe, "Phoenix_to_SantaFe");
        Path Portland_to_Seattle = new Path(ColorENUM.COLORLESS, 1, Portland, Seattle, "Portland_to_Seattle");
        Path Portland_to_SanFrancisco = new Path(ColorENUM.GREEN, 5, Portland, San_Francisco, "Portland_to_SanFrancisco");
        Path Portland_to_SaltLake = new Path(ColorENUM.BLUE, 6, Portland, Salt_Lake, "Portland_to_SaltLake");
        //Path SaltLake_to_SanFrancisco = new Path(Color.)      NOT IMPLEMENTED IN OUR MAP
        Path Seattle_to_Vancouver = new Path(ColorENUM.COLORLESS, 1, Seattle, Vancouver, "Seattle_to_Vancouver");
        
        
        
        Path Atlanta_to_Raleigh = new Path(ColorENUM.COLORLESS, 2, Atlanta, Raleigh, "Atlanta_to_Raleigh");
        Path Atlanta_to_Charleston = new Path(ColorENUM.COLORLESS, 2, Atlanta, Charleston, "Atlanta_to_Charleston");
        Path Atlanta_to_Nashville = new Path(ColorENUM.COLORLESS, 1, Atlanta, Nashville, "Atlanta_to_Nashville");
        Path Atlanta_to_New_Orleans = new Path(ColorENUM.ORANGE, 4,   Atlanta,New_Orleans, "Atlanta_to_New_Orleans");
        Path Atlanta_to_Miami = new Path(ColorENUM.BLUE, 5,   Atlanta, Miami, "Atlanta_to_Miami");
        Path Boston_to_Montreal = new Path(ColorENUM.COLORLESS, 2, Boston, Montreal, "Boston_to_Montreal");
        Path Boston_to_New_York = new Path(ColorENUM.RED, 2, Boston,  New_York, "Boston_to_New_York");
        Path Charleston_to_Raleigh = new Path(ColorENUM.COLORLESS, 2, Charleston, Raleigh, "Charleston_to_Raleigh");
        Path Charleston_to_Miami = new Path(ColorENUM.PURPLE, 4, Charleston, Miami, "Charleston_to_Miami");
        Path Chicago_to_Toronto = new Path(ColorENUM.WHITE, 4, Chicago, Toronto, "Chicago_to_Toronto");
        Path Chicago_to_Duluth = new Path(ColorENUM.RED, 3, Chicago, Duluth, "Chicago_to_Duluth");
        Path Chicago_to_Omaha = new Path(ColorENUM.BLUE, 4, Chicago, Omaha, "Chicago_to_Omaha");
        Path Chicago_to_St_Louis = new Path(ColorENUM.GREEN, 2, Chicago, St_Louis, "Chicago_to_St_Louis");
        Path Chicago_to_Pittsburgh = new Path(ColorENUM.BLACK, 3, Chicago, Pittsburgh, "Chicago_to_Pittsburgh");
        Path Dallas_to_Little_Rock = new Path(ColorENUM.COLORLESS, 2,  Dallas, Little_Rock, "Dallas_to_Little_Rock");
        Path Duluth_to_Winnipeg = new Path(ColorENUM.BLACK,4, Duluth, Winnipeg, "Duluth_to_Winnipeg");
        Path Duluth_to_Sault_St_Marie = new Path(ColorENUM.COLORLESS,3, Duluth, Sault_St_Marie, "Duluth_to_Sault_St_Marie");
        Path Duluth_to_Toronto = new Path(ColorENUM.PURPLE, 6, Duluth, Toronto, "Duluth_to_Toronto");
        Path Houston_to_New_Orleans = new Path(ColorENUM.COLORLESS, 2,   Houston, New_Orleans, "Houston_to_New_Orleans");
        Path Kansas_City_to_St_Louis = new Path(ColorENUM.PURPLE, 2,  Kansas_City, St_Louis, "Kansas_City_to_St_Louis");
        Path Little_Rock_to_Nashville = new Path(ColorENUM.WHITE, 3,  Little_Rock, Nashville, "Little_Rock_to_Nashville");
        Path Little_Rock_to_New_Orleans = new Path(ColorENUM.GREEN, 3,  Little_Rock, New_Orleans, "Little_Rock_to_New_Orleans");
        Path Little_Rock_to_St_Louis = new Path(ColorENUM.COLORLESS, 2,  Little_Rock, St_Louis, "Little_Rock_to_St_Louis");
        Path Little_Rock_to_Oklahoma_City = new Path(ColorENUM.COLORLESS, 2,  Little_Rock, Oklahoma_City, "Little_Rock_to_Oklahoma_City");
        Path Montreal_to_Sault_St_Marie = new Path(ColorENUM.BLACK, 5, Montreal, Sault_St_Marie, "Montreal_to_Sault_St_Marie");
        Path Montreal_to_New_York = new Path(ColorENUM.BLUE, 3, Montreal, New_York, "Montreal_to_New_York");
        Path Montreal_to_Toronto = new Path(ColorENUM.COLORLESS, 3, Montreal, Toronto, "Montreal_to_Toronto");
        Path Nashville_to_Raleigh = new Path(ColorENUM.BLACK, 3,  Nashville, Raleigh, "Nashville_to_Raleigh");
        Path Nashville_to_St_Louis = new Path(ColorENUM.COLORLESS, 2,  Nashville, St_Louis, "Nashville_to_St_Louis");
        Path New_Orleans_to_Miami = new Path(ColorENUM.RED, 6,   New_Orleans, Miami, "New_Orleans_to_Miami");
        Path New_York_to_Washington_DC = new Path(ColorENUM.ORANGE, 2, New_York, Washington_DC, "New_York_to_Washington_DC");
        Path New_York_to_Pittsburgh = new Path(ColorENUM.GREEN, 2, New_York, Pittsburgh, "New_York_to_Pittsburgh");
        Path Pittsburgh_to_Toronto = new Path(ColorENUM.COLORLESS, 2, Pittsburgh,Toronto, "Pittsburgh_to_Toronto");
        Path Pittsburgh_to_St_Louis = new Path(ColorENUM.GREEN, 5, Pittsburgh,St_Louis, "Pittsburgh_to_St_Louis");
        Path Pittsburgh_to_Nashville = new Path(ColorENUM.YELLOW, 4, Pittsburgh,Nashville, "Pittsburgh_to_Nashville");
        Path Pittsburgh_to_Raleigh = new Path(ColorENUM.COLORLESS, 2, Pittsburgh,Raleigh, "Pittsburgh_to_Raleigh");
        Path Raleigh_to_Washington = new Path(ColorENUM.COLORLESS, 2, Raleigh,Washington_DC, "Raleigh_to_Washington");
        Path Sault_St_Marie_to_Winnipeg = new Path(ColorENUM.COLORLESS, 6, Sault_St_Marie , Winnipeg, "Sault_St_Marie_to_Winnipeg");
        Path Toronto_to_Sault_St_Marie = new Path(ColorENUM.COLORLESS, 2, Toronto,Sault_St_Marie, "Toronto_to_Sault_St_Marie");


        allPaths.add(Calgary_to_Vancouver);
        allPaths.add(Calgary_to_Winnipeg);
        allPaths.add(Calgary_to_Helena);
        allPaths.add(Dallas_to_ElPaso);
        allPaths.add(Dallas_to_OklahomaCity);
        allPaths.add(Dallas_to_Houston);
        allPaths.add(Denver_to_SaltLake);
        allPaths.add(Denver_to_OklahomaCity);
        allPaths.add(Denver_to_KansasCity);
        allPaths.add(Denver_to_SantaFe);
        allPaths.add(Denver_to_Omaha);
        allPaths.add(Denver_to_Helena);
        allPaths.add(Duluth_to_Helena);
        allPaths.add(Duluth_to_Omaha);
        allPaths.add(ElPaso_Houston);
        allPaths.add(ElPaso_to_OklahomaCity);
        allPaths.add(ElPaso_to_SantaFe);
        allPaths.add(ElPaso_to_LosAngeles);
        allPaths.add(ElPaso_to_Phoenix);
        allPaths.add(Helena_to_Seattle);
        allPaths.add(Helena_to_Winnipeg);
        allPaths.add(Helena_to_SaltLake);
        allPaths.add(Helena_to_Omaha);
        allPaths.add(KansasCity_to_Omaha);
        allPaths.add(KansasCity_to_OklahomaCity);
        allPaths.add(LasVegas_to_SaltLake);
        allPaths.add(OklahomaCity_to_SantaFe);
        allPaths.add(Phoenix_to_SantaFe);
        allPaths.add(Portland_to_Seattle);
        allPaths.add(Portland_to_SanFrancisco);
        allPaths.add(Portland_to_SaltLake);
        allPaths.add(Seattle_to_Vancouver);


        allPaths.add(Atlanta_to_Raleigh);
        allPaths.add(Atlanta_to_Charleston);
        allPaths.add(Atlanta_to_Nashville);
        allPaths.add(Atlanta_to_New_Orleans);
        allPaths.add(Atlanta_to_Miami);
        allPaths.add(Boston_to_Montreal);
        allPaths.add(Boston_to_New_York);
        allPaths.add(Charleston_to_Raleigh);
        allPaths.add(Charleston_to_Miami);
        allPaths.add(Chicago_to_Toronto);
        allPaths.add(Chicago_to_Duluth);
        allPaths.add(Chicago_to_Omaha);
        allPaths.add(Chicago_to_St_Louis);
        allPaths.add(Chicago_to_Pittsburgh);
        allPaths.add(Dallas_to_Little_Rock);
        allPaths.add(Duluth_to_Winnipeg);
        allPaths.add(Duluth_to_Sault_St_Marie);
        allPaths.add(Duluth_to_Toronto);
        allPaths.add(Houston_to_New_Orleans);
        allPaths.add(Kansas_City_to_St_Louis);
        allPaths.add(Little_Rock_to_Nashville);
        allPaths.add(Little_Rock_to_New_Orleans);
        allPaths.add(Little_Rock_to_St_Louis);
        allPaths.add(Little_Rock_to_Oklahoma_City);
        allPaths.add(Montreal_to_Sault_St_Marie);
        allPaths.add(Montreal_to_New_York);
        allPaths.add(Montreal_to_Toronto);
        allPaths.add(Nashville_to_Raleigh);
        allPaths.add(Nashville_to_St_Louis);
        allPaths.add(New_Orleans_to_Miami);
        allPaths.add(New_York_to_Washington_DC);
        allPaths.add(New_York_to_Pittsburgh);
        allPaths.add(Pittsburgh_to_Toronto);
        allPaths.add(Pittsburgh_to_St_Louis);
        allPaths.add(Pittsburgh_to_Nashville);
        allPaths.add(Pittsburgh_to_Raleigh);
        allPaths.add(Raleigh_to_Washington);
        allPaths.add(Sault_St_Marie_to_Winnipeg);
        allPaths.add(Toronto_to_Sault_St_Marie);
      
        // Add all of the Paths to the array list of paths


    }


    /**
     * @param name - name of the city to search
     * @return - the city with the given name, or null if unfound
     */
    public City getCityByName(String name){
        for(City c: allCities){
            if(c.getCityName().equals(name)){
                return c;
            }
        }
        return null;
    }
  
    public void setCitiesList(TreeMap<String, City> cities) {
        mCities = cities;
    }

    public ArrayList<City> getCities(){
        return allCities;
    }

    public ArrayList<Path> getPaths(){
        return allPaths;
    }

    public City getCityInMapByName(String name) { return mCities.get(name); }

    public void claimPath(Path path) {
        this.getCurrentTTRGame().claimPath(path);
        notifyObservers();
    }

    public ArrayList<Path> getAllPaths() {
        return this.allPaths;
    }

    /**
     * Find a path in the list of all paths with the given name
     * @param name - name of the path
     * @return - The desired path or null if not found
     */
    public Path getPathByName(String name){
        for (Path p: allPaths){
            if(p.getName().equals(name)){
                return p;
            }
        }
        return null;
    }

    public void addTrainCard(TrainCard card, int playerID) {

        for (User u : mCurrentTTRGame.getUsers()) {
            if (u.getPlayerID() == playerID) {
                mCurrentTTRGame.dealTrainCard(u);
                if (u.getPlayerID() == currentUser.getPlayerID()) {
                    currentUser.addTrainCard(card);
                }
            }
        }
        notifyObservers();
    }

}
