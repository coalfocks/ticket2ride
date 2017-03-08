package com.example.tyudy.ticket2rideclient.model;

import com.example.tyudy.ticket2rideclient.common.Player;
import com.example.tyudy.ticket2rideclient.common.cities.City;
import com.example.tyudy.ticket2rideclient.common.cities.Path;
import com.example.tyudy.ticket2rideclient.interfaces.iObservable;
import com.example.tyudy.ticket2rideclient.interfaces.iObserver;
import com.example.tyudy.ticket2rideclient.common.TTRGame;
import com.example.tyudy.ticket2rideclient.common.User;

import java.util.ArrayList;
import java.util.TreeMap;

import static com.example.tyudy.ticket2rideclient.common.Color.BLACK;
import static com.example.tyudy.ticket2rideclient.common.Color.BLUE;
import static com.example.tyudy.ticket2rideclient.common.Color.COLORLESS;
import static com.example.tyudy.ticket2rideclient.common.Color.GREEN;
import static com.example.tyudy.ticket2rideclient.common.Color.ORANGE;
import static com.example.tyudy.ticket2rideclient.common.Color.PURPLE;
import static com.example.tyudy.ticket2rideclient.common.Color.RED;
import static com.example.tyudy.ticket2rideclient.common.Color.WHITE;
import static com.example.tyudy.ticket2rideclient.common.Color.YELLOW;

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
    private Player currentPlayer;
    private TTRGame mCurrentTTRGame;


    private ClientModel(){
        mTTRGameList = new ArrayList<>();
        obsList = new ArrayList<>();
        chatMsgs = new ArrayList<>();
        ipAddress = null;
        currentUser = null;
        mCurrentTTRGame = null;
        currentPlayer = null;
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

    public void setCurrentPlayer(Player p) {
        currentPlayer = p;
    }

    public void setCurrentTTRGame(TTRGame g){
        mCurrentTTRGame = g;
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
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
     * This method doesn't necessarily need to be in the ClientModel,
     * but I didn't have anywhere else to put it. So for now, it sits here
     */
    public void initCities(){
        // Create All Cities
        mCities = new TreeMap<>();
        ArrayList<Path> cityPaths = new ArrayList<Path>();

        City Atlanta = new City("Atlanta");
        City Boston = new City("Boston");
        City Calgary = new City("Calgary");
        City Charleston = new City("Charleston");
        City Chicago = new City("Chicago");
        City Dallas = new City("Dallas");
        City Denver = new City("Denver");
        City Duluth = new City("Duluth");
        City El_Paso = new City("El Paso");
        City Helena = new City("Helena");
        City Houston = new City("Houston");
        City Kansas_City = new City("Kansas City");
        City Las_Vegas = new City("Las Vegas");
        City Little_Rock = new City("Little Rock");
        City Los_Angeles = new City("Los Angeles");
        City Miami = new City("Miami");
        City Montreal = new City("Montreal");
        City Nashville = new City("Nashville");
        City New_Orleans = new City("New Orleans");
        City New_York = new City("New York");
        City Oklahoma_City = new City("Oklahoma City");
        City Omaha = new City("Omaha");
        City Phoenix = new City("Phoenix");
        City Pittsburgh = new City("Pittsburgh");
        City Portland = new City("Portland");
        City Raleigh = new City("Raleigh");
        City St_Louis = new City("St. Louis");
        City Salt_Lake = new City("Salt Lake City");
        City San_Fransisco = new City("San Fransisco");
        City Santa_Fe = new City("Santa Fe");
        City Sault_St_Marie = new City("Sault St. Marie");
        City Seattle = new City("Seattle");
        City Toronto = new City("Toronto");
        City Vancouver = new City("Vancouver");
        City Washington_DC = new City("Washington DC");
        City Winnipeg = new City("Winnipeg");

        // Note: Naming convention for the paths--whichever is alphabetically
        //  first is the first in the name & pair
        Path seattle_van = new Path(COLORLESS, 1, Seattle, Vancouver); // NEEDS SECOND PATH
        Path calg_van = new Path(COLORLESS, 3, Calgary, Vancouver);

        // VANCOUVER
        cityPaths.add(seattle_van);
        cityPaths.add(calg_van);
        Vancouver.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path calg_seattle = new Path(COLORLESS, 4, Calgary, Seattle);
        Path helena_seattle = new Path(YELLOW, 6, Helena, Seattle);
        Path port_seattle = new Path(COLORLESS, 1, Portland, Seattle);  // NEEDS SECOND PATH

        // SEATTLE
        cityPaths.add(seattle_van);
        cityPaths.add(calg_seattle);
        cityPaths.add(helena_seattle);
        Seattle.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path port_slc = new Path(BLUE, 6, Portland, Salt_Lake);
        Path port_sanfran = new Path(PURPLE, 5, Portland, San_Fransisco);   // NEEDS SECOND PATH

        // PORTLAND
        cityPaths.add(port_sanfran);
        cityPaths.add(port_seattle);
        cityPaths.add(port_slc);
        Portland.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path sanfran_slc = new Path(ORANGE, 5, San_Fransisco, Salt_Lake); // NEEDS SECOND PATH
        Path la_sanfran = new Path(YELLOW, 3, Los_Angeles, San_Fransisco); // NEEDS SECOND PATH

        // SAN FRANSISCO
        cityPaths.add(sanfran_slc);
        cityPaths.add(la_sanfran);
        cityPaths.add(port_sanfran);
        San_Fransisco.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path lasvegas_la = new Path(COLORLESS, 2, Las_Vegas, Los_Angeles);
        Path la_phoenix = new Path(COLORLESS, 3, Los_Angeles, Phoenix);
        Path elpaso_la = new Path(BLACK, 6, El_Paso, Los_Angeles);

        // LOS ANGELES
        cityPaths.add(la_phoenix);
        cityPaths.add(la_sanfran);
        cityPaths.add(lasvegas_la);
        cityPaths.add(elpaso_la);
        Los_Angeles.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path calg_helena = new Path(COLORLESS, 4, Calgary, Helena);
        Path calg_winn = new Path(WHITE, 6, Calgary, Winnipeg);

        // CALGARY
        cityPaths.add(calg_helena);
        cityPaths.add(calg_seattle);
        cityPaths.add(calg_van);
        cityPaths.add(calg_winn);
        Calgary.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path helena_slc = new Path(PURPLE, 3, Helena, Salt_Lake);
        Path lasvegas_slc = new Path(ORANGE, 3, Las_Vegas, Salt_Lake);
        Path denver_slc = new Path(RED, 3, Denver, Salt_Lake); // NEEDS SECOND PATH

        // SALT LAKE CITY
        cityPaths.add(denver_slc);
        cityPaths.add(helena_slc);
        cityPaths.add(lasvegas_slc);
        cityPaths.add(port_slc);
        cityPaths.add(sanfran_slc);
        Salt_Lake.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path denver_phoenix = new Path(WHITE, 5, Denver, Phoenix);
        Path elpaso_phoenix = new Path(COLORLESS, 3, El_Paso, Phoenix);
        Path phoenix_santafe = new Path(COLORLESS, 3, Phoenix, Santa_Fe);

        // PHOENIX
        cityPaths.add(phoenix_santafe);
        cityPaths.add(denver_phoenix);
        cityPaths.add(elpaso_phoenix);
        cityPaths.add(la_phoenix);
        Phoenix.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path helena_winnipeg = new Path(BLUE, 4, Helena, Winnipeg);
        Path duluth_helena = new Path(ORANGE, 6, Duluth, Helena);
        Path helena_omaha = new Path(RED, 5, Helena, Omaha);
        Path helena_denver = new Path(GREEN, 4, Helena, Denver);

        // HELENA
        cityPaths.add(helena_denver);
        cityPaths.add(helena_omaha);
        cityPaths.add(helena_seattle);
        cityPaths.add(helena_slc);
        cityPaths.add(helena_winnipeg);
        cityPaths.add(calg_helena);
        cityPaths.add(duluth_helena);
        Helena.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path denver_omaha = new Path(PURPLE, 4, Denver, Omaha);
        Path denver_kansas = new Path(BLACK, 4, Denver, Kansas_City); // NEEDS SECOND PATH
        Path denver_oklahoma = new Path(RED, 4, Denver, Oklahoma_City);
        Path denver_santafe = new Path(COLORLESS, 2, Denver, Santa_Fe);

        // DENVER
        cityPaths.add(denver_kansas);
        cityPaths.add(denver_oklahoma);
        cityPaths.add(denver_omaha);
        cityPaths.add(denver_phoenix);
        cityPaths.add(denver_santafe);
        cityPaths.add(denver_slc);
        cityPaths.add(helena_denver);
        Denver.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();

        Path oklahoma_santafe = new Path(BLUE, 3, Oklahoma_City, Santa_Fe);
        Path elpaso_santafe = new Path(COLORLESS, 2, El_Paso, Santa_Fe);

        // SANTA FE
        cityPaths.add(denver_santafe);
        cityPaths.add(elpaso_santafe);
        cityPaths.add(oklahoma_santafe);
        cityPaths.add(phoenix_santafe);
        Santa_Fe.setPaths(new ArrayList<Path>(cityPaths));
        cityPaths.clear();


        // Init map of cities
        mCities.put(Atlanta.getCityName(), Atlanta);
        mCities.put(Boston.getCityName(), Boston);
        mCities.put(Calgary.getCityName(), Calgary);
        mCities.put(Charleston.getCityName(), Charleston);
        mCities.put(Chicago.getCityName(), Chicago);
        mCities.put(Dallas.getCityName(), Dallas);
        mCities.put(Denver.getCityName(), Denver);
        mCities.put(Duluth.getCityName(), Duluth);
        mCities.put(El_Paso.getCityName(), El_Paso);
        mCities.put(Helena.getCityName(), Helena);
        mCities.put(Houston.getCityName(), Houston);
        mCities.put(Kansas_City.getCityName(), Kansas_City);
        mCities.put(Las_Vegas.getCityName(), Las_Vegas);
        mCities.put(Little_Rock.getCityName(), Little_Rock);
        mCities.put(Los_Angeles.getCityName(), Los_Angeles);
        mCities.put(Miami.getCityName(), Miami);
        mCities.put(Montreal.getCityName(), Montreal);
        mCities.put(Nashville.getCityName(), Nashville);
        mCities.put(New_Orleans.getCityName(), New_Orleans);
        mCities.put(New_York.getCityName(), New_York);
        mCities.put(Oklahoma_City.getCityName(), Oklahoma_City);
        mCities.put(Omaha.getCityName(), Omaha);
        mCities.put(Phoenix.getCityName(), Phoenix);
        mCities.put(Pittsburgh.getCityName(), Pittsburgh);
        mCities.put(Portland.getCityName(), Portland);
        mCities.put(Raleigh.getCityName(), Raleigh);
        mCities.put(St_Louis.getCityName(), St_Louis);
        mCities.put(Salt_Lake.getCityName(), Salt_Lake);
        mCities.put(San_Fransisco.getCityName(), San_Fransisco);
        mCities.put(Santa_Fe.getCityName(), Santa_Fe);
        mCities.put(Sault_St_Marie.getCityName(), Sault_St_Marie);
        mCities.put(Seattle.getCityName(), Seattle);
        mCities.put(Toronto.getCityName(), Toronto);
        mCities.put(Vancouver.getCityName(), Vancouver);
        mCities.put(Washington_DC.getCityName(), Washington_DC);
        mCities.put(Winnipeg.getCityName(), Winnipeg);
    }

}
