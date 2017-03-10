package com.example.tyudy.ticket2rideclient.model;

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
        initCities();
        initPaths();
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

    /**
     * Initialize all cities in the map with a name and location
     */
    private void initCities(){
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


    }

    /**
     * Initialize all cities in the map with a name and location
     */
    private void initPaths(){

        //Create all of the Paths NOTE: Their name is based on the two connected cities alphabetical order
        Path Seattle_to_Vancouver = new Path()
        City
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

    public City getCityInMapByName(String name) { return mCities.get(name); }

}
